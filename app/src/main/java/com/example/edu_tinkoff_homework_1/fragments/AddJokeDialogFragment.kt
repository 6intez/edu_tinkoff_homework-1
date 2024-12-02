package com.example.edu_tinkoff_homework_1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.edu_tinkoff_homework_1.JokeListViewModel
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.DialogAddJokeBinding
import kotlinx.coroutines.launch
import kotlin.random.Random

class AddJokeDialogFragment(private val viewModel: JokeListViewModel) : DialogFragment() {

    private var _binding: DialogAddJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAddJoke.setOnClickListener {
            val category = binding.inputCategory.text.toString()
            val question = binding.inputQuestion.text.toString()
            val answer = binding.inputAnswer.text.toString()
            val source = "Local"

            if (category.isBlank() || question.isBlank() || answer.isBlank()) {
                Toast.makeText(requireContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
            } else {
                val newJoke = Joke(Random.nextInt(), category, question, answer,source)
                lifecycleScope.launch {
                    viewModel.addJoke(newJoke)
                    dismiss()
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
