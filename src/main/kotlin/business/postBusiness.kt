package business

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import entity.FullParameters
import entity.HttpResponse
import entity.PostEntity
import infra.EndPointConstant
import infra.OperationMethod
import repository.PostRepository

class PostBusiness () {

    fun getAllPosts(): List<PostEntity> {

        val url: String = EndPointConstant.BASE.URL + EndPointConstant.POST.ALL_POST

        val fullParameters: FullParameters = FullParameters(url, OperationMethod.GET)
        val response: HttpResponse = PostRepository.getAllPosts(fullParameters)
        return Gson().fromJson<List<PostEntity>>(response.jsonResponse, object : TypeToken<List<PostEntity>>() {}.type)
        //return response
    }

    fun getSinglePost(id: Int): PostEntity {
        val url: String = EndPointConstant.BASE.URL + EndPointConstant.POST.SINGLE_POST
        val fullParameters: FullParameters = FullParameters(url, OperationMethod.GET, mapOf(Pair("id", id.toString())))

        val response: HttpResponse = PostRepository.getSinglePost(fullParameters)
        return Gson().fromJson<List<PostEntity>>(response.jsonResponse, object : TypeToken<List<PostEntity>>() {}.type)[0]
    }
}