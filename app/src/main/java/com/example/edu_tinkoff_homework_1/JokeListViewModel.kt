package com.example.edu_tinkoff_homework_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edu_tinkoff_homework_1.data.Joke
import kotlin.random.Random

class JokeListViewModel: ViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>().apply{
        value =listOf(
            Joke(Random.nextInt(), "IT", "Почему программисты предпочитают темный режим?", "Потому что свет привлекает баги!"),
            Joke(Random.nextInt(),"Погода", "Какая любимая игра у торнадо?", "Твистер!"),
            Joke(Random.nextInt(), "Животные", "Какой любимый предмет у змей?", "История!"),
            Joke(Random.nextInt(),"Растения", "Почему растения не любят математику?", "Она дает им квадратные корни!"),
            Joke(Random.nextInt(),"Еда", "Почему помидор покраснел?", "Потому что увидел, как заправляют салат!"),
            Joke(Random.nextInt(), "Кулинария", "Почему повар бросил острый перец в суп?", "Чтобы добавить щепотку драйва!"),
            Joke(Random.nextInt(), "Спорт", "Почему футбольный мяч в депрессии?", "Его постоянно бьют по жизни!")
        )
    }
    val jokes: LiveData<List<Joke>> = _jokes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun generateJokes(){

    }

    fun showGeneratedData(){

    }


}