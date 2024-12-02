package com.example.edu_tinkoff_homework_1.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edu_tinkoff_homework_1.JokeListViewModel
import com.example.edu_tinkoff_homework_1.JokeViewModelFactory
import com.example.edu_tinkoff_homework_1.R
import com.example.edu_tinkoff_homework_1.databinding.FragmentJokeListBinding
import com.example.edu_tinkoff_homework_1.recycler.JokeAdapter
import kotlinx.coroutines.launch

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

        observeViewModel()

        binding.addJokeButton.setOnClickListener {
            AddJokeDialogFragment(viewModel).show(parentFragmentManager, "AddJokeDialog")
        }
    }

    private fun createRecyclerViewList() {
        binding.recyclerView.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(), 1)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (layoutManager.findLastVisibleItemPosition() == adapter.itemCount - 1) {
                    viewModel.loadJokesFromApi()
                }
            }
        })
    }

    private fun initViewModel() {
        val factory = JokeViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), factory)[JokeListViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.jokes.observe(viewLifecycleOwner) { jokes ->
                adapter.updateList(jokes)

                if (jokes.isEmpty()) {
                    binding.emptyText.visibility = View.VISIBLE
                } else {
                    binding.emptyText.visibility = View.GONE
                }
            }

            viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }

    private fun showError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage ?: "Ошибка загрузки данных", Toast.LENGTH_SHORT).show()
    }
}