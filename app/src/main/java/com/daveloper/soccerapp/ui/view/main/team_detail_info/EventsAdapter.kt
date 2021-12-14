package com.daveloper.soccerapp.ui.view.main.team_detail_info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
import com.daveloper.soccerapp.auxiliar.ext_fun.loadImage
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.databinding.EventCardViewBinding

class EventsAdapter (
    private val eventsList: List<Event>,
    private val context: Context
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>()
{
    inner class ViewHolder (
        val binding: EventCardViewBinding
    ) : RecyclerView.ViewHolder(binding.root)
    {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = EventCardViewBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tVEventcVDate.text = eventsList.get(position).dateEvent
        if (!eventsList.get(position).timeEvent.equals(context.getStringResource(R.string.msg_team_det_noHour))) {
            holder.binding.tVEventcVHour.text = eventsList.get(position).timeEvent
        } else {
            holder.binding.tVEventcVHour.text = context.getStringResource(R.string.msg_team_det_noHour_exp)
        }
        eventsList.get(position).homeTeamBadge?.let {
            holder.binding.imgVEventcVHomeBadge.loadImage(it, false)
        }
        eventsList.get(position).awayTeamBadge?.let {
            holder.binding.imgVEventcVAwayBadge.loadImage(it, false)
        }

        holder.binding.tVEventcVHomeTeam.text = eventsList.get(position).homeTeam
        holder.binding.tVEventcVAwayTeam.text = eventsList.get(position).awayTeam
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }
}