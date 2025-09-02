package pl.xsware.domain.model.dto.budget

import pl.xsware.domain.model.entity.budget.PeriodType

data class BudgetReq(

    val userId: Long,
    val periodType: PeriodType?,
    val categoryId: Long?
)
