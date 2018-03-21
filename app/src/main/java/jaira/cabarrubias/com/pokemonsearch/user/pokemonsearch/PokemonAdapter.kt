package jaira.cabarrubias.com.pokemonsearch.user.pokemonsearch

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by user on 20/03/2018.
 */
class PokemonAdapter (private val pokemon: ArrayList<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.CustomVIewHolder>() {

    override fun getItemCount(): Int {
        return pokemon.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomVIewHolder?, position: Int) {
        holder?.itemView?.tvHeight?.text = pokemon[position].height
        holder?.itemView?.tV_weight?.text = pokemon[position].weight
        holder?.itemView?.baseStat_HP?.text = pokemon[position].hp.stats
        holder?.itemView?.baseStat_Attack?.text = pokemon[position].attack.stats
        holder?.itemView?.baseStat_Defense?.text = pokemon[position].defence.stats
        holder?.itemView?.baseStat_SpecialAttack?.text = pokemon[position].spAttack.stats
        holder?.itemView?.baseStat_SpecialDefense?.text = pokemon[position].spDefence.stats
        holder?.itemView?.baseStat_Speed?.text = pokemon[position].speed.stats

        holder?.itemView?.tvPokemonType?.text = pokemon[position].type.substring(0,1).toUpperCase() + pokemon[position].type.substring(1)
        holder?.itemView?.tvPokemonAbilities?.text = pokemon[position].abilities.substring(0, 1).toUpperCase() + pokemon[position].abilities.substring(1)

        holder?.view?.tvMove1?.text = pokemon[position].moves1.pokemonMove.substring(0, 1).toUpperCase() + pokemon[position].moves1.pokemonMove.substring(1)
        holder?.view?.tV_move2?.text = pokemon[position].moves2.pokemonMove.substring(0, 1).toUpperCase() + pokemon[position].moves2.pokemonMove.substring(1)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomVIewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_main,parent,false)
        return CustomVIewHolder(itemView)
    }

    class CustomVIewHolder (var view: View) : RecyclerView.ViewHolder(view){

    }

}