package com.vrashkov.coinmarketcapclone.ui.common.textfield

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrashkov.coinmarketcapclone.core.enums.TextFieldEnum
import com.vrashkov.coinmarketcapclone.core.theme.CoinMarketCapCloneTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrimaryTextField(
    modifier: Modifier = Modifier,
    state: TextFieldEnum,
    onFocusChanged: ((FocusState) -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    bringIntoViewRequester: BringIntoViewRequester = BringIntoViewRequester(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    label: String,
    labelSuffix: String? = null,
    labelSuffixOnClick: () -> Unit = {},
    placeholder: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    error: String? = null,
    leadingContent: @Composable() (() -> Unit)? = null,
    prefixLabelStyle: TextStyle = CoinMarketCapCloneTheme.typography.display_medium.copy(
            fontSize = 17.sp,
    ),
    suffixLabelStyle: TextStyle = CoinMarketCapCloneTheme.typography.display_medium.copy(
        fontSize = 17.sp,
    ),
    defaultMinSizeHeight: Dp = 54.dp,
    singleLine: Boolean = true,
    semanticsDescription: String = "Enter email and password"
) {
    Column(
        modifier = modifier
    ) {
        val coroutineScope = rememberCoroutineScope()

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = label,
                style = prefixLabelStyle.copy(
                    color = when (state) {
                        TextFieldEnum.Focused -> CoinMarketCapCloneTheme.colors.tabSelected
                        TextFieldEnum.Error -> CoinMarketCapCloneTheme.colors.error
                        else -> CoinMarketCapCloneTheme.colors.secondary
                    }
                ),
            )

            labelSuffix?.let {
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp),
                    text = labelSuffix,
                    style = suffixLabelStyle.copy(
                        color = CoinMarketCapCloneTheme.colors.tabNormal
                    ),
                )
            }
        }

        OutlinedTextField(
            modifier = Modifier
                .semantics {
                    contentDescription = semanticsDescription
                }
                .padding(top = 2.dp)
                .defaultMinSize(minHeight = defaultMinSizeHeight)
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChanged?.invoke(it)
                }
                .bringIntoViewRequester(bringIntoViewRequester)
                .onFocusEvent {
                    if (it.isFocused || it.hasFocus) {
                        coroutineScope.launch {
                            delay(250)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = CoinMarketCapCloneTheme.colors.primary,
                focusedIndicatorColor = when (state) {
                    TextFieldEnum.Error -> CoinMarketCapCloneTheme.colors.error
                    else -> CoinMarketCapCloneTheme.colors.tabSelected
                },
                unfocusedIndicatorColor = CoinMarketCapCloneTheme.colors.tabNormal,
                disabledIndicatorColor = Color.Transparent,
                textColor = when (state) {
                    TextFieldEnum.Error -> CoinMarketCapCloneTheme.colors.error
                    else -> CoinMarketCapCloneTheme.colors.tabNormal
                },
                placeholderColor = when (state) {
                    TextFieldEnum.Error -> CoinMarketCapCloneTheme.colors.error
                    else -> CoinMarketCapCloneTheme.colors.tabNormal
                },
                cursorColor = CoinMarketCapCloneTheme.colors.tabSelected,
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            placeholder = {
                Text(text = placeholder)
            },
            visualTransformation = visualTransformation,
            textStyle = CoinMarketCapCloneTheme.typography.display_medium.copy(fontSize = 17.sp),
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingContent,
            trailingIcon = trailingIcon,
        )

        error?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                text = error,
                fontSize = 17.sp,
                color = CoinMarketCapCloneTheme.colors.error,
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    focusState: TextFieldEnum,
    onFocusChanged: ((FocusState) -> Unit)? = null,
    label: String,
    placeholder: String = "",
    labelSuffix: String? = null,
    labelSuffixOnClick: () -> Unit,
    error: String? = null,
    value: String,
    onValueChange: (String) -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    PrimaryTextField(
        modifier = modifier,
        state = focusState,
        onFocusChanged = onFocusChanged,
        label = label,
        placeholder = placeholder,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        error = error,
        value = value,
        labelSuffix = labelSuffix,
        labelSuffixOnClick = labelSuffixOnClick,
        onValueChange = onValueChange,
        trailingIcon = {
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        isPasswordVisible = !isPasswordVisible
                    }
                    .background(CoinMarketCapCloneTheme.colors.tabNormal)
                    .padding(7.dp),
            ) {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = rememberVectorPainter(
                        if (isPasswordVisible) Icons.Rounded.Visibility else Icons.Rounded.VisibilityOff
                    ),
                    colorFilter = ColorFilter.tint(CoinMarketCapCloneTheme.colors.primary),
                    contentDescription = if (isPasswordVisible) "Make password visible" else "Make password hidden")
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun PrimaryTextFieldPreview() {
    MaterialTheme {
        PrimaryTextField(
            state = TextFieldEnum.Focused,
            onFocusChanged = {},
            label = "Password",
            placeholder = "Type your secure password",
            value = "Normal",
            onValueChange = { }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
private fun PrimaryTextFieldPreviewUnfocused() {
    MaterialTheme {
        PrimaryTextField(
            state = TextFieldEnum.NotFocused,
            onFocusChanged = {},
            label = "Password",
            placeholder = "Type your secure password",
            value = "Normal",
            onValueChange = { }
        )
    }
}