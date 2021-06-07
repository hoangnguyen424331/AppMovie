@file:Suppress("DEPRECATION")

package com.example.appmovie.data.source.remote.fetchjson

import android.os.AsyncTask
import com.example.appmovie.data.source.remote.OnFetchDataJsonListener
import com.example.appmovie.utils.KeyEntityType
import org.json.JSONObject

@Suppress("UNCHECKED_CAST")
class GetJsonFromUrl<T>(

    private val listener: OnFetchDataJsonListener<T>,
    private val keyEntityType: KeyEntityType
) : AsyncTask<String?, Void?, String?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String {
        var data = ""
        try {
            val parseDataWithJson = ParseDataWithJson()
            data = parseDataWithJson.getJsonFromUrl(params[0]).toString()
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(data: String?) {
        super.onPostExecute(data)
        if (data != null && data.isNotBlank()) {
            val jsonObject = JSONObject(data)
            listener.onSuccess(ParseDataWithJson().parseJsonToData(jsonObject, keyEntityType) as T)
        } else
            listener.onError(exception)
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null && result.isNotBlank()) {
            val jsonObject = JSONObject(result)
            listener.onSuccess(ParseDataWithJson().parseJsonToData(jsonObject, keyEntityType) as T)
        } else
            exception?.let { listener.onError(it) }
    }
}
