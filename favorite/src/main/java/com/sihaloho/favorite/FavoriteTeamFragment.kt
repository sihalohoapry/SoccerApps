package com.sihaloho.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sihaloho.core.ui.TeamAdapter
import com.sihaloho.favorite.databinding.FragmentFavoriteTeamBinding
import com.sihaloho.soccerapps.ui.detail.DetailTeamActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteTeamFragment : Fragment() {

    private var _binding : FragmentFavoriteTeamBinding ?= null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteTeamViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteTeamBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            loadKoinModules(favoriteTeamModule)
            val teamAdapter = TeamAdapter()
            teamAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailTeamActivity::class.java)
                intent.putExtra(DetailTeamActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favoriteViewModel.favoriteTeam.observe(viewLifecycleOwner, { dataTeam ->
                teamAdapter.setData(dataTeam)
                binding.viewEmptyFav.root.visibility = if (dataTeam.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTeamFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = teamAdapter
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}