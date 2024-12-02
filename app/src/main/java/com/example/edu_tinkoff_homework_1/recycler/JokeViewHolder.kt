package com.example.edu_tinkoff_homework_1.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.JokeItemBinding

class JokeViewHolder(
    private val binding: JokeItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke){
        binding.jokeCategory.text = joke.category
        binding.jokeQuestion.text = joke.question
        binding.jokeAnswer.text = joke.answer
        binding.jokeSource.text = joke.source
    }
}