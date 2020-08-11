package io.github.ovso.rollingbanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_main_banner.view.*

class BannerAdapter : ListAdapter<String, BannerViewHolder>(
    object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            areItemsTheSame(oldItem, newItem)
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder =
        BannerViewHolder.create(parent)

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) =
        holder.onBindViewHolder(getItem(position))
}

class BannerViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun onBindViewHolder(item: String) {
        with(itemView) {
            Glide.with(ivMainBanner).load(item).into(ivMainBanner)
        }
    }

    companion object {
        fun create(parent: ViewGroup): BannerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_banner, parent, false)
            return BannerViewHolder(view)
        }
    }
}