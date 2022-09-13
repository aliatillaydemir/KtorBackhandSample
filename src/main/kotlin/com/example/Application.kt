package com.example

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(CallLogging){
        routing {
            static {
            //static(remotePath = "assets") {resources("static")}
                resources("static")
            }
            /*static{
                resource("static/facebook.html")
                resource("static/text.txt")
            }*/
            get("/gomis"){
                call.respondText("Gomis goallllll!!!")
            }
            get("/try"){
                call.respondText("we are trying...")
            }
            get("/welcome"){
                val name = call.request.queryParameters["name"]
                call.respondHtml {
                    head {
                        title{ +"Our Custom Title"}
                    }
                    body {
                        if(!name.isNullOrEmpty()){
                            h3 { +"welcome, $name" }
                        }else{
                            h2 { + "welcome mr/mrs Null!" }
                        }

                        p{ +"Current directory is ${System.getProperty("user.id")}" }
                        img(src = "image.jpg")

                    }
                }
            }
        }
    }

}
