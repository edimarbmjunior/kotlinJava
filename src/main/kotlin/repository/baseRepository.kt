package repository

import entity.FullParameters
import entity.HttpResponse
import org.jetbrains.io.response
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class BaseRepository () {
    fun execute(fullParameters: FullParameters): HttpResponse{
        val conn: HttpURLConnection
        val response: HttpResponse
        val url: URL = URL(fullParameters.url + getQuery(fullParameters.parameters))

        conn = url.openConnection() as HttpURLConnection
        conn.readTimeout = 100000
        conn.connectTimeout = 120000
        conn.requestMethod = fullParameters.method.toString()

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencode")
        conn.setRequestProperty("charset", "utf-8")

        conn.useCaches = false

        //Faz a requisição fisicamente
        conn.connect()

        if(conn.responseCode == 404){
            response = HttpResponse(conn.responseCode, "")
        }else{
            val inputStream: InputStream = conn.inputStream
            response = HttpResponse(conn.responseCode, getStringFromInputStrem(inputStream))
        }

        return response
    }

    fun getStringFromInputStrem(inputStream: InputStream):String{
        try {
            val strBuilder: StringBuilder = StringBuilder()
            val br: BufferedReader = BufferedReader(InputStreamReader(inputStream))

            for(line in br.readLine()){
                strBuilder.append(line);
            }

            return strBuilder.toString()

        }catch (e: Exception){
            return ""
        }
    }

    fun getQuery(parameters: Map<String, String>): String{
        //https://jsonplaceholder.typicode.com/post?id=10&size=10

        if(parameters.isEmpty()) return ""

        val result: StringBuilder = StringBuilder()
        var first: Boolean = true

        for(param in parameters){
            if(first){
                result.append("?")
                first = false
            }else{
                result.append("&")
            }

            result.append(URLEncoder.encode(param.key), "UTF-8")
            result.append("=")
            result.append(URLEncoder.encode(param.value), "UTF-8")
        }

        return result.toString()
    }
}