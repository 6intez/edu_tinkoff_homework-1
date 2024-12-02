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

    private val currentJokes = mutableListOf<Joke>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        loadJokes()
        viewModelScope.launch {
            loadJokesFromApi()
        }
   }




    fun addJoke(newJoke: Joke) {
        viewModelScope.launch {
            try {
                newJoke.source = "Local"
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
                _isLoading.value = true
                delay(500)
                val loadedJokes = listOf(
                    Joke(Random.nextInt(), "IT", "Почему программисты предпочитают темный режим?", "Потому что свет привлекает баги!","Local"),
                    Joke(Random.nextInt(), "Погода", "Какая любимая игра у торнадо?", "Твистер!","Local"),
                    Joke(Random.nextInt(), "Животные", "Какой любимый предмет у змей?", "История!","Local"),
                    Joke(Random.nextInt(), "Растения", "Почему растения не любят математику?", "Она дает им квадратные корни!","Local"),
                    Joke(Random.nextInt(), "Еда", "Почему помидор покраснел?", "Потому что увидел, как заправляют салат!","Local"),
                    Joke(Random.nextInt(), "Кулинария", "Почему повар бросил острый перец в суп?", "Чтобы добавить щепотку драйва!","Local"),
                    Joke(Random.nextInt(), "Спорт", "Почему футбольный мяч в депрессии?", "Его постоянно бьют по жизни!","Local")
                )
                val currentList = _jokes.value.orEmpty()
                val updatedList = currentList + loadedJokes
                _jokes.value = updatedList
            } catch (e: Exception) {
                _error.value = "Error loading jokes: ${e.message}"
            } finally {
                _isLoading.value = false
            }

        }
    }

    fun loadJokesFromApi() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                delay(2000)
                val response = RetrofitInstance.api.getJokes()
                val apiJokes = response.jokes.map { joke ->
                    Joke(
                        id = joke.id,
                        category = joke.category,
                        question = joke.setup,
                        answer = joke.delivery,
                        source = "Network"
                    )
                }
                val currentList = _jokes.value.orEmpty()
                val updatedList = currentList + apiJokes
                _jokes.value = updatedList
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}