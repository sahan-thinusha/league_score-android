package com.fyp.leaguescore.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fyp.leaguescore.BuildConfig
import com.fyp.leaguescore.R
import com.fyp.leaguescore.model.Champion
import com.fyp.leaguescore.model.Team
import com.fyp.leaguescore.ui.prediction.ChampionSelectActivity
import kotlinx.android.synthetic.main.activity_champion_select.*
import kotlinx.android.synthetic.main.item_champion.view.*
import kotlinx.android.synthetic.main.item_team.view.*


class TeamListAdapter (var teams:  MutableList<Team>, var context: Context,var adapterChampion: ChampionResultAdapter, var champions: ArrayList<Champion>) :
    RecyclerView.Adapter<TeamListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        // Inflate the custom view from xml layout file
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_team, parent, false)

        return ViewHolder(view,context,adapterChampion,champions)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        teams[position].let { team ->
            holder.bind(team)
        }
    }


    fun setTeamList(teams: ArrayList<Team>) {
        this.teams.clear()
        this.teams = teams
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, val context: Context,var adapterChampion: ChampionResultAdapter, var champions: ArrayList<Champion>) : RecyclerView.ViewHolder(itemView) {


        fun bind(team: Team) {

            itemView.tvtmId.text = "Team Suggestion "+ (getAdapterPosition()+1)

            itemView.rcch.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            itemView.rcch.adapter = adapterChampion
            champions.clear()

              champions = team.champions
            adapterChampion.setChampionList(champions)

        }
    }
}
