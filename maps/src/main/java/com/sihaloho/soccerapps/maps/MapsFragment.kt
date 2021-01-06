package com.sihaloho.soccerapps.maps

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.geometry.LatLngBounds
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.sihaloho.core.data.Resource
import com.sihaloho.core.domain.model.Team
import com.sihaloho.soccerapps.maps.databinding.FragmentMapsBinding
import com.sihaloho.soccerapps.ui.detail.DetailTeamActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class MapsFragment : Fragment() {

    private var _binding : FragmentMapsBinding ?= null
    private val binding get() = _binding!!

    companion object {
        private const val ICON_ID = "ICON_ID"
    }

    private lateinit var mapboxMap: MapboxMap
    private val mapsViewModel: MapsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
        _binding = FragmentMapsBinding.inflate(inflater,container,false)
        return binding.root
       }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(mapsModule)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            getTeamData()
        }

    }

    private fun getTeamData() {
        mapsViewModel.team.observe(requireActivity(), { team ->
            if (team != null) {
                when (team) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        showMarker(team.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = team.message
                    }
                }
            }
        })
    }

    private fun showMarker(data: List<Team>?) {
        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
            style.addImage(ICON_ID, BitmapFactory.decodeResource(resources, R.drawable.mapbox_marker_icon_default))
            val latLngBoundsBuilder = LatLngBounds.Builder()

            val symbolManager = SymbolManager(binding.mapView, mapboxMap, style)
            symbolManager.iconAllowOverlap = true

            val options = ArrayList<SymbolOptions>()
            data?.forEach { data ->
                latLngBoundsBuilder.include(LatLng(-5.241570484731818, 104.9699469409319))
                options.add(
                    SymbolOptions()
                        .withLatLng(LatLng(-5.241570484731818, 104.9699469409319))
                        .withIconImage(ICON_ID)
                        .withData(Gson().toJsonTree(data))
                )
            }
            symbolManager.create(options)

            val latLngBounds = latLngBoundsBuilder.build()
            mapboxMap.easeCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 50), 5000)

            symbolManager.addClickListener { symbol ->
                val data = Gson().fromJson(symbol.data, Team::class.java)
                val intent = Intent(context, DetailTeamActivity::class.java)
                intent.putExtra(DetailTeamActivity.EXTRA_DATA, data)
                startActivity(intent)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }
    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }
    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }
    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

}