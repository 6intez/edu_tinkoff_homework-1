package com.example.edu_tinkoff_homework_1.joke_details

import com.example.edu_tinkoff_homework_1.data.Joke

interface JokeDetailsView {
    fun showJokeInfo(joke: Joke)

    fun showErrorAndCloseScreen(errorMessage: String)
}