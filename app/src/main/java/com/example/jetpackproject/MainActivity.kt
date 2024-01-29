package com.example.jetpackproject

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackproject.db.DBItemViewModelFactory
import com.example.jetpackproject.db.MyDB
import com.example.jetpackproject.db.OfflineDBItemRepository
import com.example.jetpackproject.destinations.ItemList
import com.example.jetpackproject.ui.theme.JetpackProjectTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = MyDB.getDatabase(application).myDao()
        val repository = OfflineDBItemRepository(dao)
        val factory = DBItemViewModelFactory(repository)

        setContent {
            JetpackProjectTheme {
                MainComposable(factory = factory)
            }
        }
    }
}
