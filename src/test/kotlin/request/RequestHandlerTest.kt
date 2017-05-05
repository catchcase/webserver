//import http.HttpMethod
//import app.HttpRequestHandler
//import http.getHeader
//import org.junit.Assert.assertEquals
//import org.junit.Test
//import java.lang.IllegalStateException
//
///*
//package request
//
//import com.nhaarman.mockito_kotlin.doReturn
//import com.nhaarman.mockito_kotlin.mock
//import com.sun.tools.doclets.internal.toolkit.util.DocFinder
//import com.sun.tools.hat.internal.server.HttpReader
//import http.Headers
//import header.HeaderReader
//import http.HttpHeader
//import junit.framework.Assert.assertEquals
//import http.HttpMethod
//import org.junit.Test
//import java.io.InputStream
//
//*/
///**
// * Created by Vincente A. Campisi on 10/04/17.
// */
//
//class HttpRequestHandlerTest {
//
//    @Test(expected = IllegalArgumentException::class)
//    fun receiveBlankRequest() {
//        val requestString = ""
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler()
//        val request = rh.receiveRequest(requestStringAsStream)
//        assertEquals(HttpMethod.UNKNOWN, request.method)
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun receiveInvalidRequest() {
//        val requestString = " GET HTTP/1.1 localhost:60074\r\n" +
//                "content-type:application/json\r\n"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val request = rh.receiveRequest()
//        assertEquals(HttpMethod.GET, request.method)
//    }
//
//    @Test
//    fun receiveValidGetRequest() {
//        val requestString = "GET HTTP/1.1 localhost:60074\r\n" +
//                "content-type:application/json\r\n"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val request = rh.receiveRequest()
//        assertEquals(HttpMethod.GET, request.method)
//        assertEquals(1, request.headers.size)
//        assertEquals("application/json", request.headers.getHeader("content-type"))
//    }
//
//    @Test
//    fun receiveValidPostJsonRequest() {
//        val requestString = "POST HTTP/1.1 localhost:60074\r\n" +
//                "content-type: application/json\r\n" +
//                "content-length: 7\r\n" +
//                "\r\n" +
//                "Example"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val request = rh.receiveRequest()
//        assertEquals(HttpMethod.POST, request.method)
//        assertEquals(2, request.headers.size)
//        assertEquals("application/json", request.headers.getHeader("content-type"))
//        assert(request.body!!.toString(Charsets.ISO_8859_1).compareTo("Example") == 0)
//    }
//
//    @Test
//    fun receiveValidPostBinaryRequest() {
//        val requestString = "POST HTTP/1.1 localhost:60074\r\n" +
//                "content-type: application/octet-stream\r\n" +
//                "content-length: 8\r\n" +
//                "\r\n" +
//                "65467896"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val request = rh.receiveRequest()
//        assertEquals(HttpMethod.POST, request.method)
//        assertEquals(2, request.headers.size)
//        assertEquals("application/octet-stream", request.headers.getHeader("content-type"))
//        assert(request.body!!.toString(Charsets.ISO_8859_1).compareTo("65467896") == 0)
//    }
//
//    @Test(expected = IllegalStateException::class)
//    fun receivePostJsonWrongContentLength() {
//        val requestString = "POST HTTP/1.1 localhost:60074\r\n" +
//                "content-type: application/json\r\n" +
//                "content-length: 8\r\n" +
//                "\r\n" +
//                "Example"
//        val requestStringAsStream = requestString.byteInputStream(Charsets.ISO_8859_1)
//        val rh = HttpRequestHandler(requestStringAsStream)
//        val request = rh.receiveRequest()
//        assertEquals(HttpMethod.POST, request.method)
//        assertEquals(2, request.headers.size)
//        assertEquals("application/json", request.headers.getHeader("content-type"))
//        assert(request.body!!.toString(Charsets.ISO_8859_1).compareTo("Example") == 0)
//    }
//
//}
