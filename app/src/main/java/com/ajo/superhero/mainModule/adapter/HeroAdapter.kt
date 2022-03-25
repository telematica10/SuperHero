package com.ajo.superhero.mainModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajo.superhero.Hero
import com.ajo.superhero.HeroResponse
import com.ajo.superhero.R
import com.ajo.superhero.databinding.ItemHeroBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class HeroAdapter(private var listener: OnClickListener) :
    ListAdapter<HeroResponse, RecyclerView.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_hero, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hero = getItem(position)

        with(holder as ViewHolder){
            setListener(hero)

            binding.tvNameHero.text = hero.name

            Glide.with(mContext)
                .load(hero.url.)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgHero)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemHeroBinding.bind(view)

        fun setListener(hero: HeroResponse){
            with(binding.root) {
                setOnClickListener { listener.onClick(hero) }

            }
        }
    }

