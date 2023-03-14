package com.example.notes.ui

import android.view.View
import com.example.notes.databinding.ActivityEditBinding
import com.example.notes.databinding.ActivityMainBinding

class VisibilitySetting {

    fun setVisibilityIsEmptyDB(binding: ActivityMainBinding) {
        binding.recVew.visibility = View.GONE
        binding.searchView.visibility = View.GONE
        binding.txtEmpty.visibility = View.VISIBLE
    }

    fun setVisibilityIsNotEmptyDB(binding: ActivityMainBinding) {
        binding.recVew.visibility = View.VISIBLE
        binding.searchView.visibility = View.VISIBLE
        binding.txtEmpty.visibility = View.GONE
    }

    fun setVisibilityForDelBtn (binding: ActivityEditBinding) {
        binding.btnDelete.visibility = View.VISIBLE
    }
}