package com.fyp.leaguescore.ui.prediction

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.leaguescore.R
import com.fyp.leaguescore.adapter.ChampionListAdapter
import com.fyp.leaguescore.app.Constants
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.Champion
import com.fyp.leaguescore.network.APIInterface
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_champion_select.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ChampionSelectActivity : AppCompatActivity() {
    @set:Inject
    internal var apiInterface: APIInterface? = null

    @set:Inject
    var prefs: SharedPreferences? = null

    private var champions: ArrayList<Champion> = ArrayList()
    private lateinit var adapterChampion: ChampionListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_champion_select)
        (application as LeagueScoreApplication).getAppComponent()
            ?.inject(this@ChampionSelectActivity)

        val index = intent.getIntExtra(Constants.DATA,-1)

        adapterChampion = ChampionListAdapter(champions, this)

        rcChampions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcChampions.adapter = adapterChampion
        getChampions()

    }

    fun selectChampion(it2: Champion) {
        val rtintent = Intent()
        rtintent.putExtra(Constants.DATA,it2)
        setResult(RESULT_OK, rtintent)
        finish()
    }


    private fun getChampions() {

        prefs?.getString(Constants.TOKEN, "")?.let {
            apiInterface!!.getChampions("Bearer $it")
                .enqueue(object : Callback<ArrayList<Champion>> {
                    override fun onResponse(
                        call: Call<ArrayList<Champion>>,
                        response: Response<ArrayList<Champion>>
                    ) {
                        if (response.body() != null) {
                            champions = response.body()!!
                            adapterChampion.setChampionList(champions)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Champion>>, t: Throwable) {
                        showError("Something went wrong")
                    }
                })
        }

    }

    fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }
}
