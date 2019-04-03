package io.jkratz.katas

import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import module
import org.litote.kmongo.json
import java.util.*

class ApplicationTest {

    @Test
    fun testAddData() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/users/add")
            {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(
                    mapOf(
                        "userName" to "jerry5",
                        "password" to "123456",
                        "email" to "test@gmail.com"
                    ).json
                )
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }


    @Test
    fun testList() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/users/list").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                System.out.println(response.content)
            }
        }
    }

}