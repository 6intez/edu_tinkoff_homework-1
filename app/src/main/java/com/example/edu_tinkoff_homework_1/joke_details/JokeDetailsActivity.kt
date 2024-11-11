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

class JokeDetailsActivity : AppCompatActivity(), JokeDetailsView {

    private var jokePosition: Int = -1

    private lateinit var binding: ActivityJokeDetailsBinding

    private lateinit var presenter: JokeDetailsPresenter
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
        presenter = JokeDetailsPresenter(this)
        handleExtra()
    }



    private fun handleExtra(){
        jokePosition = intent.getIntExtra(JOKE_POSITION_EXTRA, -1)
        presenter.loadJokeDetails(jokePosition)
    }



    override fun showJokeInfo(joke: Joke) {
        with(binding) {
            jokeCategory.text = "Категория: ${joke.category}"
            jokeQuestion.text = "Вопрос: ${joke.question}"
            jokeAnswer.text = "Ответ:  ${joke.answer}"
        }
    }

    override fun showErrorAndCloseScreen(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        finish()
    }
}