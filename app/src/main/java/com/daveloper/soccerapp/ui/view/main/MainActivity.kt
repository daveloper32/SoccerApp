package com.daveloper.soccerapp.ui.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daveloper.soccerapp.R
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
import com.daveloper.soccerapp.auxiliar.ext_fun.goToXActivity
import com.daveloper.soccerapp.auxiliar.ext_fun.goToXActivityWithData
import com.daveloper.soccerapp.auxiliar.ext_fun.toast
import com.daveloper.soccerapp.databinding.ActivityMainBinding
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.ui.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
        TeamsAdapter.OnItemClickListener,
        AdapterView.OnItemSelectedListener,
        View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener
{
    // Init Vars
    // Binding
    private lateinit var binding: ActivityMainBinding
    // Adapter RecyclerView
    private lateinit var teamsAdapter: TeamsAdapter
    // View Model
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view : View = binding.root
        setContentView(view)
        startView()
    }
    private fun startView () {
        initLiveData()
        binding.rVMain.layoutManager = LinearLayoutManager(this)
        viewModel.onCreate(this)
        // Listeners
        binding.tBMain.spinner.onItemSelectedListener = this
        binding.imgBMainReloadTeams.setOnClickListener(this)
        binding.rVRefreshMain.setOnRefreshListener(this)

    }

    private fun initLiveData() {
        viewModel.progressVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.pgsBMain.visibility = View.VISIBLE
                } else {
                    binding.pgsBMain.visibility = View.GONE
                }
            }
        )
        viewModel.showInfoMessage.observe(
            this,
            Observer {
                toast(getStringResource(it))
            }
        )
        viewModel.goToXActivity.observe(
            this,
            Observer {
                goToXActivity(it)
            }
        )
        viewModel.setSpinnerPosition.observe(
            this,
            Observer {
                binding.tBMain.spinner.setSelection(it)
            }
        )
        viewModel.spinnerData.observe(
            this,
            Observer {
                val leagueAdapter = ArrayAdapter(
                    this,
                    R.layout.my_selected_item,
                    it
                )
                binding.tBMain.spinner.adapter = leagueAdapter
            }
        )
        viewModel.iBReloadTeamsVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBMainReloadTeams.visibility = View.VISIBLE
                } else {
                    binding.imgBMainReloadTeams.visibility = View.GONE
                }
            }
        )
        viewModel.recyclerViewData.observe(
            this,
            Observer {
                sendTeamsInfoToAdapter(it)
            }
        )
        viewModel.refreshRecyclerViewData.observe(
            this,
            Observer {
                sendTeamsInfoToAdapter(it)
                binding.rVRefreshMain.isRefreshing = false
            }
        )
        viewModel.goToXActivityWithData.observe(
            this,
            Observer {
                goToXActivityWithData(it.activity, it.teamSelected)
            }
        )
    }

    private fun sendTeamsInfoToAdapter(teamsInfo: List<Team>) {
        teamsAdapter = TeamsAdapter(
            teamsInfo,
            this
        )
        binding.rVMain.adapter = teamsAdapter
    }


    override fun onItemClicked(selectedItem: Int, teamSelected: String) {
        viewModel.onTeamClicked(teamSelected)
    }

    override fun onRefresh() {
        viewModel.onRefreshRv(this)
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        viewModel.onSpinnerItemChanged(
            this,
            binding.tBMain.spinner.selectedItem as String
        )
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onClick(v: View?) {
        val idSelected: Int = v!!.id
        when (idSelected) {
            binding.imgBMainReloadTeams.id -> viewModel.onReloadTeamsClicked(this)
        }
    }
}