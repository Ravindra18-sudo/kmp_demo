# KMP Calculator (Compose Multiplatform)

This repository contains a Kotlin Multiplatform (KMP) calculator project using Compose Multiplatform UI with a clean architecture setup.

## Architecture

The app is organized into layers inside `composeApp/src/commonMain/kotlin/com/example/kmpcalculator`:

- `data/`
  - `repository/CalculatorRepositoryImpl.kt`  
    Contains expression parsing/evaluation logic.
- `domain/`
  - `model/CalculationResult.kt`
  - `repository/CalculatorRepository.kt`
  - `usecase/`  
    Independent use cases (`AppendInput`, `DeleteInput`, `ClearInput`, `EvaluateExpression`).
- `presentation/`
  - `action/CalculatorAction.kt`
  - `state/CalculatorUiState.kt`
  - `viewmodel/CalculatorViewModel.kt`
  - `screen/CalculatorScreen.kt`
  - `component/CalculatorButton.kt`
- `ui/theme/`
  - `Color.kt` (global color palette)
  - `Theme.kt` (global app theme)
  - `Type.kt` (typography)
- `di/AppModule.kt`
  - Centralized object graph and ViewModel creation.

## Global Theme and Color Access

- Colors are defined in `ui/theme/Color.kt` via the `AppColors` object.
- Theme is defined in `ui/theme/Theme.kt` via `CalculatorTheme`.
- The current Material color scheme is globally accessible via `AppTheme.colors`.

## Run Android

```bash
./gradlew :composeApp:assembleDebug
```

## Run tests

```bash
./gradlew :composeApp:allTests
```

> Note: On CI or local machines, make sure Android SDK is configured (`ANDROID_HOME` or `local.properties` with `sdk.dir=...`) because Android unit tests are part of `allTests`.