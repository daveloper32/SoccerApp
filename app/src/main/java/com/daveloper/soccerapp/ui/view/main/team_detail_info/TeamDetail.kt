package com.daveloper.soccerapp.ui.view.main.team_detail_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.daveloper.soccerapp.auxiliar.ext_fun.getStringResource
import com.daveloper.soccerapp.auxiliar.ext_fun.loadImage
import com.daveloper.soccerapp.auxiliar.ext_fun.toast
import com.daveloper.soccerapp.data.model.entity.Event
import com.daveloper.soccerapp.databinding.FragmentTeamDetailBinding
import com.daveloper.soccerapp.ui.viewmodel.main.team_detail_info.TeamDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetail : Fragment(),
    View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener
{
    // Init vars
    // Binding
    private lateinit var binding: FragmentTeamDetailBinding
    // Safe Args reference
    private val teamNameNavArgs by navArgs<TeamDetailArgs>()
    // View Model
    private val viewModel: TeamDetailViewModel by viewModels<TeamDetailViewModel> ()
    // Adapter Recycler View
    private lateinit var eventsAdapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamDetailBinding.inflate(inflater)
        initView()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initView() {
        initLiveData()
        binding.rVEvents.layoutManager = LinearLayoutManager(this.requireContext())
        viewModel.onCreate(teamNameNavArgs.teamNameSelected)
        // Listeners
        binding.tBTeamDetail.tBImgVTeamDetBackicon.setOnClickListener(this)
        binding.imgBTeamDetReloadEvents.setOnClickListener(this)
        binding.tVTeamDetWebPage.setOnClickListener(this)
        binding.imgBTeamWebPage.setOnClickListener(this)
        binding.imgBTeamDetFacebook.setOnClickListener(this)
        binding.imgBTeamDetInstagram.setOnClickListener(this)
        binding.imgBTeamDetTwitter.setOnClickListener(this)
        binding.imgBTeamDetYoutube.setOnClickListener(this)
        binding.rVRefreshEvents.setOnRefreshListener(this)
    }

    private fun initLiveData() {
        viewModel.progressVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.pgsBTeamDetail.visibility = View.VISIBLE
                } else {
                    binding.pgsBTeamDetail.visibility = View.GONE
                }
            }
        )
        viewModel.progressEventsVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.pgsBEvents.visibility = View.VISIBLE
                } else {
                    binding.pgsBEvents.visibility = View.GONE
                }
            }
        )
        viewModel.showInfoMessage.observe(
            this,
            Observer {
                this.requireActivity().toast(it)
            }
        )
        viewModel.showInfoMessageFromResource.observe(
            this,
            Observer {
                this
                    .requireActivity()
                    .toast(
                        this.requireActivity().getStringResource(it)
                    )
            }
        )
        viewModel.goToXActivity.observe(
            this,
            Observer {
                if (it) {
                    findNavController()
                        .navigate(
                            TeamDetailDirections.actionTeamDetailToLeagueTeams()
                        )
                }
            }
        )
        viewModel.recyclerViewData.observe(
            this,
            Observer {
                sendEventsInfoToAdapter(it)
            }
        )
        viewModel.iBReloadEventsVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBTeamDetReloadEvents.visibility = View.VISIBLE
                } else {
                    binding.imgBTeamDetReloadEvents.visibility = View.GONE
                }
            }
        )
        viewModel.setTextTeamName.observe(
            this,
            Observer {
                binding.tVTeamDetName.text = it
            }
        )
        viewModel.setTextTeamDescription.observe(
            this,
            Observer {
                binding.tVTeamDetDescription.text = it
            }
        )
        viewModel.setTextFoundationYear.observe(
            this,
            Observer {
                binding.tVTeamDetFoundYear.text = it
            }
        )
        viewModel.setImageTeamBadge.observe(
            this,
            Observer {
                binding.imgVTeamDetBadge.loadImage(it, false)
            }
        )
        viewModel.setImageTeamJersey.observe(
            this,
            Observer {
                binding.imgVTeamDetJersey.loadImage(it, false)
            }
        )
        viewModel.setTextWebpage.observe(
            this,
            Observer {
                binding.tVTeamDetWebPage.text = it
            }
        )
        viewModel.linkToWebpage.observe(
            this,
            Observer {
                startActivity(it)
            }
        )
        viewModel.linkToFacebookWebpage.observe(
            this,
            Observer {
                startActivity(it)
            }
        )
        viewModel.linkToInstagramWebpage.observe(
            this,
            Observer {
                startActivity(it)
            }
        )
        viewModel.linkToTwitterWebpage.observe(
            this,
            Observer {
                startActivity(it)
            }
        )
        viewModel.linkToYoutubeWebpage.observe(
            this,
            Observer {
                startActivity(it)
            }
        )
        viewModel.tVSocialNetworkVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.tVTeamDetSocialNetworksTitle.visibility = View.VISIBLE
                } else {
                    binding.tVTeamDetSocialNetworksTitle.visibility = View.GONE
                }
            }
        )
        viewModel.iBWebpageVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBTeamWebPage.visibility = View.VISIBLE
                } else {
                    binding.imgBTeamWebPage.visibility = View.GONE
                }
            }
        )
        viewModel.iBFacebookVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBTeamDetFacebook.visibility = View.VISIBLE
                } else {
                    binding.imgBTeamDetFacebook.visibility = View.GONE
                }
            }
        )
        viewModel.iBInstagramVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBTeamDetInstagram.visibility = View.VISIBLE
                } else {
                    binding.imgBTeamDetInstagram.visibility = View.GONE
                }
            }
        )
        viewModel.iBTwitterVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBTeamDetTwitter.visibility = View.VISIBLE
                } else {
                    binding.imgBTeamDetTwitter.visibility = View.GONE
                }
            }
        )
        viewModel.iBYoutubeVisibility.observe(
            this,
            Observer {
                if (it) {
                    binding.imgBTeamDetYoutube.visibility = View.VISIBLE
                } else {
                    binding.imgBTeamDetYoutube.visibility = View.GONE
                }
            }
        )
    }

    private fun sendEventsInfoToAdapter(eventsInfo: List<Event>) {
        eventsAdapter = EventsAdapter(eventsInfo, this.requireContext())
        binding.rVEvents.adapter = eventsAdapter
    }

    override fun onClick(v: View?) {
        val idSelected: Int = v!!.id
        when (idSelected) {
            // Toolbar
            binding.tBTeamDetail.tBImgVTeamDetBackicon.id -> viewModel.onBackClicked()
            // Activity
            binding.imgBTeamDetReloadEvents.id -> viewModel.onReloadEventsClicked()
            binding.tVTeamDetWebPage.id -> viewModel.onWebpageTeamClicked()
            binding.imgBTeamWebPage.id -> viewModel.onWebpageTeamClicked()
            binding.imgBTeamDetFacebook.id -> viewModel.onFacebookWebpageTeamClicked()
            binding.imgBTeamDetInstagram.id -> viewModel.onInstagramWebpageTeamClicked()
            binding.imgBTeamDetTwitter.id -> viewModel.onTwitterWebpageTeamClicked()
            binding.imgBTeamDetYoutube.id -> viewModel.onYoutubeWebpageTeamClicked()
        }
    }

    override fun onRefresh() {
        viewModel.onCreate(teamNameNavArgs.teamNameSelected)
        binding.rVRefreshEvents.isRefreshing = false
    }

}