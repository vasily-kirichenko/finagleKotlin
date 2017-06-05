import com.twitter.finagle.*
import com.twitter.finagle.Http
import com.twitter.finagle.http.*
import com.twitter.finagle.http.Status
import com.twitter.util.Await
import com.twitter.util.Future

fun main(args: Array<String>) {
    val service =
        object : Service<Request, Response>() {
            override fun apply(req: Request): Future<Response> {
                val response = Response.apply(req.version(), Status.Ok())
                response.contentString = "foo"
                return Future.value(
                    response
                )
            }
        }

    val server = Http.serve(":8080", service)
    Await.ready(server)
}