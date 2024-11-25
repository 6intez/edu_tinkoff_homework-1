package com.example.edu_tinkoff_homework_1.recycler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edu_tinkoff_homework_1.data.Joke
import com.example.edu_tinkoff_homework_1.databinding.JokeItemBinding
import com.example.edu_tinkoff_homework_1.recycler.util.JokeDiffUtilCallback

class JokeAdapter(
    private var data: List<Joke> = emptyList(),
    private val clickListener: (Int) -> Unit,
): RecyclerView.Adapter<JokeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = JokeItemBinding.inflate(inflater)
        return JokeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                handleJokeClick(adapterPosition)
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun updateList(newData: List<Joke>){
        val diffUtilCallback = JokeDiffUtilCallback(data, newData)
        val calculatedDiff = DiffUtil.calculateDiff(diffUtilCallback)
        data = newData
        calculatedDiff.dispatchUpdatesTo(this)
    }

    private fun handleJokeClick(position: Int){
        if (position != RecyclerView.NO_POSITION) {
            clickListener(position)
        }

    }
}