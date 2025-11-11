# Telegram Plus - Android App

A feature-rich Telegram-like messaging application built with Material 3 design guidelines and modern Android development practices.

## Features

### Core Features
- 💬 **Real-time Messaging** - Send and receive messages instantly
- 👥 **Group Chats** - Create and manage group conversations
- 📞 **Voice & Video Calls** - High-quality audio and video calling
- 📁 **Media Sharing** - Share photos, videos, documents, and more
- 🔔 **Push Notifications** - Stay updated with Firebase Cloud Messaging
- 🔍 **Search** - Find messages, chats, and contacts quickly

### Plus Features (Advanced)
- 👻 **Ghost Mode** - Hide your online status and read receipts
- 🛡️ **Anti-Delete** - Save messages even after they're deleted
- 📂 **Chat Folders** - Organize chats into custom folders
- ⏰ **Scheduled Messages** - Send messages at a specific time
- 🎨 **Custom Themes** - Personalize the app with custom colors
- 🌐 **Message Translation** - Translate messages in real-time
- 🔄 **Forward Without Quote** - Forward messages without attribution
- 💾 **Auto-Save to Gallery** - Automatically save media to gallery
- ⌨️ **Hide Keyboard on Scroll** - Better chat experience
- 👑 **Admin Panel** - Advanced group management tools

## Technology Stack

### Architecture
- **MVVM Pattern** - Clean separation of concerns
- **Repository Pattern** - Centralized data management
- **Kotlin Coroutines** - Asynchronous programming
- **Flow & StateFlow** - Reactive data streams

### UI/UX
- **Material 3 Design** - Latest Material Design guidelines
- **Jetpack Compose** - Modern declarative UI (partial)
- **View Binding** - Type-safe view access
- **RecyclerView** - Efficient list rendering
- **ViewPager2** - Smooth tab navigation

### Data & Storage
- **Room Database** - Local data persistence
- **DataStore** - Modern preferences storage
- **Encrypted Shared Preferences** - Secure data storage

### Networking
- **Retrofit** - REST API client
- **OkHttp** - HTTP client with interceptors
- **Gson** - JSON serialization

### Media & Files
- **Coil** - Fast image loading
- **Glide** - Image caching and transformation
- **ExoPlayer** - Video and audio playback
- **CameraX** - Modern camera API

### Firebase Services
- **Firebase Cloud Messaging** - Push notifications
- **Firebase Auth** - User authentication (optional)

### Additional Libraries
- **WorkManager** - Background task scheduling
- **Paging 3** - Efficient data pagination
- **Lottie** - Animation support

## Project Structure

```
app/
├── src/main/
│   ├── java/com/telegramplus/app/
│   │   ├── data/
│   │   │   ├── local/
│   │   │   │   ├── dao/          # Room DAOs
│   │   │   │   └── TelegramDatabase.kt
│   │   │   ├── model/            # Data models
│   │   │   └── repository/       # Data repositories
│   │   ├── features/             # Plus features
│   │   ├── services/             # Background services
│   │   ├── ui/
│   │   │   ├── adapters/         # RecyclerView adapters
│   │   │   ├── auth/             # Login/signup
│   │   │   ├── chat/             # Chat screens
│   │   │   ├── fragments/        # Fragment components
│   │   │   ├── main/             # Main activity
│   │   │   ├── profile/          # User profile
│   │   │   ├── settings/         # App settings
│   │   │   └── viewmodel/        # ViewModels
│   │   ├── util/                 # Utility classes
│   │   └── TelegramPlusApp.kt    # Application class
│   └── res/
│       ├── drawable/             # Vector drawables
│       ├── layout/               # XML layouts
│       ├── menu/                 # Menu resources
│       ├── values/               # Colors, strings, themes
│       └── xml/                  # XML configurations
```

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17 or later
- Android SDK 34
- Minimum SDK 24 (Android 7.0)

### Building the App

1. **Clone the repository**
   ```bash
   cd "Telegram App"
   ```

2. **Open in Android Studio**
   - File → Open → Select the project directory

3. **Sync Gradle**
   - Android Studio will automatically sync Gradle files
   - Wait for dependencies to download

4. **Run the app**
   - Connect an Android device or start an emulator
   - Click the Run button or press Shift+F10

### Configuration

1. **Firebase Setup** (Optional for FCM)
   - Create a Firebase project
   - Download `google-services.json`
   - Place it in `app/` directory
   - Add Firebase plugin to `build.gradle`

2. **API Configuration**
   - Update API endpoints in network configuration files
   - Add your API keys if required

## Material 3 Design

This app fully implements Material 3 (Material You) design:

- **Dynamic Color** - Adapts to system theme
- **Color Roles** - Primary, Secondary, Tertiary colors
- **Typography Scale** - Material 3 type system
- **Elevation** - Modern shadow system
- **Shape System** - Rounded corners and custom shapes
- **Motion** - Smooth transitions and animations

## Key Features Implementation

### Ghost Mode
Hides your online status and prevents read receipts from being sent. Perfect for privacy-conscious users.

### Anti-Delete
Saves messages locally before deletion, allowing you to view deleted messages. Uses Room database with soft deletes.

### Chat Folders
Organize your chats into custom folders with colors and icons. Supports filtering by pinned, muted, read status.

### Scheduled Messages
Schedule messages to be sent at a specific time using WorkManager for reliable delivery.

## Contributing

Contributions are welcome! Please follow these guidelines:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Write/update tests
5. Submit a pull request

## License

This project is for educational purposes. Telegram® is a registered trademark of Telegram FZ-LLC.

## Disclaimer

This is a demonstration project showcasing Android development with Material 3. It is not affiliated with Telegram and should not be used for production purposes without proper backend implementation.

## Contact

For questions or support, please open an issue in the repository.

---

Built with ❤️ using Material 3 and modern Android development practices.
