package ru.nsu.fit.konstantinov.task_2_2_1.utils

import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import ru.nsu.fit.konstantinov.task_2_2_1.models.ConfigModel
import java.io.File
import java.io.InputStreamReader


class ConfigParser {
    companion object {
        fun getConfigModelFromFile(filepath: String): ConfigModel {
            val reader = JsonReader(InputStreamReader(File(filepath).inputStream()))
            return GsonBuilder().create().fromJson(reader, ConfigModel::class.java)
        }
    }
}
