# Ankara Yolculuk Music

Ankara yolculuğuna özel gerçek müzik verileri sunan modern Android uygulaması.

## Teknolojiler

- **Kotlin** - Modern programlama dili
- **Jetpack Compose** - Modern UI toolkit
- **MVVM Architecture** - Clean architecture pattern
- **Retrofit** - HTTP client
- **Moshi** - JSON serialization
- **Coil** - Image loading library
- **Coroutines & Flow** - Asynchronous operations
- **Spotify Web API** - Real music data source

## Özellikler

### 🎵 Müzik Entegrasyonu
- Gerçek Spotify API ile entegrasyon
- Dinamik şarkı listeleri (Ankara, Bozkır, Yolculuk konseptleri)
- Albüm kapakları ve sanatçı bilgileri

### 🎨 Görsel Tasarım
- Material 3 tasarım prensipleri
- Responsive ve modern arayüz
- Animasyonlu geçişler

### 📱 Kapsamlı Uygulama Özellikleri
- **Kategorize Edilmiş Listeler**:
  - Ankara Havaları
  - Gece Sürüşü - Deep House
  - Yol Hikayeleri - Akustik
  - Bozkır Müzikleri

- **Track Details**: Şarkı detayları ve sanatçı bilgileri
- **Loading & Error Handling**: Asenkron veri çekme
- **Navigation**: NavHost ile screen geçişleri

## Kurulum

### 1. Repository'yi Klonlayın
```bash
git clone https://github.com/username/ankara-yolculuk-music.git
cd ankara-yolculuk-music
```

### 2. API Key Oluşturun
- [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/) adresinden uygulama oluşturun
- Client ID ve Client Secret alın
- Access Token oluşturun (Spotify API docs referans alın)

### 3. Local Properties Ayarı
`local.properties` dosyasına API key'inizi ekleyin:

```properties
# local.properties file
SPOTIFY_API_KEY=your_spotify_api_key_here
```

### 4. Projeyi Çalıştırın
Android Studio'da projeyi açın ve bir emülatörde veya gerçek cihazda çalıştırın.

## Build ve Test

### Debug APK Oluşturma
```bash
./gradlew assembleDebug
```

### Unit Testler
```bash
./gradlew testDebugUnitTest
```

### UI Testler
```bash
./gradlew connectedDebugAndroidTest
```

## GitHub Workflow

Proje otomatik build için GitHub Actions kullanmaktadır. `.github/workflows/main.yml` dosyası:
- JDK 17 kullanımı
- Gradle cache ile hızlı build
- Debug APK oluşturma
- APK'yı GitHub Artifacts olarak sunma

## Proje Yapısı

```
app/src/main/java/com/ankarayolculukmusic/
├── data/
│   ├── api/             # Retrofit API service
│   ├── models/          # Data models (Spotify API)
│   └── repository/      # Data repository
├── ui/
│   ├── components/      # Reusable UI components
│   ├── screens/         # Screen components
│   ├── viewmodels/      # ViewModels
│   └── navigation/      # Navigation setup
└── MainActivity.kt      # Entry point
```

## API Kullanımı

Spotify Web API v1 kullanılır. Ana endpointler:
- `/search` - Track arama
- `/tracks/{id}` - Track detayları

## Lisans

MIT License
