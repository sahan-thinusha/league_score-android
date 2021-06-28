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
import com.fyp.leaguescore.ui.prediction.ChampionSelectActivity
import kotlinx.android.synthetic.main.item_champion.view.*


    class ChampionListAdapter (var champions:  MutableList<Champion>, var context: Context) :
        RecyclerView.Adapter<ChampionListAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
            // Inflate the custom view from xml layout file
            var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_champion, parent, false)

            return ViewHolder(view,context)
        }

        override fun getItemCount(): Int {
            return champions.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            champions[position].let { champion ->
                holder.bind(champion)
            }
        }


        fun setChampionList(champions: ArrayList<Champion>) {
            this.champions.clear()
            this.champions = champions
            notifyDataSetChanged()
        }

        class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
            fun bind(champion: Champion) {
                itemView.tvchaname.text = champion.name

                if (!champion.avatar.isNullOrEmpty()){
                    Glide.with(context).load(BuildConfig.API_URL+champion.avatar).error(R.drawable.avatar_placeholder).placeholder(R.drawable.avatar_placeholder).into(itemView.imgch);
                }

                itemView.rootView.setOnClickListener{
                    champion.let { it2 -> (itemView.context as ChampionSelectActivity).selectChampion(it2)}
                }
            }
        }
    }

