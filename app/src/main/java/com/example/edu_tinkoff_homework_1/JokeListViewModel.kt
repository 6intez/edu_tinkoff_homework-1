package com.example.edu_tinkoff_homework_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edu_tinkoff_homework_1.data.Joke
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class JokeListViewModel: ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        _jokes.value = emptyList()
   }

    fun addJoke(newJoke: Joke) {
        viewModelScope.launch {
            try {
                delay(1000)
                val currentList = _jokes.value.orEmpty()
                val updatedList = currentList + newJoke
                _jokes.value = updatedList
            } catch (e: Exception) {
                _error.value = "Error adding joke: ${e.message}"
            }
        }
    }


    private fun loadJokes() {
        viewModelScope.launch {
            try {
                delay(500)
                val loadedJokes = listOf(
                    Joke(Random.nextInt(), "IT", "Почему программисты предпочитают темный режим?", "Потому что свет привлекает баги!"),
                    Joke(Random.nextInt(), "Погода", "Какая любимая игра у торнадо?", "Твистер!"),
                    Joke(Random.nextInt(), "Животные", "Какой любимый предмет у змей?", "История!"),
                    Joke(Random.nextInt(), "Растения", "Почему растения не любят математику?", "Она дает им квадратные корни!"),
                    Joke(Random.nextInt(), "Еда", "Почему помидор покраснел?", "Потому что увидел, как заправляют салат!"),
                    Joke(Random.nextInt(), "Кулинария", "Почему повар бросил острый перец в суп?", "Чтобы добавить щепотку драйва!"),
                    Joke(Random.nextInt(), "Спорт", "Почему футбольный мяч в депрессии?", "Его постоянно бьют по жизни!")
                )
                _jokes.postValue(loadedJokes)
            } catch (e: Exception) {
                _error.value = "Error loading jokes: ${e.message}"
            }
        }
    }
}