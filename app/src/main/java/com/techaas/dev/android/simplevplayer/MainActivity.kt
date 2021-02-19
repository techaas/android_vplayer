// Copyright (c) 2021, TECHaas.com. All rights reserved.
//
package com.techaas.dev.android.simplevplayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoText = findViewById<TextView>(R.id.video_time)
        val video = VideoView(this)
        video.setVideoPath("android.resource://" + packageName + "/" + R.raw.sample)

        video.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)

        video.setOnPreparedListener { mp ->
            mp.isLooping = true
            var running = true
            val duration: Int = video.duration

            Thread {
                do {
                    runOnUiThread {
                        videoText.text = "%.2f s".format((video.currentPosition / 1000.0))
                    }
                    try {
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    if (!running) break
                } while (video.currentPosition < duration)
            }.start()
        }

        video.setOnTouchListener{ v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    Log.i(TAG, "setOnTouchListener")
                    if (video.isPlaying) {
                        video.pause()
                    } else {
                        video.start()
                    }
                }
                else -> { }
            }
            true
        }

        video.requestFocus()
        video.start()

        // relativeの真ん中に置く
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

        val relativeLayout = findViewById<RelativeLayout>(R.id.base_layout)
        relativeLayout.addView(video, params)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}