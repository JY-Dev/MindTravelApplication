package com.jydev.mindtravelapplication.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.jydev.mindtravelapplication.R
import com.jydev.mindtravelapplication.ui.main.MainActivity

class FirebaseMessagingService : FirebaseMessagingService() {
    private val gson = Gson()
    override fun onMessageReceived(message: RemoteMessage) {
        message.data.let {
            val payload = it[PAYLOAD]?.let {json ->
                gson.fromJson(json,FcmPayLoad::class.java)
            } ?: return
            val data = it[DATA]?:""
            val intent = payload.fcmService.getIntent(this,payload,data)
            sendNotification(payload.title,payload.content,intent)
        }
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM TOKEN","FCM TOKEN : $token")
    }

    private fun sendNotification(title : String, content : String, intent : Intent) {
        val stackBuilder = TaskStackBuilder.create(this).apply {
            addNextIntent(Intent(this@FirebaseMessagingService,MainActivity::class.java))
            addNextIntent(intent)
        }
        val pendingIntent: PendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_IMMUTABLE)
        val channelId = "fcm_channel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.clover)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        val channel = NotificationChannel(
            channelId,
            "Channel human readable title",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    companion object{
        const val PAYLOAD = "payload"
        const val DATA = "data"
    }
}