package com.fyp.leaguescore.ui.video

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fyp.leaguescore.R
import com.fyp.leaguescore.adapter.ChampionResultAdapter
import com.fyp.leaguescore.adapter.ProvideoListAdapter
import com.fyp.leaguescore.adapter.TeamListAdapter
import com.fyp.leaguescore.app.Constants
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.Team
import com.fyp.leaguescore.model.Video
import com.fyp.leaguescore.network.APIInterface
import com.fyp.leaguescore.ui.user.SignUpActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_champion_list.*
import kotlinx.android.synthetic.main.activity_champion_list.rcteam
import kotlinx.android.synthetic.main.activity_pro_match_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProMatchListActivity : AppCompatActivity() {
    private var video: ArrayList<Video> = ArrayList()
    private lateinit var adapterVideo: ProvideoListAdapter

    @set:Inject
    internal var apiInterface: APIInterface? = null

    @set:Inject
    var prefs: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pro_match_list)
        (application as LeagueScoreApplication).getAppComponent()?.inject(this@ProMatchListActivity)


        val actionBar = supportActionBar
        actionBar!!.title= "Pro Matches"

        adapterVideo = ProvideoListAdapter(video, this)
        rcVideos.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rcVideos.adapter = adapterVideo
        getVideos()
    }

    fun selectVideo(it2: Video) {
        val intent = Intent(this@ProMatchListActivity, ProMatchViewActivity::class.java)
        intent.putExtra(Constants.DATA,it2.videoId)
        startActivity(intent)
    }

    private fun getVideos() {
        prefs?.getString(Constants.TOKEN, "")?.let {
            apiInterface!!.getVideos("Bearer $it")
                .enqueue(object : Callback<ArrayList<Video>> {
                    override fun onResponse(
                        call: Call<ArrayList<Video>>,
                        response: Response<ArrayList<Video>>
                    ) {
                        if (response.body() != null) {
                            var data = response.body()!!
                            video = data
                            adapterVideo.setVideos(video)
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Video>>, t: Throwable) {
                        showError("Something went wrong")
                    }
                })
        }
    }
    fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }
}