# Weather app

This is an application that allows the user to know the weather based on their current location.

## Design of the app
- The app was made using jetpack compose to made the UI implementation more faster.
- Almost all images are obtained from the weather api and only some resources are found locally.
- Airbnb's lottie library was used to show some error images and give the app a nice look
- The API used to obtain the weather information was https://api.openweathermap.org

## Architecture

The architecture of the application is based on the following points:

- A single-activity architecture, using the [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started) to manage navigation operations.
- Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) (MVVM) facilitating a [separation](https://en.wikipedia.org/wiki/Separation_of_concerns) and organization between the view layer and network layer.
- [S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) design principles intended to make software designs more understandable, flexible and maintainable.
- [Modular app architecture](https://proandroiddev.com/build-a-modular-android-app-architecture-25342d99de82) allows to be developed features in isolation, independently from other features.

### Modules

The next graph shows the app modularisation:

- `:app` indirectly depends on `:features`.
- 
- `:features` modules depends on `:lib:network`, `:lib:data` and `:lib:model`.
- 
- `:lib:network` depends of `:lib:model`and only of the android native frameworks for backend communication and testing. This layer don’t depends on any UI framework. This project contains also the necessary repositories to propagate the information between the `:features` and `:lib:data`.
- 
- `:lib:data` depends of `:lib:model` and contains all the necessary use cases to make the app works(Use cases for fetching weather information). This project contains also the necessary repositories to propagate the information between the `:features` and `:lib:network`.
- 
- `:lib:model` don’t have any dependency.
- 
- `:lib` don’t have any dependency.

#### App module

The`:app` module is the main entry of the client app.  It is also responsible for initiating the [dependency graph]

#### Core module(Network)

The `:lib:network` module is an android library  for serving network requests. Providing the data source for the weather app information and the necessary repositories to pass the info to the `:lib:data`.

#### Core module(Data)

The `:lib:data` module is an android library  and contains the use cases that fetch the data from the `:lib:network` and the repositories classes tha organize the data for the view models.

#### Features modules

In the `:features` module you will find the code for the weather app.

#### Libraries modules

The `:lib` modules basically contains different utilities that can be used by the different modules.

#### Technology

Some of the main libraries used for this app are:

1. Dagger Hilt for handling the dependency injection
2. Jetpack compose for UI implementation.
3. Kotlin language according to the requirements of the test.
4. Compose Navigation for handling the navigation inside the app.
5. Coroutines for fetching weather data and async communication.
6. Mockito, Junit and Mockk libraries were use to create the unit tests
7. Okhttp vs retrofit for information requests from the client app.
8. Coil library for showing images coming from the weather API.

For more information related to the libraries used, please check the dependencies.gradle file inside the on the buildSystem folder.

#### Project setup

1. As an initial step you should clone the repository.
2. Add this key `API_KEY = "d8694f0099874d2e0f80149dca56ad66"`  to the `local.properties` file
3. Compile and run the app on a device(Emulator can lead to some location issues, causing the app does not work as expected)
4. Location permissions must be granted for the app to work.


#### What would I do if I had more time

1. I would have liked to implement the UI tests but due to the time I had to do the test, I was only able to implement the unit tests
2. I would have liked to work a little more on the design as there are details and components in the code that could be done better.
3. I would have liked to created a `:common` resources module. For example, there is a class that I use in the unit tests to mock the information which I had to copy and paste in all the layers, causing the code to be quite repeated. If I had created a `:common` module, the above would have been avoided.
4. If I had had more time I would have tried to organize the code a little better in the feature module and I would have tried to separate it into some utility classes.
5. There are some strings in the feature code that are hardcoded, I would like to fix this as well.

#### What was achieved during the test

1. Coroutines were used for the communication with the API.
2. The app was written entirely in kotlin.
3. Jetpack compose was used to write UI screens and components
4. In the application the information is being handled as if they were states that update the UI.
5. SOLID and clean architecture principles were applied.
6. An architecture by features was used thinking about the scalability of the project and to allow other developers to understand and scale the project more easily.
7. A ViewModel pattern was used to communicate the changes from the data and network layers to the UI layer.
8. Unit tests were implemented for all the layers in the app.

#### Thanks for the opportunity given, it was a lot of fun for me to take this test

