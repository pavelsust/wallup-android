/**
 * ISC License
 *
 * Copyright 2018-2019, Saksham (DawnImpulse)
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 * WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE
 * OR PERFORMANCE OF THIS SOFTWARE.
 **/
package com.dawnimpulse.wallup.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dawnimpulse.wallup.R
import com.dawnimpulse.wallup.ui.holders.LoadingHolder
import com.dawnimpulse.wallup.ui.holders.WallupCollectionHolder
import com.dawnimpulse.wallup.ui.interfaces.OnLoadMoreListener
import com.dawnimpulse.wallup.ui.objects.WallupCollectionObject
import com.dawnimpulse.wallup.utils.Config

/**
 * @info -
 *
 * @author - Saksham
 * @note Last Branch Update - master
 *
 * @note Created on 2019-06-11 by Saksham
 * @note Updates :
 */
class WallupCollectionAdapter(
        private val recycler: RecyclerView,
        private val data: MutableList<WallupCollectionObject?>

) : CustomAdapter(Config.disposableCollectionsActivity, recycler) {

    private val VIEW_TYPE = 0
    private val VIEW_LOADING = 1
    private lateinit var loadMoreListener: OnLoadMoreListener

    // --------------------
    //     items count
    // --------------------
    override fun getItemCount(): Int {
        return data.size
    }

    // --------------------
    //      view type
    // --------------------
    override fun getItemViewType(position: Int): Int {
        return if (data[position] == null) VIEW_LOADING else VIEW_TYPE
    }

    // --------------------
    //     create holder
    // --------------------
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (VIEW_TYPE == viewType)
            WallupCollectionHolder(LayoutInflater.from(parent.context).inflate(R.layout.inflator_collections, parent, false))
        else
            LoadingHolder(LayoutInflater.from(parent.context).inflate(R.layout.inflator_loading, parent, false))
    }

    // --------------------
    //     bind holder
    // --------------------
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WallupCollectionHolder) {
            holder.bind(data[position]!!)
        }
    }

    // -----------------------------
    //     dispose progress holder
    // ----------------------------
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is WallupCollectionHolder)
            Config.disposableWallupCollectionsViewHolder[holder.adapterPosition]?.dispose()
    }

    // ------------------------------
    //     dispose progress detach
    // ------------------------------
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        Config.disposableWallupCollectionsViewHolder.forEach { it.value.dispose() }
    }

    // ------------------------
    //     loading items
    // ------------------------
    override fun onLoading() {
        super.onLoading()

        loadMoreListener.onLoadMore()
    }

    // --------------------
    //     set listener
    // --------------------
    fun setOnLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

}