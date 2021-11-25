package com.daveloper.soccerapp.ui.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
import com.daveloper.soccerapp.auxiliar.ext_fun.goToXActivity
import com.daveloper.soccerapp.auxiliar.ext_fun.toast
import com.daveloper.soccerapp.databinding.ActivityMainBinding
import com.daveloper.soccerapp.data.model.entity.Team
import com.daveloper.soccerapp.ui.viewmodel.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
        TeamsAdapter.OnItemClickListener,
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
        viewModel.onCreate()
        // Listeners
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
        viewModel.recyclerViewData.observe(
            this,
            Observer {
                sendTeamsInfoToAdapter(it)
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
        toast("$teamSelected is Selected!")
    }

    override fun onRefresh() {
        viewModel.onCreate()
        binding.rVRefreshMain.isRefreshing = false
    }
}