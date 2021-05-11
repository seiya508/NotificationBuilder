package com.example.notificationbuilder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //通知設定
        var notificationId = 1  //IDは同一にして通知を上書きする
        // The channel ID of the notification.
        var channel_id_pattern1 = "channel_01"
        var channel_id_pattern2 = "channel_02"

        // Notification channel ID is ignored for Android 7.1.1
        // (API level 25) and lower.
        // 特に, 通知重要度については, Android 7.1 以前に準拠
        var notificationBuilderPattern1 = NotificationCompat.Builder(this, channel_id_pattern1)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("パターン1")
                .setContentText("通知テスト")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)   /// 通知の優先度(緊急)
                // 通知の振動設定
                // 単位はミリ秒
                //
                // 要素1 : 振動開始までの時間
                // 要素2 : 振動
                // 要素3 : 休止
                // 要素4 : (これ以降は要素2-3の繰り返し)
                .setVibrate(longArrayOf(0, 200, 25, 200, 25, 1000))

        var notificationBuilderPattern2 = NotificationCompat.Builder(this, channel_id_pattern2)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("パターン2")
                .setContentText("通知テスト")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)   // 通知の優先度(高)
                .setVibrate(longArrayOf(0, 100, 100, 100, 100, 100))  // 通知の振動設定

        pattern1.setOnClickListener{

            // 通知の表示
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId, notificationBuilderPattern1.build())
            }

            Log.d("Main", "Clicked pattern1")

        }

        pattern2.setOnClickListener{

            // 通知の表示
            with(NotificationManagerCompat.from(this)) {
                // notificationId is a unique int for each notification that you must define
                notify(notificationId, notificationBuilderPattern2.build())
            }

            Log.d("Main", "Clicked pattern2")

        }
    }
}