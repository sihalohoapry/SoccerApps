package com.sihaloho.soccerapps.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sihaloho.soccerapps.R
import com.sihaloho.core.domain.model.Team
import com.sihaloho.soccerapps.databinding.ActivityDetailTeamBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTeamActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var binding: ActivityDetailTeamBinding
    private val detailTeamViewModel: DetailTeamViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailTeam = intent.getParcelableExtra<Team>(EXTRA_DATA)
        showDetailTeam(detailTeam)
        
    }

    private fun showDetailTeam(detailTeam: Team?) {
        detailTeam?.let {
            supportActionBar?.title = detailTeam.nameTeam
            binding.content.tvDetailDescription.text = detailTeam.description
            Glide.with(this)
                    .load(detailTeam.photoStadium)
                    .into(binding.appBarImage)
            Glide.with(this)
                    .load(detailTeam.logoTeam)
                    .into(binding.logo)

            var statusFavorite = detailTeam.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                if (statusFavorite){
                    val snackbar = Snackbar.make(binding.detailLayout, "Remove from favorite team", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }else{
                    val snackbar = Snackbar.make(binding.detailLayout, "Add to favorite team", Snackbar.LENGTH_LONG)
                    snackbar.show()
                }
                statusFavorite = !statusFavorite
                detailTeamViewModel.setFavoriteTeam(detailTeam, statusFavorite)
                setStatusFavorite(statusFavorite)

            }
        }

    }
    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))

        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_no_favorite))

        }
    }
}