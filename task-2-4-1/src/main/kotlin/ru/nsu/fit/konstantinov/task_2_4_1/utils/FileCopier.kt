package ru.nsu.fit.konstantinov.task_2_4_1.utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

class FileCopier {
    companion object {
        fun copyAll(directory: File, dir: Path, origin: Path) {
            if (directory.length() != 0L) {
                Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .map { it.toFile() }
                    .forEach { it.delete() }
                Files.createDirectories(dir)
            }
            try {
                Files.walk(origin).forEach {
                    Files.copy(it, dir.resolve(origin.relativize(it)), StandardCopyOption.REPLACE_EXISTING)
                }
            } catch (e: NoSuchFileException) {
                println("No such file")
            }
        }
    }
}
