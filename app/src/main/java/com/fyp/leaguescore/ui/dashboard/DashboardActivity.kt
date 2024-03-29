package com.fyp.leaguescore.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fyp.leaguescore.R
import com.fyp.leaguescore.ui.match.MatchHistoryActivity
import com.fyp.leaguescore.ui.prediction.TeamPrediction
import com.fyp.leaguescore.ui.user.SignUpActivity
import com.fyp.leaguescore.ui.video.ProMatchListActivity
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnTeamBuild.setOnClickListener {
            val intent = Intent(this@DashboardActivity, TeamPrediction::class.java)
            startActivity(intent)
        }
        btnProMatches.setOnClickListener {
            val intent = Intent(this@DashboardActivity, ProMatchListActivity::class.java)
            startActivity(intent)
        }
        btnMatchHistory.setOnClickListener {
            val intent = Intent(this@DashboardActivity, MatchHistoryActivity::class.java)
            startActivity(intent)
        }
    }
}