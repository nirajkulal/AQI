package com.app.aqi.data.network

import android.util.Log
import com.app.aqi.data.local.LocalDB
import com.app.aqi.data.local.util.JSONMapper
import com.app.aqi.utils.Constants.TAG
import okhttp3.*
import okio.ByteString
import javax.inject.Inject

class OkHttpAPIImp @Inject constructor(
    val client: OkHttpClient,
    val request: Request,
    val localDB: LocalDB
) : API {

    private var ws: WebSocket? = null

    override fun connectWS() {
        ws = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Log.v(TAG, "onOpen $response")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Log.v(TAG, "onMessage $text")
                try {
                    localDB?.writeAQI(JSONMapper.getCities(text))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                Log.v(TAG, "onMessage $bytes")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Log.v(TAG, "onClosing $reason $code")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Log.v(TAG, "onClosed $reason $code")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Log.v(TAG, "onFailure ${response.toString().orEmpty()}")
                Log.v(TAG, "onFailure ${t.message}")
            }
        })
    }

    override fun disconnectWS() {
        ws?.cancel()
    }
}



