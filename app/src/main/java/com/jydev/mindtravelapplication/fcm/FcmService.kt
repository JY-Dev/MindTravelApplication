package com.jydev.mindtravelapplication.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.jydev.mindtravelapplication.R

class FcmService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        message.notification?.let {
            sendNotification(it.body ?:"Body 없음")
        }
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM TOKEN","FCM TOKEN : $token")
    }

    private fun sendNotification(messageBody: String) {

        val channelId = "fcm_channel"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.clover)
            .setContentTitle("테스트")
            .setContentText(messageBody)
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
}