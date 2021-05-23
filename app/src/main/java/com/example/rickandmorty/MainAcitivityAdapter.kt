package com.example.rickandmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rickandmorty.databinding.RockyMortyItemBinding
//import com.example.rickandmorty.R
import com.example.rickandmorty.network.Result


class MainAcitivityAdapter : RecyclerView.Adapter<MainAcitivityAdapter.ViewHolder>(){

    inner class ViewHolder( val binding: RockyMortyItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItem(result: Result){
            binding.name.text = result.name
            binding.status.text = result.status
            binding.species.text = result.species
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }


    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RockyMortyItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        val imgUri = item.image
            .toUri().buildUpon().scheme("https").build()
        Glide.with(holder.itemView).load(imgUri).apply(
            RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(holder.binding.imageView)
        holder.bindItem(item)
//        holder.itemView.apply {
//            Glide.with(context!!).load(imgUri).apply(
//                RequestOptions().placeholder(R.drawable.loading_animation)
//                .error(R.drawable.ic_broken_image))
//                .into()
//
//        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}