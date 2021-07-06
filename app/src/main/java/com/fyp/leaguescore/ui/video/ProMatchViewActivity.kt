package com.fyp.leaguescore.ui.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fyp.leaguescore.R
import com.fyp.leaguescore.app.Constants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_pro_match_view.*


class ProMatchViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pro_match_view)

        val id = intent.getStringExtra(Constants.DATA)

        videoPlayer.enterFullScreen();
        videoPlayer.toggleFullScreen();

        getLifecycle().addObserver(videoPlayer);


        videoPlayer.getPlayerUiController();

        videoPlayer.enterFullScreen();
        videoPlayer.toggleFullScreen();
        videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video into the YouTube Player
                if (id != null) {
                    youTubePlayer.loadVideo(id, 0f)
                }
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
            }
        })

    }
}