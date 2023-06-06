package ru.nsu.fit.konstantinov.task_2_4_1.application.repository

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.api.errors.JGitInternalException
import org.eclipse.jgit.api.errors.RefNotFoundException
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter
import org.eclipse.jgit.transport.URIish
import ru.nsu.fit.konstantinov.task_2_4_1.application.config.Config
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class Repository {
    fun pullRepo(name: String, branch: String) {
        val git = Git.open(File("./repos/$name"))
        try {
            git.checkout().setName(branch).call()
        } catch (e: RefNotFoundException) {
            git.checkout().setCreateBranch(true).setName(branch).call()
            git.checkout().setName(branch).call()
        }
    }

    fun cloneRepo(name: String) {
        Git.init().setDirectory(File("./repos")).call()
        if (!Files.isDirectory(Paths.get("./repos/$name"))) {
            Files.createDirectories(Paths.get("./repos/$name"))
        }
        val student = Config().readConfig("$name.kts")
        try {
            Git.cloneRepository()
                .setURI(student.repoUrl)
                .setDirectory(File("./repos/$name"))
                .call()
        } catch (e: JGitInternalException) {
            println("You have already cloned this repo")
            return
        }
        val git = Git.open(File("./repos/$name"))
        git.remoteAdd().setName(name).setUri(URIish(student.repoUrl)).call()
    }

    fun logStudentsAttendance(monday: Date, sunday: Date, name: String): Boolean {
        val git = Git.open(File("./repos/$name"))
        val between = CommitTimeRevFilter.between(monday, sunday)
        val revCommit = git.log().setRevFilter(between).call()
        return revCommit.count() > 0
    }
}
