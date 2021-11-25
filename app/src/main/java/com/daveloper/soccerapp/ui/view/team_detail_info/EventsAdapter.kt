package com.daveloper.soccerapp.ui.view.team_detail_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.data.model.entity.Event

class EventsAdapter (
    private val eventsList: List<Event>
) : RecyclerView.Adapter<EventsAdapter.ViewHolder>()
{
    inner class ViewHolder (
        view: View
    ) : RecyclerView.ViewHolder(view)
    {
        val tV_eventcV_date: TextView
        val tV_eventcV_hour: TextView
        val imgV_eventcV_home_badge: ImageView
        val imgV_eventcV_away_badge: ImageView
        val tV_eventcV_home_team: TextView
        val tV_eventcV_away_team: TextView
        init {
            tV_eventcV_date = view.findViewById(R.id.tV_eventcV_date)
            tV_eventcV_hour = view.findViewById(R.id.tV_eventcV_hour)
            imgV_eventcV_home_badge = view.findViewById(R.id.imgV_eventcV_home_badge)
            imgV_eventcV_away_badge = view.findViewById(R.id.imgV_eventcV_away_badge)
            tV_eventcV_home_team = view.findViewById(R.id.tV_eventcV_home_team)
            tV_eventcV_away_team = view.findViewById(R.id.tV_eventcV_away_team)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.event_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tV_eventcV_date.text = eventsList.get(position).dateEvent
        holder.tV_eventcV_hour.text = eventsList.get(position).timeEvent
        holder.tV_eventcV_home_team.text = eventsList.get(position).homeTeam
        holder.tV_eventcV_away_team.text = eventsList.get(position).awayTeam
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }


}