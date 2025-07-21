# 🎵 TopAlbumsApp

A modern Android app that displays the top 100 albums from the iTunes RSS feed. Built with MVVM, Retrofit, and LiveData.

---

## 🚀 Features

- Fetches top 100 albums from [iTunes RSS Feed](https://itunes.apple.com/us/rss/topalbums/limit=100/json)
- Displays album list with image, artist, genre, and release date
- Album details screen
- Error handling and loading indicators
- Unit tests for ViewModel
- MVVM architecture
- Dependency Injection (Hilt)
- Clean architecture principles

---

## 🖼️ Screenshots

| Album List | Album Details |
|------------|---------------|
| ![list](screenshots/album_list.png) | ![details](screenshots/album_details.png) |

---

## 🛠️ Tech Stack

- **Kotlin**
- **MVVM** architecture
- **Coroutines**
- **LiveData**
- **Retrofit2** for networking
- **Gson** for JSON parsing
- **Hilt** for DI
- **ViewBinding** or **DataBinding**
- **JUnit** for unit testing

---

## 🧪 Testing

- ViewModel unit tests: `AlbumListViewModelTest.kt`
- Mocked repository using `Mockito`

---

## 📦 Project Structure

<details>
<summary>Click to expand</summary>

com.example.topalbumsapp/<br>
├── models/           # DTOs and domain models<br>
├── repositories/     # Repository + mapper<br>
├── services/         # Retrofit API<br>
├── network/          # Interceptors and network layer<br>
├── features/<br>
├── ui/               # UI: Fragments, ViewModels, Adapters<br>
├── di/               # Dependency injection setup<br>
├── utils/            # Binding adapters, constants, helpers<br>
└── MainApplication.kt<br>

</details>

---

## 📲 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Kotlin 1.9+
- Gradle 8+
- JDK 11


### Clone the repository

