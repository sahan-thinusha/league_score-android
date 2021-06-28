package com.fyp.leaguescore.ui.prediction

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.leaguescore.R
import com.fyp.leaguescore.adapter.ChampionListAdapter
import com.fyp.leaguescore.adapter.ChampionResultAdapter
import com.fyp.leaguescore.adapter.TeamListAdapter
import com.fyp.leaguescore.app.Constants
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.Champion
import com.fyp.leaguescore.model.Team
import com.fyp.leaguescore.network.APIInterface
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_champion_list.*
import kotlinx.android.synthetic.main.activity_champion_select.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ChampionListActivity : AppCompatActivity() {

    private var team: ArrayList<Team> = ArrayList()
    private lateinit var adapterTeam: TeamListAdapter

    @set:Inject
    internal var apiInterface: APIInterface? = null

    @set:Inject
    var prefs: SharedPreferences? = null

    private var champions: ArrayList<Champion> = ArrayList()
    private lateinit var adapterChampion: ChampionResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion_list)
        (application as LeagueScoreApplication).getAppComponent()?.inject(this@ChampionListActivity)

        adapterChampion = ChampionResultAdapter(champions, this)
        adapterTeam = TeamListAdapter(team, this,adapterChampion,champions)

        rcteam.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcteam.adapter = adapterTeam

        var ch1 : String = ""
        var ch2 : String = ""
        var ch3 : String = ""
        var ch4 : String = ""
         ch1 = intent.getStringExtra("CH1").toString()
         ch2 = intent.getStringExtra("CH2").toString()
         ch3 = intent.getStringExtra("CH3").toString()
         ch4 = intent.getStringExtra("CH4").toString()


       getPredictions(ch1,ch2,ch3,ch4)
    }

    private fun getPredictions(ch1 : String,ch2 : String,ch3 : String,ch4 : String) {
        prefs?.getString(Constants.TOKEN, "")?.let {
            apiInterface!!.prediction("Bearer $it",ch1,ch2,ch3,ch4)
                .enqueue(object : Callback<ArrayList<Team>> {
                    override fun onResponse(
                        call: Call<ArrayList<Team>>,
                        response: Response<ArrayList<Team>>
                    ) {
                        if (response.body() != null) {
                            var data = response.body()!!
                            team = data
                            adapterTeam.setTeamList(data)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Team>>, t: Throwable) {
                        showError("Something went wrong")
                    }
                })
        }
    }

    fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }
    override fun onBackPressed() {
        finish()
    }
}