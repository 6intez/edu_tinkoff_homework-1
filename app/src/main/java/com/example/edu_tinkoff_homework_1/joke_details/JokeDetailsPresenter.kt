package com.example.edu_tinkoff_homework_1.joke_details

import android.icu.text.Transliterator.Position
import com.example.edu_tinkoff_homework_1.JokeListActivity
import com.example.edu_tinkoff_homework_1.data.Joke

class JokeDetailsPresenter(
    private val view: JokeDetailsView
) {
    fun loadJokeDetails(jokePosition: Int){
        if (jokePosition == -1) {
            view.showErrorAndCloseScreen("Invalid joke position")
        } else {
            (JokeListActivity.jokes[jokePosition] as? Joke)?.let {
                view.showJokeInfo(it)
            } ?: view.showErrorAndCloseScreen("Invalid joke data!")
        }
    }
}