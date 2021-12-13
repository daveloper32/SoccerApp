package com.daveloper.soccerapp.ui.view.league_teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daveloper.soccerapp.auxiliar.ext_fun.loadImage
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.databinding.TeamCardViewBinding

class TeamsAdapter(
    private val teamsList: List<Team>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>()
{
    inner class ViewHolder (
        val binding: TeamCardViewBinding
    ) : RecyclerView.ViewHolder(binding.root),
            View.OnClickListener
    {
        init {
            binding.root.setOnClickListener(this)
        }

        fun getSelectedTeam () : String {
            return binding.tVTeamcVName.text.toString()
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
        val view = TeamCardViewBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        teamsList.get(position).teamBadge?.let { holder.binding.imgVTeamcVBadge.loadImage(it,false) }
        holder.binding.tVTeamcVName.text = teamsList.get(position).name
        holder.binding.tVTeamcVStadium.text = teamsList.get(position).stadiumName
    }

    override fun getItemCount(): Int {
        return teamsList.size
    }

    interface OnItemClickListener {
        fun onItemClicked (selectedItem: Int, teamSelected: String)
    }
}