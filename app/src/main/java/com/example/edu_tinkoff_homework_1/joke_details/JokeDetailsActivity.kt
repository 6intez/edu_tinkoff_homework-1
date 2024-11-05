package com.example.edu_tinkoff_homework_1.joke_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.edu_tinkoff_homework_1.JokeListActivity
import com.example.edu_tinkoff_homework_1.R
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.ActivityJokeDetailsBinding
import com.example.edu_tinkoff_homework_1.databinding.ActivityJokeListBinding

class JokeDetailsActivity : AppCompatActivity() {

    private var jokePosition: Int = -1

    private lateinit var binding: ActivityJokeDetailsBinding
    companion object {
        private const val JOKE_POSITION_EXTRA = "JOKE_POSITION"

        fun getInstance(context: Context, jokePosition: Int): Intent {
            return Intent(context, JokeDetailsActivity::class.java).apply{
                putExtra(JOKE_POSITION_EXTRA, jokePosition)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleExtra()
    }

    private fun handleError() {
        Toast.makeText(this, "Invalid joke data ", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun handleExtra(){
        jokePosition = intent.getIntExtra(JOKE_POSITION_EXTRA, -1)

        if (jokePosition == -1) {
            handleError()
        } else {
            val item = JokeListActivity.jokes[jokePosition] as? Joke

            if (item != null) {
                setupJokeData(item)
            } else {
                handleError()
            }
        }
    }

    private fun setupJokeData(joke: Joke){
        with(binding) {
            jokeCategory.text = "Категория: ${joke.category}"
            jokeQuestion.text = "Вопрос: ${joke.question}"
            jokeAnswer.text = "Ответ:  ${joke.answer}"
        }

    }


}