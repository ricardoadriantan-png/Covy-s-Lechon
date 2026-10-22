# 📘 API Integration Documentation: Covy's Lechon Android App

This document outlines the standard step-by-step procedure for integrating and configuring external APIs (such as **REST APIs via Retrofit** and **Firebase Cloud Messaging**) within the **Covy's Lechon** Android project.

---

## 🌐 1. Integrating a REST API (OpenStreetMap Nominatim Geocoding Example)

To integrate any external REST API using Retrofit and Gson, follow these 5 core steps:

### Step A: Add Dependencies (`app/build.gradle.kts`)
Open your module-level build file and add Retrofit, Gson converter, and OkHttp logging interceptor:
```kotlin
dependencies {
    // Retrofit & Gson for REST API consumption
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    // OkHttp Logging Interceptor for debugging network requests
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
}
```
*Remember to click **Sync Now** in Android Studio after updating Gradle scripts.*

### Step B: Request Internet Permission (`AndroidManifest.xml`)
Ensure your app has permission to access the internet:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Step C: Create Response Data Models
Create Java data classes to map the JSON response structure. Use Gson annotations (`@SerializedName`) to match JSON keys:
```java
public class NominatimLocation {
    @SerializedName("display_name")
    private String displayName;

    @SerializedName("lat")
    private String latitude;

    @SerializedName("lon")
    private String longitude;

    // Getters
    public String getDisplayName() { return displayName; }
    public String getLatitude() { return latitude; }
    public String getLongitude() { return longitude; }
}
```

### Step D: Define the API Service Interface
Create an interface outlining the HTTP methods (`@GET`, `@POST`, `@Query`, `@Header`, etc.):
```java
public interface NominatimApiService {
    @GET("search")
    Call<List<NominatimLocation>> searchAddress(
            @Query("q") String query,
            @Query("format") String format,
            @Header("User-Agent") String userAgent
    );
}
```

### Step E: Create the Singleton Retrofit Client
Build a centralized Retrofit client with a base URL and Gson converter factory:
```java
public class RetrofitClient {
    private static final String BASE_URL = "https://nominatim.openstreetmap.org/";
    private static Retrofit retrofit = null;

    public static NominatimApiService getNominatimApi() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                            .header("User-Agent", "CovysLechonAndroidApp/1.0")
                            .build()))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NominatimApiService.class);
    }
}
```

---

## 🔥 2. Integrating Firebase Cloud Messaging (FCM) API

To add push notification capabilities via FCM:

### Step A: Add Firebase BoM & Messaging Dependencies (`app/build.gradle.kts`)
```kotlin
dependencies {
    // Firebase BoM & Cloud Messaging
    implementation(platform("com.google.firebase:firebase-bom:35.0.0"))
    implementation("com.google.firebase:firebase-messaging")
}
```

### Step B: Add Notification Permission (`AndroidManifest.xml`)
For Android 13 (API level 33) and above:
```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

### Step C: Create the Messaging Service Class
Extend `FirebaseMessagingService` to handle incoming tokens and messages:
```java
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        // Handle token refresh
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // Build and display notification
    }
}
```

### Step D: Register the Service (`AndroidManifest.xml`)
Inside the `<application>` tag:
```xml
<service
    android:name=".MyFirebaseMessagingService"
    android:exported="false">
    <intent-filter>
        <action android:name="com.google.firebase.MESSAGING_EVENT" />
    </intent-filter>
</service>
```

---

## 📌 Summary Checklist for Adding New APIs
1. **Dependencies**: Add required libraries in `build.gradle.kts`.
2. **Permissions**: Add necessary permissions in `AndroidManifest.xml`.
3. **Data Classes**: Create models for requests/responses.
4. **Service Interface**: Define endpoint methods.
5. **Client/Manager**: Instantiate the API client and execute asynchronous calls in your Activities.
