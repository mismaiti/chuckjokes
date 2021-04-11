package com.mismaiti.chuckjokes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.databinding.ItemCategoryBinding
import com.mismaiti.chuckjokes.ui.home.HomeFragmentDirections
import java.util.*
import kotlin.collections.ArrayList

class CategoriesAdapter(private val context: Context?)
    : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoryList: ArrayList<JokesEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun loadCategoryList(list: List<JokesEntity>?) {
        categoryList.clear()
        categoryList = list?.toMutableList() as ArrayList<JokesEntity>
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(private val itemBinding: ItemCategoryBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(jokesEntity: JokesEntity) {
            with(itemBinding) {
                if (jokesEntity.category?.isEmpty() == true) {
                    itemCategoryName.visibility = View.GONE
                }
                else {
                    itemCategoryName.visibility = View.VISIBLE
                    itemCategoryName.text = jokesEntity.category?.capitalize()
                }

                Glide.with(itemView.context).load(jokesEntity.iconUrl)
                    .into(itemCategoryIcon)
                itemCategoryValue.text = jokesEntity.value
            }

            itemView.setOnClickListener {
                val action = jokesEntity.category?.let { it1 ->
                    HomeFragmentDirections.actionNavigationHomeToNavigationCategories(
                        it1
                    )
                }
                val navController = Navigation.findNavController(it)
                if (action != null) {
                    navController.navigate(action)
                }
            }
        }

    }
}
