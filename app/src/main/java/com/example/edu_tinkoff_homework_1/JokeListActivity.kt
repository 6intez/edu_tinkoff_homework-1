package com.example.edu_tinkoff_homework_1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.ActivityJokeListBinding
import com.example.edu_tinkoff_homework_1.joke_details.JokeDetailsActivity
import com.example.edu_tinkoff_homework_1.recycler.JokeAdapter
import com.example.edu_tinkoff_homework_1.JokeListViewModel
import kotlin.random.Random

class JokeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeListBinding
    private lateinit var viewModel: JokeListViewModel
    companion object {
        val jokes = listOf(
            Joke(Random.nextInt(), "IT", "Почему программисты предпочитают темный режим?", "Потому что свет привлекает баги!"),
            Joke(Random.nextInt(),"Погода", "Какая любимая игра у торнадо?", "Твистер!"),
            Joke(Random.nextInt(), "Животные", "Какой любимый предмет у змей?", "История!"),
            Joke(Random.nextInt(),"Растения", "Почему растения не любят математику?", "Она дает им квадратные корни!"),
            Joke(Random.nextInt(),"Еда", "Почему помидор покраснел?", "Потому что увидел, как заправляют салат!"),
            Joke(Random.nextInt(), "Кулинария", "Почему повар бросил острый перец в суп?", "Чтобы добавить щепотку драйва!"),
            Joke(Random.nextInt(), "Спорт", "Почему футбольный мяч в депрессии?", "Его постоянно бьют по жизни!")
        )
    }

    private val adapter = JokeAdapter { position ->
        startActivity(JokeDetailsActivity.getInstance(this, position))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerViewList()
        initViewModel()

    }


    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
    }

    private  fun initViewModel(){
        val factory = JokeViewModelFactory()
        viewModel = ViewModelProvider(this,factory)[JokeListViewModel::class.java]
        viewModel.jokes.observe(this){ jokesList ->
            adapter.updateList(jokesList)}
        viewModel.error.observe(this){showError(it)}
    }

    private fun showError(it: String?){
        Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
    }
}




