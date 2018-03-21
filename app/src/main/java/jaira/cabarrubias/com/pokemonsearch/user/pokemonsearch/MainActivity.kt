package jaira.cabarrubias.com.pokemonsearch.user.pokemonsearch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val url = "https://pokeapi.co/api/v2/pokemon/"
    private var addPokemon = ArrayList<Pokemon>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.visibility = View.GONE

        btnSearch.setOnClickListener{
            addPokemon.clear()
            progressBar.visibility =View.VISIBLE
            progressBar.visibility = View.GONE

            fetchPokemons()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
    }


    private fun fetchPokemons() {

        doAsync {
            val tempName = PokemonSearch.text.toString()
            val resultJson = URL(url +tempName).readText()
            val jsonObject = JSONObject(resultJson)
            val pokemonID = jsonObject.getInt("id")
            val pokemonName = jsonObject.getString("name")
            val pokemonSprites = jsonObject.getJSONObject("sprites").getString("front_default")

            val pokemonType = jsonObject.getJSONArray("types").getJSONObject(0)
                    .getJSONObject("type").getString("name")

            val pokemonAbilities = jsonObject.getJSONArray("abilities").getJSONObject(0)
                    .getJSONObject("ability").getString("name")

            val pokemonHeight = jsonObject.getString("height")
            val pokemonWeight = jsonObject.getString("weight")

            val pokemonMoves1 = jsonObject.getJSONArray("moves").getJSONObject(0)
                    .getJSONObject("move").getString("name")
            val pokemonMoves2 = jsonObject.getJSONArray("moves").getJSONObject(1)
                    .getJSONObject("move").getString("name")

            val pokemonHP = jsonObject.getJSONArray("stats").getJSONObject(0).getString("base_stat")
            val pokemonAttack = jsonObject.getJSONArray("stats").getJSONObject(1).getString("base_stat")
            val pokemonDefense = jsonObject.getJSONArray("stats").getJSONObject(2).getString("base_stat")
            val pokemonSpecialAttack = jsonObject.getJSONArray("stats").getJSONObject(3).getString("base_stat")
            val pokemonSpecialDefense = jsonObject.getJSONArray("stats").getJSONObject(4).getString("base_stat")
            val pokemonSpeed = jsonObject.getJSONArray("stats").getJSONObject(5).getString("base_stat")

            uiThread {
                recyclerView.adapter = PokemonAdapter(addPokemon)
                addPokemon.add(Pokemon(Sprites(pokemonSprites),
                        pokemonID,
                        pokemonName,
                        pokemonType,
                        pokemonAbilities,
                        pokemonType,
                        pokemonHeight,
                        pokemonWeight,
                        Move(pokemonMoves1),
                        Move(pokemonMoves2),
                        baseStats(pokemonHP),
                        baseStats(pokemonAttack),
                        baseStats(pokemonDefense),
                        baseStats(pokemonSpecialAttack),
                        baseStats(pokemonSpecialDefense),
                        baseStats(pokemonSpeed)
                ))

                when {
                    pokemonID < 10 -> tV_id.text = "#00" + pokemonID
                    pokemonID > 100 -> tV_id.text = "#" + pokemonID
                    pokemonID > 10 -> tV_id.text = "#0" + pokemonID
                }
                tvPokemon.text = pokemonName.substring(0, 1).toUpperCase() + pokemonName.substring(1)
                tvPokemonType.text = pokemonType.substring(0, 1).toUpperCase() + pokemonType.substring(1)
                tvPokemonAbilities.text = pokemonAbilities.substring(0, 1).toUpperCase() + pokemonAbilities.substring(1)
                tvHeight.text = pokemonHeight.substring(0, 1).toUpperCase() + pokemonHeight.substring(1)
                tV_weight.text = pokemonWeight.substring(0, 1).toUpperCase() + pokemonWeight.substring(1)

                tvMove1.text = pokemonMoves1.substring(0, 1).toUpperCase() + pokemonMoves1.substring(1)
                tV_move2.text = pokemonMoves2.substring(0, 1).toUpperCase() + pokemonMoves2.substring(1)

                baseStat_HP.text = pokemonHP.substring(0, 1).toUpperCase() + pokemonHP.substring(1)
                baseStat_Attack.text = pokemonAttack.substring(0, 1).toUpperCase() + pokemonAttack.substring(1)
                baseStat_Defense.text = pokemonDefense.substring(0, 1).toUpperCase() + pokemonDefense.substring(1)
                baseStat_SpecialAttack.text = pokemonSpecialAttack.substring(0, 1).toUpperCase() + pokemonSpecialAttack.substring(1)
                baseStat_SpecialDefense.text = pokemonSpecialDefense.substring(0, 1).toUpperCase() + pokemonSpecialDefense.substring(1)
                baseStat_Speed.text = pokemonSpeed.substring(0, 1).toUpperCase() + pokemonSpeed.substring(1)


                Picasso.with(this@MainActivity).load(pokemonSprites).into(imgPokemon)

                progressBar.visibility = View.GONE
                relativelayout.visibility = View.VISIBLE
                tvPokemonType.visibility = View.VISIBLE
                scrollView2.visibility = View.VISIBLE


            }
        }
    }
}
