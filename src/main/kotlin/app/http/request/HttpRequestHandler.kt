package app.http.request

import app.http.HttpHeader
import app.http.HttpMethod
import app.http.getHeader
import core.Request
import core.RequestHandler
import java.net.Socket

/**
 * Created by Vincente A. Campisi on 03/04/17.
 */
class HttpRequestHandler : RequestHandler {

    override fun receiveRequest(socket: Socket): HttpRequest {
        val requestReader = HttpRequestReader(socket.getInputStream())
        val request = HttpRequest(requestReader.readMethod(), requestReader.readHeaders())

        if (request.method == HttpMethod.POST) {
            try {
                receiveRequestBody(socket, request.headers.getHeader("content-length").toInt(), request)
            } catch (nfe: NumberFormatException) {
                throw IllegalArgumentException("Invalid header format.")
            }
        }
        return request
    }

    private fun receiveRequestBody(socket: Socket, contentLength: Int, httpRequest: HttpRequest) {
        val requestReader = HttpRequestReader(socket.getInputStream())
        if (contentLength <= 0) {
            throw IllegalStateException("Invalid content-length header.")
        }

        val requestBody = ByteArray(contentLength)
        val bytes = requestReader.readBytes(contentLength, requestBody)

        if (bytes != contentLength) {
            throw IllegalStateException("Content Length does not match number of bytes read.")
        }

        httpRequest.body = requestBody
    }
}

class HttpRequest(val method: HttpMethod, val headers: List<HttpHeader>, var body: ByteArray? = null) : Request