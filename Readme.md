# 🕒 Always On Display - Custom AOD Clock & Ambient Lock Screen Suite

[![Kotlin](https://img.shields.io/badge/Kotlin-2.4.21-purple.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Android Gradle Plugin](https://img.shields.io/badge/AGP-9.4.1-blue.svg?style=flat&logo=android)](https://developer.android.com/studio/releases/gradle-plugin)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202026.09.00-green.svg?style=flat&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Material 3](https://img.shields.io/badge/Material%203-Expressive-orange.svg?style=flat&logo=materialdesign)](https://m3.material.io/)
[![Clean Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM%20%2B%20UDF-brightgreen.svg?style=flat)](#-architecture--design-patterns)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](LICENSE)

**Always On Display** is a modern, privacy-first, 100% Jetpack Compose ambient lock screen and customizable AOD application for Android. Built with **Kotlin**, **Jetpack Compose (Material 3)**, and strict **Clean Architecture**, Always On Display enables users to customize, preview, and activate stunning **Digital**, **Abstract**, **Neon**, and **Atomic** clock styles on their lock screens with live battery metrics, date display, 500ms touch debouncing, full landscape responsiveness, and Android 16 (API 37) compatibility—all with zero tracking and no network requirements.

---

## 🌟 Overview & Philosophy

Always On Display operates on three core principles:
1. **100% Offline & Privacy-First**: All clock configurations, battery monitoring, and overlay service settings stay strictly on your local device. Zero tracking, zero telemetry, and no `INTERNET` permission requested.
2. **Seamless Customization Flow**: Choose between 4 distinct clock categories (Digital, Abstract, Neon, and Atomic), preview full-screen OLED themes, and toggle the foreground overlay service with a single tap.
3. **Engineering Excellence**: Built with strict Clean Architecture, state hoisting, 500ms touch debouncing, responsive dual-orientation landscape layouts, scalable design tokens (`AppDimensions` & `AppTypography`), and Android 16 (API 37) `showWhenLocked` keyguard overlay support.

---

## ✨ Core Features & Capabilities

### 📱 1. Digital Clock Customization
- **10 Unique Digital Styles**: Choose from a wide collection of digital clock layouts ranging from bold neon digits, LED rounded containers, progress rings, and multi-colored time displays.
- **Integrated Battery & Date Info**: Real-time battery percentage monitoring and date formatting integrated directly into digital clock themes.

### 🎨 2. Abstract Analog Clocks
- **10 Artistic Abstract Dial Themes**: Beautifully crafted abstract analog dials with custom-rendered hour, minute, and second hands.

### 💡 3. Neon Analog Clocks
- **10 Vibrant Neon Glowing Dials**: High-contrast, neon-illuminated analog dials optimized for OLED and AMOLED screens.

### ⚛️ 4. Atomic Precision Clocks
- **10 Atomic-Themed Dial Designs**: Sleek, high-precision atomic dial layouts with smooth analog hand movement.

### 🖤 5. OLED-Optimized Full-Screen Preview & AOD Mode
- Pure OLED black background (`#000000`) theme designed to conserve battery while displaying crisp, vibrant clock elements, date text, and live battery percentage.

### 🔒 6. Keyguard & Lock Screen Overlay Service
- Foreground Service (`OverlayService` using `specialUse` FGS) with `ACTION_SCREEN_OFF` broadcast receiver.
- Automatically triggers on screen-off and displays over the keyguard (`android:showWhenLocked="true"` & `android:turnScreenOn="true"`).

### 🛡️ 7. Smart Permission Onboarding & Auto-Skip
- Guided onboarding explaining System Overlay (`SYSTEM_ALERT_WINDOW`) and Runtime Notification (`POST_NOTIFICATIONS`) requirements.
- Automatically skips the permission screen if required permissions are already granted.

### ⚡ 8. Debounced Touch Guard Architecture
- Centralized 500ms touch debouncing (`clickableDebounced` & `rememberDebouncedClick`) across all buttons, cards, and switches to eliminate rapid multi-click/double-tap glitches.

### 📐 9. Scalable Design System Tokens
- 100% Jetpack Compose UI driven by centralized `AppDimensions` (SDP/SSP scalable dimensions) and `AppTypography` design tokens.
- Single-component design system wrappers (`AppButton`, `AppOutlinedButton`, `AppSwitch`, `AppCard`, `AppScaffold`, `AppTopAppBar`, `ClockItemCard`, etc.).

### 📐 10. Responsive & Dual-Orientation Landscape Support
- Fluid layout adaptations supporting both Portrait vertical flows and side-by-side two-column Landscape layouts for selection grids, detail cards, and full-screen previews.

---

## 📐 Architecture & Design Patterns

Always On Display follows modern Android architectural standards with strict **Clean Architecture**, **MVVM**, and **Unidirectional Data Flow (UDF)**:

### Complete Project Directory & Package Structure

```
com.example.always_on_display/
├── App.kt                          # Application entry point & AppContainer host
├── MainActivity.kt                 # Single Activity entry point & keyguard lock screen flag host
│
├── core/
│   ├── designsystem/
│   │   ├── components/            # Centralized Design System primitives (AppButton, AppCard, AppSwitch, AppTopAppBar, AppIconButton, AppIcon, AppDigitalClock, AppAnalogClock, ClockItemCard, etc.)
│   │   └── theme/                 # Theme, Color, AppTypography, AppDimensions
│   └── utils/                     # Kotlin extensions & utilities (BatteryUtils, DebounceExtensions)
│
├── data/
│   ├── datastore/                 # Jetpack DataStore Preferences datasource (ClockDataStore)
│   └── repository/                # Concrete repository implementations (ClockSettingsRepositoryImpl, BatteryRepositoryImpl)
│
├── di/                            # Dependency Injection Container & Factory
│   ├── AppContainer.kt            # Manual DI Container instantiating repositories & use cases
│   └── ViewModelFactory.kt        # ViewModelProvider.Factory supplying use cases to ViewModels
│
├── domain/
│   ├── model/                     # Domain models (ClockConfig, ClockCategory, BatteryInfo)
│   ├── repository/                # Abstract repository interfaces (ClockSettingsRepository, BatteryRepository)
│   └── usecases/                  # Single-responsibility business use cases
│       ├── GetBatteryInfoUseCase.kt
│       ├── GetClockSettingsUseCase.kt
│       ├── SaveClockSettingsUseCase.kt
│       └── ToggleServiceUseCase.kt
│
├── presentation/                  # Presentation Layer (Jetpack Compose + MVVM + UDF)
│   ├── abstractclock/             # Abstract Clock Selection, Detail, Resource Mapper, & ViewModel
│   ├── atomic/                    # Atomic Clock Selection, Detail, Resource Mapper, & ViewModel
│   ├── digital/                   # Digital Clock Selection, Preview, & ViewModel
│   ├── display/                   # Always On Display Full-Screen Screen & ViewModel
│   ├── main/                      # Home Screen, CategoryCard, & ViewModel
│   ├── navigation/                # NavHost, AppNavigation, & Screen routes
│   ├── neon/                      # Neon Clock Selection, Detail, Resource Mapper, & ViewModel
│   ├── permission/                # Overlay & Notification Permission Onboarding Screen & ViewModel
│   └── splash/                    # SplashScreen & ViewModel
│
└── service/
    └── OverlayService.kt          # Foreground Service with Screen-Off Broadcast Receiver & Notification
```

### Key Engineering Practices:
- **Unidirectional Data Flow (UDF)**: ViewModels expose immutable `StateFlow<UiState>` collected cleanly with `collectAsStateWithLifecycle()`.
- **Debounced Interactions**: User actions use a 500ms debounce guard (`clickableDebounced` / `rememberDebouncedClick`) to prevent duplicate click triggers.
- **Strict Design System Adherence**: 100% of screens, cards, dialogs, and top app bars leverage centralized app components (`AppTopAppBar`, `AppIconButton`, `AppCard`, `ClockItemCard`, etc.) for consistency.
- **Android 16 & Keyguard Compliance**: Configured with `showWhenLocked`, `turnScreenOn`, and `specialUse` foreground service types to comply with Android 14+ / 16+ background execution rules.

---

## 🛠 Tech Stack & Dependencies

All dependencies are centrally managed via Gradle Version Catalog ([`libs.versions.toml`](gradle/libs.versions.toml)):

| Category | Library / Dependency | Version | Purpose |
| :--- | :--- | :--- | :--- |
| **Language** | [Kotlin](https://kotlinlang.org/) | `2.4.21` | Modern, expressive, type-safe programming |
| **Build System** | [Android Gradle Plugin (AGP)](https://developer.android.com/build) | `9.4.1` | Project build configuration |
| **UI Framework** | [Jetpack Compose BOM](https://developer.android.com/develop/ui/compose/bom) | `2026.09.00` | Declarative UI toolkit |
| **Design System** | [Material 3](https://developer.android.com/develop/ui/compose/components) | Expressive | Material Design 3 UI components |
| **Icons** | Material Icons Extended | BOM Managed | Comprehensive icon set |
| **Navigation** | [Navigation Compose](https://developer.android.com/guide/navigation) | `2.10.2` | Single-activity Compose navigation |
| **Preferences** | [Jetpack DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore) | `1.3.0` | Asynchronous key-value settings storage |
| **Responsive Units** | [SDP & SSP Android](https://github.com/intuit/sdp) | `1.1.1` | Scalable size and font dimensions |
| **Animations** | [Lottie Compose](https://github.com/Airbnb/lottie-android) | `6.7.1` | Vector animations |
| **Background Work** | [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) | `2.12.0` | Background task scheduling |
| **Release Optimization** | R8 Shrinking & ProGuard | Enabled | Minification & resource optimization (`isMinifyEnabled = true`) |

---

## 🚀 Getting Started & Git Instructions

### Prerequisites
- **Android Studio**: Android Studio Ladybug (2024.2+) or newer.
- **Java Development Kit (JDK)**: JDK 25.
- **Android SDK Requirements**:
  - `compileSdk`: `37`
  - `targetSdk`: `37`
  - `minSdk`: `24` (Android 7.0 Nougat or higher)

### 1. Clone the Repository
```bash
git clone https://github.com/hammad-ahmed-android-engineer/AlwaysOnDisplay.git
cd AlwaysOnDisplay
```

### 2. Open Project in Android Studio
1. Launch Android Studio.
2. Select **File > Open...** and navigate to the cloned `AlwaysOnDisplay` directory.
3. Allow Gradle to synchronize dependencies.

### 3. Build & Run via Command Line

#### Compile Kotlin Sources:
```bash
# Windows
.\gradlew.bat compileDebugKotlin

# macOS / Linux
./gradlew compileDebugKotlin
```

#### Assemble Debug APK:
```bash
# Windows
.\gradlew.bat assembleDebug

# macOS / Linux
./gradlew assembleDebug
```
The output APK is generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

#### Assemble Optimized Release APK:
```bash
# Windows
.\gradlew.bat assembleRelease

# macOS / Linux
./gradlew assembleRelease
```

#### Install Directly onto a Connected Device / Emulator:
```bash
# Windows
.\gradlew.bat installDebug

# macOS / Linux
./gradlew installDebug
```

---

## 🔒 Permissions & Security

Always On Display requests minimal local permissions required for overlay display and foreground service operation:

- `android.permission.SYSTEM_ALERT_WINDOW`: Enables displaying customizable clock overlays over other apps and on lock screens.
- `android.permission.FOREGROUND_SERVICE` & `FOREGROUND_SERVICE_SPECIAL_USE`: Maintains a low-priority foreground service to detect screen-off events.
- `android.permission.POST_NOTIFICATIONS`: Prompts for notification permission on Android 13+ (API 33+) for the active service notification.

> [!IMPORTANT]
> Always On Display requires **NO internet permission** (`android.permission.INTERNET`). All clock configurations, battery information, and overlay preferences remain strictly on your physical device.

---

## 🤝 Contributing

Contributions, feedback, and issue reports are welcome!
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/amazing-feature`).
3. Commit your changes (`git commit -m "Add amazing feature"`).
4. Push to the branch (`git push origin feature/amazing-feature`).
5. Open a Pull Request.

---

## 📄 License

This project is open-source and distributed under the terms of the [Apache License 2.0](LICENSE).
