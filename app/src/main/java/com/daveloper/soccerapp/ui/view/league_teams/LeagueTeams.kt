package com.daveloper.soccerapp.ui.view.league_teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
import com.daveloper.soccerapp.auxiliar.ext_fun.toast
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.databinding.FragmentLeagueTeamsBinding
import com.daveloper.soccerapp.ui.viewmodel.league_teams.LeagueTeamsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueTeams : Fragment(),
    TeamsAdapter.OnItemClickListener,
    AdapterView.OnItemSelectedListener,
    View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener
{
    // Init Vars
    // Binding
    private lateinit var binding: FragmentLeagueTeamsBinding
    // ViewModel
    private val viewModel: LeagueTeamsViewModel by viewModels<LeagueTeamsViewModel>()
    // Adapter RecyclerView
    private lateinit var teamsAdapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeagueTeamsBinding.inflate(inflater)
        initView()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initView() {
        // Init Live Data Observers
        initLiveData()
        //
        binding.rVLeagueTeams.layoutManager = LinearLayoutManager(this.requireContext())
        //
        viewModel.onCreate()
        //Listeners
        binding.tBLeagueTeams.spinner.onItemSelectedListener = this
        binding.rVRefreshLeagueTeams.setOnRefreshListener(this)
        binding.imgBLeagueTeamsReloadTeams.setOnClickListener(this)
    }

    private fun initLiveData() {
        viewModel.progressVisibility.observe(
            viewLifecycleOwner,
            Observer {
                if (it) {
                    binding.pgsBLeagueTeams.visibility = View.VISIBLE
                } else {
                    binding.pgsBLeagueTeams.visibility = View.GONE
                }
            }
        )
        viewModel.showInfoMessage.observe(
            viewLifecycleOwner,
            Observer {
                this.requireActivity().toast(
                    this.requireActivity().getStringResource(it)
                )
            }
        )
        viewModel.goToXActivity.observe(
            viewLifecycleOwner,
            Observer {
                //goToXActivity(it)
            }
        )
        viewModel.setSpinnerPosition.observe(
            viewLifecycleOwner,
            Observer {
                binding.tBLeagueTeams.spinner.setSelection(it)
            }
        )
        viewModel.spinnerData.observe(
            viewLifecycleOwner,
            Observer {
                val leagueAdapter = ArrayAdapter(
                    this.requireContext(),
                    R.layout.my_selected_item,
                    it
                )
                binding.tBLeagueTeams.spinner.adapter = leagueAdapter
            }
        )
        viewModel.iBReloadTeamsVisibility.observe(
            viewLifecycleOwner,
            Observer {
                if (it) {
                    binding.imgBLeagueTeamsReloadTeams.visibility = View.VISIBLE
                } else {
                    binding.imgBLeagueTeamsReloadTeams.visibility = View.GONE
                }
            }
        )
        viewModel.recyclerViewData.observe(
            viewLifecycleOwner,
            Observer {
                sendTeamsInfoToAdapter(it)
            }
        )
        viewModel.refreshRecyclerViewData.observe(
            viewLifecycleOwner,
            Observer {
                sendTeamsInfoToAdapter(it)
                binding.rVRefreshLeagueTeams.isRefreshing = false
            }
        )
        viewModel.goToXActivityWithData.observe(
            viewLifecycleOwner,
            Observer {
                //goToXActivityWithData(it.activity, it.teamSelected)
            }
        )
    }

    private fun sendTeamsInfoToAdapter(teamsInfo: List<Team>) {
        teamsAdapter = TeamsAdapter(
            teamsInfo,
            this
        )
        binding.rVLeagueTeams.adapter = teamsAdapter
    }



    override fun onItemClicked(selectedItem: Int, teamSelected: String) {
        viewModel.onTeamClicked(teamSelected)
    }

    override fun onRefresh() {
        viewModel.onRefreshRv()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.onSpinnerItemChanged(
            binding.tBLeagueTeams.spinner.selectedItem as String
        )
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onClick(v: View?) {
        val idSelected: Int = v!!.id
        when (idSelected) {
            binding.imgBLeagueTeamsReloadTeams.id -> viewModel.onReloadTeamsClicked()
        }
    }
}