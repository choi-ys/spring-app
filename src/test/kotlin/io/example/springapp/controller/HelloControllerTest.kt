package io.example.springapp.controller

import io.example.springapp.domain.dto.message.Message
import io.example.springapp.domain.dto.message.Message.Companion.currentVersion
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author : choi-ys
 * @date : 2022-02-25 오전 12:34
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc
@DisplayName("API:Hello")
internal class HelloControllerTest(
    private val mockMvc: MockMvc,
) {

    companion object {
        const val HELLO_API_URL = "/hello"
    }

    @Test
    @DisplayName("[200:GET]요청에 해당하는 메시지를 포맷팅하여 반환한다.")
    fun givenRequestMessage_whenFormattingMessage_thenReturnMessageTemplate() {
        // Given
        val requestMessage = "sample message"
        val messageTemplate = Message.formatMessage(requestMessage)

        // When
        val resultActions = this.mockMvc.perform(get(HELLO_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .param("message", requestMessage)
        )

        // Then
        resultActions.andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.messageTemplate").value(messageTemplate))
            .andExpect(jsonPath("$.version").value(currentVersion()))
            .andExpect(jsonPath("$.createdAt").exists())
    }
}