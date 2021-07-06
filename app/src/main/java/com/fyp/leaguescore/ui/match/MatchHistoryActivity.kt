package com.fyp.leaguescore.ui.match

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.leaguescore.R
import com.fyp.leaguescore.adapter.MatchHistoryAdapter
import com.fyp.leaguescore.adapter.ProvideoListAdapter
import com.fyp.leaguescore.app.Constants
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.Match
import com.fyp.leaguescore.model.Video
import com.fyp.leaguescore.network.APIInterface
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_match_history.*
import kotlinx.android.synthetic.main.activity_pro_match_list.*
import kotlinx.android.synthetic.main.activity_pro_match_list.rcVideos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MatchHistoryActivity : AppCompatActivity() {

    private var match: ArrayList<Match> = ArrayList()
    private lateinit var adapterMatch: MatchHistoryAdapter

    @set:Inject
    internal var apiInterface: APIInterface? = null

    @set:Inject
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_history)

        (application as LeagueScoreApplication).getAppComponent()?.inject(this@MatchHistoryActivity)


        val actionBar = supportActionBar
        actionBar!!.title= "Match History"

        adapterMatch = MatchHistoryAdapter(match, this)
        rcmatch.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcmatch.adapter = adapterMatch
        getMatches()
    }

    private fun getMatches() {
        prefs?.getString(Constants.TOKEN, "")?.let {
            apiInterface!!.getMatches("Bearer $it")
                .enqueue(object : Callback<ArrayList<Match>> {
                    override fun onResponse(
                        call: Call<ArrayList<Match>>,
                        response: Response<ArrayList<Match>>
                    ) {
                        if (response.body() != null) {
                            var data = response.body()!!
                            match = data
                            adapterMatch.setMatches(match)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Match>>, t: Throwable) {
                        showError("Something went wrong")
                    }
                })
        }
    }
    fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }
}