package com.telegramplus.app.data.local.dao

import androidx.room.*
import com.telegramplus.app.data.model.ChatFolder
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatFolderDao {
    
    @Query("SELECT * FROM folders ORDER BY position ASC")
    fun getAllFolders(): Flow<List<ChatFolder>>
    
    @Query("SELECT * FROM folders WHERE id = :folderId")
    suspend fun getFolderById(folderId: String): ChatFolder?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: ChatFolder)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolders(folders: List<ChatFolder>)
    
    @Update
    suspend fun updateFolder(folder: ChatFolder)
    
    @Delete
    suspend fun deleteFolder(folder: ChatFolder)
    
    @Query("DELETE FROM folders WHERE id = :folderId")
    suspend fun deleteFolderById(folderId: String)
}
