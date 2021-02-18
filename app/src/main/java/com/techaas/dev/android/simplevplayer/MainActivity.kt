package com.techaas.dev.android.simplevplayer

import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val video = VideoView(this)
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.sample)

        video.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT)

        video.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })
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
}