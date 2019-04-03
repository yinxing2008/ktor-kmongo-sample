# ktor-kmongo-sample
Sample application using Kotlin, Ktor, and KMongo.


It's a wonderful combination for build full reactive stack.
Compared to Springboot WebFlux, it's easier to use Ktor.
Usage:
1. Start local mongodb. In case of port other than 27017, change it in Application.kt`"mongodb://127.0.0.1:27017"`
2. In the sample, demo is the db name is demo and users is the collection name, which you can change in UserController.kt.
3. Insert Data
Use tools such as PostMan to send POST request
a) service url: http://localhost:8080/users/add
b) data sample:
```
{
  "userName" : "jerry",
  "password" : "123456",
  "email" : "test2@gmail.com"
}
```
c) request type should be json
![](https://upload-images.jianshu.io/upload_images/6169789-f160422b8f51d291.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/6169789-8ba4bcce8df2c8fc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
4. query data with GET request type
service url: http://localhost:8080/users/list
![](https://upload-images.jianshu.io/upload_images/6169789-2aec7cdb7ed58980.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
5. Also you can use testAddData and testList in code of ApplicationTest to insert and query data.
```
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
```

Blog: [https://www.jianshu.com/p/8aeb32f81f10](https://www.jianshu.com/p/8aeb32f81f10)

This sample project is derived from following project:
https://github.com/jkratz55/kotlin-ktor-mongo-sample