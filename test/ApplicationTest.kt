package io.jkratz.katas

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import module
import org.litote.kmongo.json
import java.util.*

class ApplicationTest {

    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/users").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                System.out.println(response.content)
            }
        }
    }


    @Test
    fun testAddData() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/users")
            {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(
                    mapOf(
                        "userName" to "jerry",
                        "password" to "123456",
                        "email" to "test@gmail.com"
                    ).json
                )
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }


}