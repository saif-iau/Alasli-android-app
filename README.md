# Alasli-android-app
# Alasli - Tablet App with Side Navigation

A modern Android tablet application built with Jetpack Compose, featuring a side navigation bar and multiple screens optimized for landscape orientation.

## Features

- ✅ Side Navigation Bar (240dp wide)
- ✅ Multiple Pages: Home, Table, Create, Profile, Settings
- ✅ Landscape-only orientation (locked)
- ✅ Material 3 Design
- ✅ Tablet-optimized UI
- ✅ Clean architecture with Navigation Component

## Project Structure

```
com.example.alasli/
├── MainActivity.kt
├── ui/
│   ├── navigation/
│   │   ├── Screen.kt              # Navigation routes
│   │   ├── SideNavigationBar.kt   # Side nav component
│   │   └── AppNavigation.kt       # Main navigation setup
│   ├── screens/
│   │   ├── HomeScreen.kt          # Dashboard with cards
│   │   ├── TableScreen.kt         # Data table view
│   │   ├── CreateScreen.kt        # Form to create items
│   │   ├── ProfileScreen.kt       # User profile
│   │   └── SettingsScreen.kt      # App settings
│   └── theme/
│       └── AlasliTheme.kt
```

## Screens Overview

### 1. Home Screen
- Dashboard with summary cards
- Shows total items, active, and pending counts

### 2. Table Screen
- Interactive data table
- Edit and delete actions for each row
- Status chips with color coding
- Scrollable list view

### 3. Create Screen
- Form with text inputs
- Dropdown for status selection
- Create and Cancel buttons
- Input validation

### 4. Profile Screen
- User information display
- Edit profile button
- Icon-based information rows

### 5. Settings Screen
- Toggle switches for preferences
- Notifications, Dark Mode, Auto Save
- Version information

## Key Components

### Side Navigation Bar
- Fixed 240dp width
- Icons + text labels
- Active state highlighting
- Settings pinned to bottom

### Navigation
- Uses Jetpack Navigation Compose
- State preservation on navigation
- Single top launch mode to prevent stack buildup

## Setup Instructions

1. **Copy the files to your project:**
   - Place `MainActivity.kt` in `src/main/java/com/example/alasli/`
   - Create folders for `navigation/` and `screens/` as shown in structure
   - Update `AndroidManifest.xml` to lock landscape orientation
   - Update `build.gradle.kts` with navigation dependencies

2. **Required Dependencies:**
   ```kotlin
   implementation("androidx.navigation:navigation-compose:2.7.6")
   implementation("androidx.compose.material:material-icons-extended:1.5.4")
   ```

3. **AndroidManifest.xml:**
   Add to MainActivity:
   ```xml
   android:screenOrientation="landscape"
   ```

4. **Build and Run:**
   - Sync Gradle
   - Build project
   - Run on tablet or emulator (landscape mode)

## Customization

### Adding New Pages
1. Create new screen in `ui/screens/`
2. Add route in `Screen.kt`
3. Add navigation item in `SideNavigationBar.kt`
4. Add composable in `AppNavigation.kt`

### Changing Colors/Theme
- Modify `AlasliTheme.kt` for color schemes
- Material 3 dynamic theming supported

### Adjusting Layout
- Side nav width: Change `width(240.dp)` in `SideNavigationBar.kt`
- Card spacing and padding can be adjusted in individual screens

## Requirements

- minSdk: 24
- targetSdk: 34
- Kotlin 1.9+
- Compose BOM 2023.08.00+

## Notes

- Optimized for 10"+ tablets
- Works best in landscape orientation
- All screens are responsive to different tablet sizes
- Material 3 design system for consistent UI
