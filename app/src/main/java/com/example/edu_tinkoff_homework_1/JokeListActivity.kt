package com.example.edu_tinkoff_homework_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.ActivityJokeListBinding
import com.example.edu_tinkoff_homework_1.recycler.JokeAdapter

class JokeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeListBinding

    private val adapter = JokeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createRecyclerViewList()

        val jokes = listOf(
            Joke("IT", "Почему программисты предпочитают темный режим?", "Потому что свет привлекает баги!"),
            Joke("Погода", "Какая любимая игра у торнадо?", "Твистер!"),
            Joke("Животные", "Какой любимый предмет у змей?", "История!"),
            Joke("Растения", "Почему растения не любят математику?", "Она дает им квадратные корни!"),
            Joke("Еда", "Почему помидор покраснел?", "Потому что увидел, как заправляют салат!"),
            Joke("Кулинария", "Почему повар бросил острый перец в суп?", "Чтобы добавить щепотку драйва!"),
            Joke("Спорт", "Почему футбольный мяч в депрессии?", "Его постоянно бьют по жизни!")
        )
        adapter.updateList(jokes)
    }


    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)
    }
}