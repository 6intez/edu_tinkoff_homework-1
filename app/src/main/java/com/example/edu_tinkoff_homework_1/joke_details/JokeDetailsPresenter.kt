package com.example.edu_tinkoff_homework_1.joke_details

import android.icu.text.Transliterator.Position
import android.util.Log
import com.example.edu_tinkoff_homework_1.JokeListViewModel
import com.example.edu_tinkoff_homework_1.data.Joke

class JokeDetailsPresenter(
    private val view: JokeDetailsView,
    private val jokeListViewModel: JokeListViewModel
) {

    fun loadJokeDetails(jokePosition: Int) {
        if (jokePosition == -1) {
            view.showErrorAndCloseScreen("Invalid joke position")
        } else {

            val jokes = jokeListViewModel.jokes.value

            val selectedJoke = jokes?.getOrNull(jokePosition)

            if (selectedJoke != null) {
                view.showJokeInfo(selectedJoke)
            } else {
                view.showErrorAndCloseScreen("Invalid joke data!")
            }
        }
    }
}
