package com.vasyancoder.cameraandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vasyancoder.cameraandroid.databinding.ItemGalleryListBinding
import java.io.File

class GalleryAdapter(
    private val images: List<String>,
    private val context: Context
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var lines: List<String>?

    init {
        val filename = "date.txt"
        val dir = context.filesDir
        val fileDate = File(dir, filename)
        lines = try {
            fileDate.readLines()
        } catch (e: Exception) {
            null
        }
    }

    class GalleryViewHolder(val binding: ItemGalleryListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = ItemGalleryListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GalleryViewHolder(binding)
    }

    override fun getItemCount() = lines?.size ?: 0

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
//        val image = images[position]
        val date = lines?.get(position) ?: ""

        holder.binding.dateText.text = date
//        Glide.with(context).load(image).into(holder.binding.galleryImage)
    }
}