package flickrapp.com.data.api.deserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeDeserializer : JsonDeserializer<LocalDateTime> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime {
        val dateTimeString = json?.asString

        return try {
            return parseFromZoneOffset(dateTimeString)
        } catch (e: Exception) {
            parseFromTimeZoneName(dateTimeString)
        }
    }

    private fun parseFromZoneOffset (dateTimeString: String?): LocalDateTime {
        return LocalDateTime.parse(
            dateTimeString,
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")
        )
    }

    private fun parseFromTimeZoneName(dateTimeString: String?): LocalDateTime {
        return LocalDateTime.parse(
            dateTimeString,
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz")
        )
    }
}
