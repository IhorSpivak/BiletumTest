package com.example.biletum.application

import android.app.NotificationManager
import android.R
import androidx.core.app.NotificationCompat
import android.media.RingtoneManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.biletum.view.profile.events.events_list.MainActivity
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        sendMyNotification(message.notification!!.body)
    }


    private fun sendMyNotification(message: String?) {

        //On click of notification it redirect to this Activity
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.sym_def_app_icon)
            .setContentTitle("My Firebase Push notification")
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }
}