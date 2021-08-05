package com.example.gotrypper_paging.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "NewsData")
data class New(
    @ColumnInfo(name = "Content")
    val news_content: String,

    @ColumnInfo(name = "Title")
    val news_title: String,

    @ColumnInfo(name = "Image")
    val news_image: String,

    @ColumnInfo(name = "Status")
    var status:Boolean = false,

    val favorite: Int,
    val image_source_link: String,
    val image_source_title: String,
    val member_first_name: String,
    val member_id: String,
    val member_last_name: String,
    val member_like: Int,
    val member_profile_pic: String,
    val member_username: String,
    val news_id: String,
    val news_publishdate: String,
    val news_url: String,
    val slug: String,
    val tags: String,
    val thumb_image: String,
    val total_comment: String,
    val total_like: String,
    val total_views: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}