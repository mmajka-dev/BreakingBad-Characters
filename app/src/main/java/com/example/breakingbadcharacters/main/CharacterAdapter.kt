package com.example.breakingbadcharacters.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.models.CharacterItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class CharacterAdapter(val characters: ArrayList<CharacterItem>, val onClickListener: onClickListener): RecyclerView.Adapter<CharacterViewHolder>(), Filterable {
    var characterFilterList = ArrayList<CharacterItem>()

    init {
       characterFilterList = characters
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val characterView = inflater.inflate(R.layout.rv_item, parent, false)
        return CharacterViewHolder(characterView)
    }

    override fun getItemCount(): Int {
        return characterFilterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val bind = characterFilterList.get(position)
        holder.bind(bind, onClickListener)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()){
                    characterFilterList = characters
                }else{
                    val results = ArrayList<CharacterItem>()
                    for (row in characters){
                        if (row.name.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))){
                            results.add(row)
                        }
                    }
                    characterFilterList = characters
                }
                val filterResults = FilterResults()
                filterResults.values = characterFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                characterFilterList = results?.values as ArrayList<CharacterItem>
                notifyDataSetChanged()
            }

        }
    }

}

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view){
    val img = itemView.image
    val name = itemView.name

    fun bind(character: CharacterItem, onClickListener: onClickListener){
        Picasso.get().load(character.img).into(img)
        name.text = character.name
    }
}

interface onClickListener{
    fun onClick(position: Int, view: View, character: CharacterItem)
}