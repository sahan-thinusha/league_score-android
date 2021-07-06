package com.fyp.leaguescore.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyp.leaguescore.R
import com.fyp.leaguescore.model.Video
import com.fyp.leaguescore.ui.video.ProMatchListActivity
import kotlinx.android.synthetic.main.pro_match_list_item.view.*

class ProvideoListAdapter  (var videos:  MutableList<Video>, var context: Context) :
    RecyclerView.Adapter<ProvideoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        // Inflate the custom view from xml layout file
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pro_match_list_item, parent, false)

        return ViewHolder(view,context)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        videos[position].let { video ->
            holder.bind(video)
        }
    }


    fun setVideos(videos: ArrayList<Video>) {
        this.videos.clear()
        this.videos = videos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bind(video: Video) {
            itemView.vdname.text = video.title

            itemView.rootView.setOnClickListener{
                video.let { it2 -> (itemView.context as ProMatchListActivity).selectVideo(it2)}
            }
        }
    }
}