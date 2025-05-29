import java.util.UUID

data class MoodEntry(
    val id: UUID = UUID.randomUUID(),
    val date: String = "",
    val mood: String = "",
    val note: String = "",
    val category: String = "",
    val sleptWell: Boolean = false,
    val rating: Float = 3f,
    val isImportant: Boolean = false
)