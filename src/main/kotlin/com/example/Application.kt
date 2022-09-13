package com.example

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.Serializable


fun main(){
    embeddedServer(Netty, port = 8080){
        install(ContentNegotiation){
            json()
        }
        module()
    }.start(wait = true)
}

fun Application.module(){
    install(Routing){
        get("/"){
            call.respondText("Ni Hao bebecik")
        }
        get ("/users/{username}"){
            val username = call.parameters["username"]
            val header = call.request.headers["Connection"]
            if(username == "Admin"){
                call.response.header(name = "Custom-Header","Admin")
                call.respond(message = "Hii Admin", status = HttpStatusCode.OK)
            }
            call.respondText("hiiii: $username with header: $header")
        }
        get("/user"){
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]
            call.respondText("Hiii my name is $name, I'm $age years old.")
        }
        get("/person"){
            try{
                val person = Person("Ali",31)
                call.respond(message = person, status = HttpStatusCode.OK)
            }catch (e:Exception){
                call.respond(message = "${e.message}", status = HttpStatusCode.BadRequest)
            }

        }
        get("/redirect") {
            call.respondRedirect("/moved", permanent = false)
        }
        get("/moved") {
            call.respondText("you did it!!!")
        }
    }
    }

@Serializable
data class Person(
    val name: String,
    val age: Int
)
