package com.vrashkov.coinmarketcapclone.ui.common.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.compose.ui.window.Dialog

internal const val ANIMATION_TIME = 300L

@Composable
internal fun AnimatedModalBottomSheetTransition(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(ANIMATION_TIME.toInt()),
            initialOffsetY = { fullHeight -> fullHeight }
        ),
        exit = slideOutVertically(
            animationSpec = tween(ANIMATION_TIME.toInt()),
            targetOffsetY = { fullHeight -> fullHeight }
        ),
        content = content
    )
}
/**
 * Figured out by trial and error
 */
private const val DIALOG_BUILD_TIME = 200L

/**
 * [Dialog] which uses a modal transition to animate in and out its content.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ModalTransitionDialog(
    onDismissRequest: () -> Unit,
    dismissOnBackPress: Boolean = true,
    content: @Composable (ModalTransitionDialogHelper) -> Unit
) {

    val onCloseSharedFlow: MutableSharedFlow<Unit> = remember { MutableSharedFlow() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val animateContentBackTrigger = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        launch {
            //delay(DIALOG_BUILD_TIME)
            animateContentBackTrigger.value = true
        }
        launch {
            onCloseSharedFlow.asSharedFlow().collectLatest { startDismissWithExitAnimation(animateContentBackTrigger, onDismissRequest) }
        }
    }

    Dialog(
        onDismissRequest = { coroutineScope.launch { startDismissWithExitAnimation(animateContentBackTrigger, onDismissRequest) } },
        properties = DialogProperties(usePlatformDefaultWidth = false, dismissOnBackPress = dismissOnBackPress, dismissOnClickOutside = false)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) { // Required in order to occupy the whole screen before the animation is triggered
            AnimatedModalBottomSheetTransition(visible = animateContentBackTrigger.value) {
                content(ModalTransitionDialogHelper(coroutineScope, onCloseSharedFlow))
            }
        }
    }
}

private suspend fun startDismissWithExitAnimation(
    animateContentBackTrigger: MutableState<Boolean>,
    onDismissRequest: () -> Unit
) {
    animateContentBackTrigger.value = false
    delay(ANIMATION_TIME)
    onDismissRequest()
}

/**
 * Helper class that can be used inside the content scope from
 * composables that implement the [ModalTransitionDialog] to hide
 * the [Dialog] with a modal transition animation
 */
class ModalTransitionDialogHelper(
    private val coroutineScope: CoroutineScope,
    private val onCloseFlow: MutableSharedFlow<Unit>
) {
    fun triggerAnimatedClose() {
        coroutineScope.launch {
            onCloseFlow.emit(Unit)
        }
    }
}