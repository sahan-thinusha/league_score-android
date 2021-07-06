package com.fyp.leaguescore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fyp.leaguescore.BuildConfig
import com.fyp.leaguescore.R
import com.fyp.leaguescore.model.Champion
import com.fyp.leaguescore.model.Match
import kotlinx.android.synthetic.main.item_champion.view.*
import kotlinx.android.synthetic.main.match_history_item.view.*


class MatchHistoryAdapter (var matches:  MutableList<Match>, var context: Context) :
    RecyclerView.Adapter<MatchHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        // Inflate the custom view from xml layout file
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_history_item, parent, false)

        return ViewHolder(view,context)
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        matches[position].let { match ->
            holder.bind(match)
        }
    }


    fun setMatches(matches: ArrayList<Match>) {
        this.matches.clear()
        this.matches = matches
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bind(match: Match) {

            if (match.path.isNotEmpty()){
                Glide.with(context).load(BuildConfig.API_URL+match.path).into(itemView.imgHistory);
            }


        }
    }
}