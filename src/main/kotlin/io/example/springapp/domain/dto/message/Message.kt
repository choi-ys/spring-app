package io.example.springapp.domain.dto.message

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

/**
 * @author : choi-ys
 * @date : 2022-02-25 오전 12:46
 */
data class Message(
    val id: String = UUID.randomUUID().toString(),
    val messageTemplate: String,
    val version: Int = VERSION,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'hh:mm:ss",
        timezone = "Asia/Seoul"
    )
    val createdAt: LocalDateTime = LocalDateTime.now(),
) {
    companion object {
        private const val MESSAGE_PREFIX = "hello"
        private const val VERSION = 1

        fun formatMessage(message: String) =
            String.format("%s %s", MESSAGE_PREFIX, message)

        fun of(message: String): Message {
            return Message(
                messageTemplate = formatMessage(message)
            )
        }

        fun currentVersion() = VERSION
    }
}
