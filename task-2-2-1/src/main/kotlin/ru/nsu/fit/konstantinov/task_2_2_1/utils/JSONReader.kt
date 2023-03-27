package ru.nsu.fit.konstantinov.task_2_2_1.utils

import com.fasterxml.jackson.databind.ObjectMapper
import ru.nsu.fit.konstantinov.task_2_2_1.models.BakerConfig
import ru.nsu.fit.konstantinov.task_2_2_1.models.DeliveryWorkerConfig
import java.io.File
import java.util.*

internal class JSONReader {
    fun readBakers(fileBakers: File?): List<BakerConfig> =
        ObjectMapper().readValue(fileBakers, Array<BakerConfig>::class.java).asList()

    fun readDeliveryWorkers(fileDeliveryWorkers: File?): List<DeliveryWorkerConfig> =
        ObjectMapper().readValue(fileDeliveryWorkers, Array<DeliveryWorkerConfig>::class.java).asList()
}
