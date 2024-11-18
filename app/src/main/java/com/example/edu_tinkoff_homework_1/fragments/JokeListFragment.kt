package com.example.edu_tinkoff_homework_1.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.edu_tinkoff_homework_1.JokeListViewModel
import com.example.edu_tinkoff_homework_1.JokeViewModelFactory
import com.example.edu_tinkoff_homework_1.R
import com.example.edu_tinkoff_homework_1.databinding.FragmentJokeListBinding
import com.example.edu_tinkoff_homework_1.recycler.JokeAdapter

class JokeListFragment : Fragment(R.layout.fragment_joke_list) {

    private lateinit var binding: FragmentJokeListBinding
    private lateinit var viewModel: JokeListViewModel
    private val adapter = JokeAdapter { position ->
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, JokeDetailsFragment.newInstance(position))
            .addToBackStack(null)
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJokeListBinding.bind(view)
        createRecyclerViewList()
        initViewModel()
    }

    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
    }

    private fun initViewModel() {
        val factory = JokeViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[JokeListViewModel::class.java]
        viewModel.jokes.observe(viewLifecycleOwner) { jokesList ->
            adapter.updateList(jokesList)
        }
        viewModel.error.observe(viewLifecycleOwner) { showError(it) }
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}