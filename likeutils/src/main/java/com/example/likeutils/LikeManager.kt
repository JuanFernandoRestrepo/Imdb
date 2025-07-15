package com.example.likeutils

import com.example.likeutils.data.Like
import com.example.likeutils.data.LikeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LikeManager {

    suspend fun toggleLike(itemId: Long, userEmail: String, likeDao: LikeDao): Boolean {
        return withContext(Dispatchers.IO) {
            val liked = likeDao.isItemLiked(itemId, userEmail)
            if (!liked) {
                likeDao.insert(Like(itemId = itemId, userEmail = userEmail))
            } else {
                likeDao.deleteLike(itemId, userEmail)
            }
            !liked
        }
    }

    suspend fun isLiked(itemId: Long, userEmail: String, likeDao: LikeDao): Boolean {
        return withContext(Dispatchers.IO) {
            likeDao.isItemLiked(itemId, userEmail)
        }
    }

    suspend fun getLikesByUser(userEmail: String, likeDao: LikeDao): List<Like> {
        return withContext(Dispatchers.IO) {
            likeDao.getLikesByUser(userEmail)
        }
    }
}