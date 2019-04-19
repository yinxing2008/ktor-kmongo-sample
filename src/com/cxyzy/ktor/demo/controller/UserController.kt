package com.cxyzy.ktor.demo.controller

import com.cxyzy.ktor.demo.bean.BaseResult
import com.cxyzy.ktor.demo.bean.User
import com.cxyzy.ktor.demo.dbName
import com.cxyzy.ktor.demo.request.RegisterRequest
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Route.userRoutes() {

    val logger: Logger = LoggerFactory.getLogger("UserController")
    val client: CoroutineClient by inject()


    val collectionName = "users"

    route("/users") {

        get("/list") {
            val users = client.getDatabase(dbName)
                .getCollection<User>(collectionName)
                .find()
                .toList()
            call.respond(HttpStatusCode.OK, users)
        }

        post<RegisterRequest>("/register") { request ->
            val user = User(
                userName = request.userName,
                password = request.password
            )
            client.getDatabase(dbName)
                .getCollection<User>(collectionName)
                .insertOne(user)
            val result = BaseResult(100,"ok")
            call.respond(HttpStatusCode.OK,result)
        }
    }
}



