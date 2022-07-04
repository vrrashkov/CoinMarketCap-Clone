# CoinMarketCap Clone Sample

This sample project presents an app similar to CoinMarketCap.

## Preview

<img title="" src="screenshots/showcase.gif" alt="Screenshot" width="200">

## This sample showcases:

- Kotlin + Jetpack Compose + Flows & Coroutines

- Multi Module, MVVM, Clean Architecture

- Jetpack Navigation, Room, Hilt

- Retrofit REST Api

## Features

### Jetpack Navigation

This app shows how we can navigate with **Jetpack Navigation** between different screens and between screens with common layouts using **nested NavHost**. This helps us navigating between different screens and using for example **BottomTabBar** with **Jetpack Navigation**
 
### Multi Module

- app (App entry point, containing basic navigation setup and DI module initialization

- core (Anything that will be common and is **NOT** UI related) 

- datasource
	- database (Room)
	
	- domain (Usecases, Mappers, Repositories, Models)
	
	- graphql (Not integrated yet)
	
	- network (Rest Api)

- ui
	- common (Anything that will be common and **IS** UI related)
	- every major functionallity is separated in different modules
	
### MVVM

Screens mostly consist of:

- View (Everything from the UI)

- ViewModel (Managing our screen ViewModel) 

- State

- NavigationEvent (Events that change our screens)

- ActionEvent (One Time trigger events like: show dialog, open drawer)

All events are collected in the UI layer within **LaunchedEffect**