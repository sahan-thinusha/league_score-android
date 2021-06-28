package com.fyp.leaguescore.ui.prediction

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fyp.leaguescore.BuildConfig
import com.fyp.leaguescore.R
import com.fyp.leaguescore.app.Constants
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.Champion
import com.fyp.leaguescore.model.Team
import com.fyp.leaguescore.network.APIInterface
import com.fyp.leaguescore.ui.dashboard.DashboardActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_team_prediction.*
import kotlinx.android.synthetic.main.item_champion.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TeamPrediction : AppCompatActivity() {

    private val REQUEST_C1 = 435
    private val REQUEST_C2 = 535
    private val REQUEST_C3 = 635
    private val REQUEST_C4 = 735

    private var ch1 : String =""
    private var ch2 : String =""
    private var ch3 : String =""
    private var ch4 : String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_prediction)

        imgCh1.setOnClickListener {
            val intent = Intent(this@TeamPrediction, ChampionSelectActivity::class.java)
            startActivityForResult(intent,REQUEST_C1)
        }

        imgCh2.setOnClickListener {
            val intent = Intent(this@TeamPrediction, ChampionSelectActivity::class.java)
            startActivityForResult(intent,REQUEST_C2)

        }

        imgCh3.setOnClickListener {
            val intent = Intent(this@TeamPrediction, ChampionSelectActivity::class.java)
            startActivityForResult(intent,REQUEST_C3)

        }

        imgCh4.setOnClickListener {
            val intent = Intent(this@TeamPrediction, ChampionSelectActivity::class.java)
            startActivityForResult(intent,REQUEST_C4)

        }

        btn_generateteam.setOnClickListener {
            val intent = Intent(this@TeamPrediction, ChampionListActivity::class.java)
            intent.putExtra("CH1", ch1)
            intent.putExtra("CH2", ch2)
            intent.putExtra("CH3", ch3)
            intent.putExtra("CH4", ch4)

            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val d= data?.getParcelableExtra<Champion>(Constants.DATA)

            if (requestCode == REQUEST_C1) {
                if (d != null) {
                    Glide.with(this@TeamPrediction).load(BuildConfig.API_URL+d.avatar).error(R.drawable.avatar_placeholder).placeholder(R.drawable.avatar_placeholder).into(imgCh1)
                    tvCh1Name.text = d.name
                    ch1 = d.index.toString()
                    };


            }else if  (requestCode == REQUEST_C2) {
                if (d != null) {
                    Glide.with(this@TeamPrediction).load(BuildConfig.API_URL+d.avatar).error(R.drawable.avatar_placeholder).placeholder(R.drawable.avatar_placeholder).into(imgCh2)
                    tvCh2Name.text = d.name
                    ch2 = d.index.toString()

                };

            }else if  (requestCode == REQUEST_C3) {
                if (d != null) {
                    Glide.with(this@TeamPrediction).load(BuildConfig.API_URL+d.avatar).error(R.drawable.avatar_placeholder).placeholder(R.drawable.avatar_placeholder).into(imgCh3)
                    tvCh3Name.text = d.name
                    ch3 = d.index.toString()

                };

            }else if  (requestCode == REQUEST_C4) {
                if (d != null) {
                    Glide.with(this@TeamPrediction).load(BuildConfig.API_URL+d.avatar).error(R.drawable.avatar_placeholder).placeholder(R.drawable.avatar_placeholder).into(imgCh4)
                    tvCh4Name.text = d.name
                    ch4 = d.index.toString()

                };

            }

        }
    }

}