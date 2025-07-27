package ru.yavshok.app.data.model

data class ExperimentResponse(
    val flags: Flags
)

data class Flags(
    val age: AgeExperiment
)

data class AgeExperiment(
    val enabled: Boolean,
    val young: AgeRange,
    val adult: AgeRange,
    val old: AgeRange,
    val oldFrom: Int,
    val youngFrom: Int
)

data class AgeRange(
    val from: Int,
    val to: Int
)

enum class AgeGroup {
    YOUNG, ADULT, OLD, UNKNOWN
}

fun determineAgeGroup(age: Int, experiment: AgeExperiment): AgeGroup {
    return when {
        age in experiment.young.from..experiment.young.to -> AgeGroup.YOUNG
        age in experiment.adult.from..experiment.adult.to -> AgeGroup.ADULT
        age in experiment.old.from..experiment.old.to -> AgeGroup.OLD
        else -> AgeGroup.UNKNOWN
    }
}

fun getAgeGroupSubtitle(ageGroup: AgeGroup, experiment: AgeExperiment?): String {
    return when (ageGroup) {
        AgeGroup.YOUNG -> "Молоденький котик"
        AgeGroup.ADULT -> "Взрослый котик"
        AgeGroup.OLD -> "Старый котик"
        AgeGroup.UNKNOWN -> "Котик в ШОКе"
    }
} 