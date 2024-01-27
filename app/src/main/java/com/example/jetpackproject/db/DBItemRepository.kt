package com.example.jetpackproject.db

import kotlinx.coroutines.flow.Flow

interface DBItemRepository {

        fun getAllDataStream(): Flow<List<DBItem>>

        fun getDataStream(id: Int): Flow<DBItem>

        suspend fun insertData(item: DBItem)

        suspend fun updateData(item: DBItem)

        suspend fun deleteData(item: DBItem)
    }
