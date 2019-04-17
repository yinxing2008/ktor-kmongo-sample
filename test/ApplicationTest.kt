package io.jkratz.katas

import com.cxyzy.ktor.demo.module
import io.ktor.http.*
import kotlin.test.*
import io.ktor.server.testing.*
import org.litote.kmongo.json

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