package com.sihaloho.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sihaloho.core.R
import com.sihaloho.core.databinding.ItemListTeamBinding
import com.sihaloho.core.domain.model.Team
import java.util.ArrayList

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ListViewHolder>() {

    private var listData = ArrayList<Team>()
    var onItemClick: ((Team) -> Unit)? = null

    fun setData(newListData: List<Team>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_team, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTeamBinding.bind(itemView)
        fun bind(data: Team) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.logoTeam)
                    .into(ivLogo)
                tvName.text = data.nameTeam
                tvLocation.text = data.location
                tvDescription.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}