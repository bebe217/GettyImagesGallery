package com.example.gettyimagesgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gettyimagesgallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val recyclerViewAdapter = ImageRecyclerViewAdapter()

    val viewModel = ImageListViewModel()
    var isLoading = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerView.adapter = recyclerViewAdapter

        viewModel.getNewImages()
        viewModel.imageUrlsLiveData.observe(this, {
            recyclerViewAdapter.updateData(it)
            isLoading = false
        })

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!binding.recyclerView.canScrollVertically(1)) {
                    if (isLoading) return
                    isLoading = true
                    viewModel.getNewImages()
                }
            }
        })
    }
}