package cesa.enterprise.mock

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.content.*
import io.ktor.http.content.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
        post("/message") {
            val f = File.createTempFile("req",".xml")
            Files.copy(call.receiveStream(), Paths.get("request ${System.currentTimeMillis()}.xml"))
            call.respondFile(File("response.xml"))
        }
        // Static feature. Try to access `/static/ktor_logo.svg`
        static("/static") {
            resources("static")
        }
    }
}

