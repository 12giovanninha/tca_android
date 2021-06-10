package com.example.tca.models.game

import com.example.tca.models.category.Category
import com.example.tca.models.problem.Answer

data class NextProblem(
    var status: String,
    var message: String,
    var data: NextProblemData?
)

data class NextProblemData(
    var problem: NextProblemFields
)

data class NextProblemFields(
    var question: String,
    var difficulty: String,
    var category: Category,
    var answers: List<Answer>
) {
    var id: Long? = null
}