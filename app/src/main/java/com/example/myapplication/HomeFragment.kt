package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("HomeFragment", "onViewCreated called")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val contentViewModel: ContentViewModel by activityViewModels()
        contentViewModel.contentLiveData.observe(viewLifecycleOwner) { content ->
            Log.d("HomeFragment", "Content received: $content")
            val adapter = ContentAdapter(requireContext(), content)
            recyclerView.adapter = adapter
        }

        contentViewModel.loadContent()
    }
}
