// UserEntity.kt
import androidx.room.Entity

@Entity(tableName = "users")
class UserEntity(
    val name: String,
    val email: String
) : BaseEntity()
