package com.udtt.applegamsung.data.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udtt.applegamsung.R
import java.util.UUID

@Entity
data class ApplePower(
    val name: String = "",
    val description: String = "",
    val minPower: Int = 0,
    val maxPower: Int = 0,

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
) {
    val level: Level
        get() = Level.findById(id)

    enum class Level(val id: String, @DrawableRes val imageRes: Int) {
        ALLERGY("Kly7PEvKx3zTXI28w6uD", R.drawable.lv_0),
        MUGGLE("8Id3umiKylAiHRfDXk7p", R.drawable.lv_1),
        NORMAL("YFBJp89VWswmkIX8o95N", R.drawable.lv_2),
        APPKID("XFb451nOwP6xMG8tnNLa", R.drawable.lv_3),
        APPLE_PREFERRED("uFIE0s2s2A95sYsAQcwI", R.drawable.lv_4),
        APPRICKET("e2zmCYBubznEzTTw814x", R.drawable.lv_5),
        APPRICKET_PRO("uPxani2505JWeynWI5DF", R.drawable.lv_6),
        APPLE_TREE("8ljHZQb4NDRuBoNcFWab", R.drawable.lv_7),
        APPLE_GARDEN("VFK3uW4TZDXjfPHxCowl", R.drawable.lv_8);

        companion object {
            fun findById(id: String): Level = values().first { it.id == id }
        }
    }
}