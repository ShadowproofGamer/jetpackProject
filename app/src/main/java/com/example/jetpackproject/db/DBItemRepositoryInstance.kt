package com.example.jetpackproject.db;

import kotlinx.coroutines.flow.Flow

class DBItemRepositoryInstance(private val myDao: MyDao) : DBItemRepository {
    override fun getAllDataStream(): Flow<List<DBItem>> {
        return myDao.getAllData()
    }

    override fun getDataStream(id: Int): Flow<DBItem> {
        return myDao.getData(id)
    }

    override suspend fun insertData(item: DBItem) {
        myDao.insert(item)
    }

    override suspend fun updateData(item: DBItem) {
        myDao.update(item)
    }

    override suspend fun deleteData(item: DBItem) {
        myDao.delete(item)
    }

}