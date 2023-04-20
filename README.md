# PresFeed

PresFeed is a demo app that demonstrates the MVVM architecture pattern in Android. It uses various technologies such as Hilt, Room, Navigation, LiveData, coroutines, Retrofit, Okhttp, Moshi, and unit testing.

## Features

PresFeed has two pages:

1. Headlines page: This page shows a list of headlines from a specific news source.
2. News detail page: This page shows the details of a news article.

## Installation

To run the app, you need to clone the repository and open it in Android Studio. Then, you can build and run the app on an emulator or a physical device.

Before building the app, you need to add your own API key for the news service. To do this, create a `apikey.properties` file in the root directory of the project and add the following line, replacing `<your-api-key>` with your actual API key:



## Technologies Used

PresFeed uses the following technologies:

- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [Room](https://developer.android.com/training/data-storage/room) for database management
- [Navigation](https://developer.android.com/guide/navigation) for navigating between screens
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) for observing data changes
- [Coroutines](https://developer.android.com/kotlin/coroutines) for managing asynchronous tasks
- [Retrofit](https://square.github.io/retrofit/) for making API calls
- [Okhttp](https://square.github.io/okhttp/) for handling network requests
- [Moshi](https://github.com/square/moshi) for JSON parsing
- Unit testing with [JUnit](https://junit.org/junit4/), [Mockito](https://site.mockito.org/), and [Espresso](https://developer.android.com/training/testing/espresso)

## Architecture

PresFeed follows the MVVM (Model-View-ViewModel) architecture pattern. Here's a brief overview of the architecture:

- **Model**: This layer contains the data models and repository classes that manage the data.
- **View**: This layer contains the UI components such as Activities, Fragments, and XML layouts.
- **ViewModel**: This layer acts as a mediator between the View and Model layers. It contains the business logic of the app and provides data to the View layer.






