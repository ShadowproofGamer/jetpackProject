package com.example.jetpackproject.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DBItemViewModel(private val repo: DBItemRepository): ViewModel() {
    val dataList = repo.getAllDataStream()

    fun getData(id:Int): Flow<DBItem> {
        return repo.getDataStream(id)
    }

    fun insertData(item: DBItem){
        viewModelScope.launch {
            repo.insertData(item)
        }
    }

    fun updateData(item: DBItem){
        viewModelScope.launch {
            repo.updateData(item)
        }
    }

    fun deleteData(item: DBItem){
        viewModelScope.launch {
            repo.deleteData(item)
        }
    }
}