package com.udtt.applegamsung.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udtt.applegamsung.data.dao.CategoriesDao
import com.udtt.applegamsung.data.dao.ProductsDao
import com.udtt.applegamsung.data.dao.AppleBoxItemsDao
import com.udtt.applegamsung.data.dao.TestResultsDao
import com.udtt.applegamsung.data.entity.*
import com.udtt.applegamsung.data.util.DateTypeConverter

@TypeConverters(DateTypeConverter::class)
@Database(
    entities = [Category::class, Product::class, TestResult::class, AppleBoxItem::class, ApplePower::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun categoriesDao(): CategoriesDao

    abstract fun productsDao(): ProductsDao

    abstract fun testResultsDao(): TestResultsDao

    abstract fun appleBoxItemsDao(): AppleBoxItemsDao

}