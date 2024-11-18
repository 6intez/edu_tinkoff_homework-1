package com.example.edu_tinkoff_homework_1.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.edu_tinkoff_homework_1.R
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.FragmentJokeDetailsBinding
import com.example.edu_tinkoff_homework_1.joke_details.JokeDetailsPresenter
import com.example.edu_tinkoff_homework_1.joke_details.JokeDetailsView

class JokeDetailsFragment : Fragment(R.layout.fragment_joke_details), JokeDetailsView {

    private var jokePosition: Int = -1
    private lateinit var binding: FragmentJokeDetailsBinding
    private lateinit var presenter: JokeDetailsPresenter

    companion object {
        private const val JOKE_POSITION = "joke_position"

        fun newInstance(jokePosition: Int): JokeDetailsFragment {
            val fragment = JokeDetailsFragment()
            val bundle = Bundle()
            bundle.putInt(JOKE_POSITION, jokePosition)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJokeDetailsBinding.bind(view)
        presenter = JokeDetailsPresenter(this)
        jokePosition = arguments?.getInt(JOKE_POSITION) ?: -1
        presenter.loadJokeDetails(jokePosition)
    }

    override fun showJokeInfo(joke: Joke) {
        with(binding) {
            jokeCategory.text = "Категория: ${joke.category}"
            jokeQuestion.text = "Вопрос: ${joke.question}"
            jokeAnswer.text = "Ответ: ${joke.answer}"
        }
    }

    override fun showErrorAndCloseScreen(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        parentFragmentManager.popBackStack()
    }
}