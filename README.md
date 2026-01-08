# Aradhya Light House - Android Application

An Android application for managing electrical parts and lighting store inventory. This app provides a complete solution for store management with features for product catalog, CRUD operations, customer purchase list, and SMS notifications.

## Features

- **Product Listing**: Display all electrical parts and lights with their details
  - Product name
  - Price
  - Company name
  - Category
  - Stock quantity

- **CRUD Operations**:
  - Create: Add new products to the inventory
  - Read: View product details
  - Update: Edit product information
  - Delete: Remove products from inventory

- **Customer Purchase Management**:
  - Track customer purchases
  - View purchase history
  - Maintain customer contact information

- **SMS Notifications**:
  - Send purchase confirmation to customer's mobile number
  - Automated SMS with purchase details
  - Invoice details via SMS

## Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM with Room Database
- **Database**: SQLite (Room)
- **UI**: Jetpack Compose
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 33 (Android 13)

## Project Structure

```
app/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   │   ├── PartDao.kt
│   │   │   ├── CustomerDao.kt
│   │   │   └── PurchaseDao.kt
│   │   ├── entity/
│   │   │   ├── Part.kt
│   │   │   ├── Customer.kt
│   │   │   └── Purchase.kt
│   │   └── AppDatabase.kt
│   ├── repository/
│   │   ├── PartRepository.kt
│   │   ├── CustomerRepository.kt
│   │   └── PurchaseRepository.kt
│   └── model/
│       └── (API models if needed)
├── domain/
│   └── usecase/
│       ├── PartUseCase.kt
│       ├── CustomerUseCase.kt
│       └── PurchaseUseCase.kt
├── presentation/
│   ├── ui/
│   │   ├── screens/
│   │   │   ├── PartListScreen.kt
│   │   │   ├── PartDetailScreen.kt
│   │   │   ├── AddPartScreen.kt
│   │   │   ├── CustomerListScreen.kt
│   │   │   └── PurchaseScreen.kt
│   │   └── composables/
│   │       └── (Reusable UI components)
│   ├── viewmodel/
│   │   ├── PartViewModel.kt
│   │   ├── CustomerViewModel.kt
│   │   └── PurchaseViewModel.kt
│   └── MainActivity.kt
├── utils/
│   ├── SMSManager.kt
│   └── Constants.kt
└── AndroidManifest.xml
```

## Installation

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Build and run the app on an emulator or device

## Dependencies

- AndroidX libraries
- Jetpack Compose
- Room Database
- Kotlin Coroutines
- Android SMS Manager for SMS functionality

## Usage

1. **Add Products**: Navigate to the Add Product screen and fill in the details
2. **View Products**: Browse the product list with filtering options
3. **Edit Products**: Click on a product and update its information
4. **Manage Purchases**: Create purchase orders and select products
5. **Send SMS**: Automatically send purchase confirmations to customer numbers

## Permissions

```xml
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

## Contributing

Feel free to fork this project and submit pull requests for any improvements.

## License

MIT License - See LICENSE file for details

## Author

Developed for Aradhya Light House Store Management System
