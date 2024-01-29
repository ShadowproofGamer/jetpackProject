package com.example.jetpackproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.jetpackproject.db.DBItemViewModelFactory
import com.example.jetpackproject.db.MyDB
import com.example.jetpackproject.db.DBItemRepositoryInstance
import com.example.jetpackproject.ui.theme.JetpackProjectTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = MyDB.getDatabase(application).myDao()
        val repository = DBItemRepositoryInstance(dao)
        val factory = DBItemViewModelFactory(repository)

        setContent {
            JetpackProjectTheme {
                MainComposable(factory = factory)
            }
        }
    }
}
