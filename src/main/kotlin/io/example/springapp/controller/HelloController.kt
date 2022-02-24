package io.example.springapp.controller

import io.example.springapp.domain.dto.message.Message
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author : choi-ys
 * @date : 2022-02-25 오전 12:32
 */

private val logger = LoggerFactory.getLogger(HelloController::class.java)

@RestController
@RequestMapping("hello"
)
class HelloController {

    @GetMapping
    fun hello(@RequestParam("message") message: String) =
        ResponseEntity.ok(Message.of(message)).apply {
            logger.info("[{}][{}]", this.statusCode, this.body)
        }
}