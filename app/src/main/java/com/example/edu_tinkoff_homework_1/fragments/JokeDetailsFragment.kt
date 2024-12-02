package com.example.edu_tinkoff_homework_1.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.edu_tinkoff_homework_1.JokeListViewModel
import com.example.edu_tinkoff_homework_1.R
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.FragmentJokeDetailsBinding
import com.example.edu_tinkoff_homework_1.joke_details.JokeDetailsView

class JokeDetailsFragment : Fragment(R.layout.fragment_joke_details), JokeDetailsView {

    private var jokePosition: Int = -1
    private lateinit var binding: FragmentJokeDetailsBinding
    private lateinit var jokeListViewModel: JokeListViewModel

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
        jokeListViewModel = ViewModelProvider(requireActivity())[JokeListViewModel::class.java]
        jokePosition = arguments?.getInt(JOKE_POSITION) ?: -1
        Log.d("JokeDetailsFragment", "Received position: $jokePosition")
        jokeListViewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            if (jokePosition != -1) {
                val selectedJoke = jokes.getOrNull(jokePosition)
                if (selectedJoke != null) {
                    showJokeInfo(selectedJoke)
                } else {
                    showErrorAndCloseScreen("Joke not found")
                }
            } else {
                showErrorAndCloseScreen("Invalid joke position")
            }
        }
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

    override fun getLifecycleOwner(): LifecycleOwner = viewLifecycleOwner
}


