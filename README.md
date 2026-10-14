# 🍗 Covy's Lechon Android Application

Welcome to the **Covy's Lechon** official Android mobile application! This app provides customers with a seamless ordering, queue tracking, and profile management experience for authentic Lechon Manok.

---

## ✨ Key Features & Screens

1. **Splash Screen (`splashscreen.java`)**:
   - Engaging startup animation leading into the application.
2. **Authentication Flow (`SignInActivity.java` & `CreateAccount.java`)**:
   - Secure email and password authentication powered by **Firebase Authentication**.
   - Custom user registration capturing customer display names and caching them locally via `SharedPreferences`.
3. **Interactive Dashboard (`DashBoardActivity.java`)**:
   - Personalized greeting banner dynamically displaying the registered user's name (`"WELCOME, [USER NAME]!"`).
   - Order verification card and real-time **Queue Number Tracking** (`#Q-042`) with interactive order placement dialogs.
   - Delivery status progress and loyalty points rewards tracker.
4. **Menu Screen (`MenuActivity.java` & `menu.xml`)**:
   - Complete food menu featuring **Lechon Manok (Whole)**, **Lechon Manok (Half)** with blue border highlight, **Paa**, **Wings**, and **Chicken Inasal**.
   - "Add to Order" action buttons with toast feedback.
5. **Profile Screen (`ProfileActivity.java` & `profile.xml`)**:
   - Account overview displaying customer name, personal information, settings, and secure Firebase sign-out.
6. **Bottom Navigation Bar**:
   - Unified cream-themed bottom navigation bar (`#FFF8EC`) with soft orange active pills (`#FFE8C5`) allowing instant switching between **Home**, **Menu**, **Track**, and **Profile**.
7. **OpenStreetMap Nominatim Geocoding API**:
   - Integrated **Retrofit**, **Gson**, and **OkHttp** for forward and reverse address geocoding.
8. **Firebase Cloud Messaging (FCM)**:
   - Configured `MyFirebaseMessagingService` for real-time push notifications regarding order status and queue updates.

---

## 🛠️ Tech Stack & Libraries

- **Language**: Java / Android SDK (Min SDK 24, Target SDK 37)
- **UI Architecture**: XML Layouts, Material 3 Design (`com.google.android.material`), ConstraintLayout
- **Backend & Auth**: Firebase Authentication (BoM 35.0.0) & Firebase Cloud Messaging
- **Networking**: Retrofit 2.9.0, Gson Converter, OkHttp 4.x Logging Interceptor

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/ricardoadriantan-png/Covy-s-Lechon.git
   ```
2. Open the project in **Android Studio**.
3. Ensure your `google-services.json` file is placed in the `app/` directory.
4. Run the project on an Android emulator or physical device.

---

## 🔗 Repository
- **GitHub**: [https://github.com/ricardoadriantan-png/Covy-s-Lechon.git](https://github.com/ricardoadriantan-png/Covy-s-Lechon.git)
