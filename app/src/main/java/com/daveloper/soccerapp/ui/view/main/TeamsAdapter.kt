package com.daveloper.soccerapp.ui.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.auxiliar.ext_fun.loadImage
import com.daveloper.soccerapp.data.model.entity.Team

class TeamsAdapter(
    private val teamsList: List<Team>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>()
{
    inner class ViewHolder (
        view: View
    ) : RecyclerView.ViewHolder(view),
            View.OnClickListener
    {
        val imgV_teamcV_badge: ImageView
        val tV_teamcV_name: TextView
        val tV_teamcV_stadium: TextView

        init {
            imgV_teamcV_badge = view.findViewById(R.id.imgV_teamcV_badge)
            tV_teamcV_name = view.findViewById(R.id.tV_teamcV_name)
            tV_teamcV_stadium = view.findViewById(R.id.tV_teamcV_stadium)
            view.setOnClickListener(this)
        }

        fun getSelectedTeam () : String {
            return tV_teamcV_name.text.toString()
        }

        override fun onClick(v: View?) {
            val selectedItem: Int = adapterPosition
            if (selectedItem != RecyclerView.NO_POSITION) {
                if (!getSelectedTeam().isEmpty()) {
                    listener.onItemClicked(selectedItem, getSelectedTeam())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.team_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        teamsList.get(position).teamBadge?.let { holder.imgV_teamcV_badge.loadImage(it,false) }
        holder.tV_teamcV_name.text = teamsList.get(position).name
        holder.tV_teamcV_stadium.text = teamsList.get(position).stadiumName
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    interface OnItemClickListener {
        fun onItemClicked (selectedItem: Int, teamSelected: String)
    }
}