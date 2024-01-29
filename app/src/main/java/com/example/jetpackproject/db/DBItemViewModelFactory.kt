package com.example.jetpackproject.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class DBItemViewModelFactory(private val repo: DBItemRepository): NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DBItemViewModel(repo) as T
}