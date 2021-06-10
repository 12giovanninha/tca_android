package com.example.tca.models.category

data class Category(
    var success: String,
    var message: String,
    var data: CategoryData?
)

data class CategoryData(
    val categories: List<OutCategory>
)

data class OutCategory(
    var name: String,
    var id: Int
)