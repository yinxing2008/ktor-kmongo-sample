package com.cxyzy.ktor.demo.bean

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

class BaseResult(
    val code: Int,
    val message: String
)

data class User(
    @BsonId val id: UUID = UUID.randomUUID(),
    val userName: String,
    val password: String,
    val email: String = ""
)

data class Note(
    @BsonId val id: UUID = UUID.randomUUID(),
    val title: String,
    val content: String
)