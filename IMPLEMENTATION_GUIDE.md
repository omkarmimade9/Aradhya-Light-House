# Aradhya Light House - Implementation Guide

## Quick Start for Android Development

This guide provides step-by-step instructions to complete the Android application development.

## Project Files Already Created

1. **build.gradle** - Root level build configuration
2. **settings.gradle** - Project structure and dependencies configuration
3. **AndroidManifest.xml** - App permissions and activity declarations
4. **Part.kt** - Data entity for electrical parts/lighting products
5. **README.md** - Project overview

## Remaining Development Tasks

### 1. Create Data Layer

#### Customer Entity (app/src/main/kotlin/com/aradhya/lighthouse/data/entity/Customer.kt)
```kotlin
@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val email: String = "",
    val address: String = ""
)
```

#### Purchase Entity (app/src/main/kotlin/com/aradhya/lighthouse/data/entity/Purchase.kt)
```kotlin
@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val customerId: Int,
    val partId: Int,
    val quantity: Int,
    val totalAmount: Double,
    val purchaseDate: Long
)
```

#### Database (app/src/main/kotlin/com/aradhya/lighthouse/data/local/AppDatabase.kt)
```kotlin
@Database(
    entities = [Part::class, Customer::class, Purchase::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun partDao(): PartDao
    abstract fun customerDao(): CustomerDao
    abstract fun purchaseDao(): PurchaseDao
}
```

### 2. Create DAOs (Data Access Objects)

#### PartDao (app/src/main/kotlin/com/aradhya/lighthouse/data/local/dao/PartDao.kt)
```kotlin
@Dao
interface PartDao {
    @Insert
    suspend fun insert(part: Part)
    
    @Update
    suspend fun update(part: Part)
    
    @Delete
    suspend fun delete(part: Part)
    
    @Query("SELECT * FROM parts")
    suspend fun getAllParts(): List<Part>
    
    @Query("SELECT * FROM parts WHERE id = :id")
    suspend fun getPartById(id: Int): Part?
}
```

### 3. Create Repositories

#### PartRepository
```kotlin
class PartRepository(private val partDao: PartDao) {
    suspend fun addPart(part: Part) = partDao.insert(part)
    suspend fun updatePart(part: Part) = partDao.update(part)
    suspend fun deletePart(part: Part) = partDao.delete(part)
    suspend fun getAllParts() = partDao.getAllParts()
    suspend fun getPartById(id: Int) = partDao.getPartById(id)
}
```

### 4. Create ViewModels

#### PartViewModel
```kotlin
class PartViewModel(private val repository: PartRepository) : ViewModel() {
    private val _parts = MutableLiveData<List<Part>>()
    val parts: LiveData<List<Part>> = _parts
    
    fun loadParts() {
        viewModelScope.launch {
            _parts.value = repository.getAllParts()
        }
    }
    
    fun addPart(part: Part) {
        viewModelScope.launch {
            repository.addPart(part)
            loadParts()
        }
    }
}
```

### 5. Create UI Screens with Jetpack Compose

#### MainActivity (app/src/main/kotlin/com/aradhya/lighthouse/presentation/MainActivity.kt)
```kotlin
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AradhyaLightHouseTheme {
                Navigation()
            }
        }
    }
}
```

#### Part List Screen
- Display all electrical parts in a LazyColumn
- Show part name, price, company, and stock quantity
- Implement search and filter functionality
- Add FAB button for adding new parts

#### Add/Edit Part Screen
- Form with input fields for part details
- Validation for mandatory fields
- Save and Cancel buttons

#### Purchase Screen
- Select customer from list
- Add parts to purchase cart
- Calculate total amount
- Display order summary

### 6. SMS Manager Implementation

#### SMSManager (app/src/main/kotlin/com/aradhya/lighthouse/utils/SMSManager.kt)
```kotlin
class SMSManager(private val context: Context) {
    fun sendPurchaseSMS(phoneNumber: String, purchaseDetails: String) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            val message = "Aradhya Light House Purchase: $purchaseDetails"
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
```

### 7. Dependencies to Add

Add to app/build.gradle:
```gradle
dependencies {
    // Room Database
    implementation 'androidx.room:room-runtime:2.5.1'
    kapt 'androidx.room:room-compiler:2.5.1'
    
    // Jetpack Compose
    implementation 'androidx.compose.ui:ui:1.5.0'
    implementation 'androidx.compose.material:material:1.5.0'
    
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1'
    
    // ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.1'
    
    // Dependency Injection (Hilt)
    implementation 'com.google.dagger:hilt-android:2.46'
    kapt 'com.google.dagger:hilt-compiler:2.46'
}
```

## Building and Running

1. Open the project in Android Studio
2. Sync Gradle files
3. Connect a device or start an emulator
4. Run: `./gradlew installDebug` or click Run in Android Studio

## Testing

1. Unit tests for repositories and ViewModels
2. Integration tests for database operations
3. UI tests for Compose screens

## Next Steps

1. Complete the repository and DAO implementations
2. Build UI screens with Jetpack Compose
3. Implement SMS sending functionality
4. Add proper error handling and validation
5. Create comprehensive unit and integration tests
6. Add app theme and styling

## Troubleshooting

- **Gradle sync fails**: Check Java version compatibility
- **Compose errors**: Ensure Compose compiler is updated
- **SMS permission denied**: Check AndroidManifest.xml permissions
- **Database errors**: Verify Room annotations and entity definitions
