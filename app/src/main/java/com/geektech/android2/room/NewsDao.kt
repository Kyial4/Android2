package com.geektech.android2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.geektech.android2.models.News


@Dao
interface NewsDao {

    @Query("SELECT* FROM news")
    fun getall ():List<News?>?

    @Query("SELECT* FROM news order by createdAt DESC")
    fun sortAll ():List<News?>?

    @Insert
    fun insert (news:News);

    @Delete
    fun delete(news: News?)

    @Query("SELECT * FROM news WHERE tittle LIKE '%' || :search || '%'")
    fun getSearch(search: String?): List<News?>?


}
