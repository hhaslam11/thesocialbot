package app.kaleb.theSocialBot.services

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@Service
class MessagingService {

    fun getMessages(): List<String> {
        val questions: MutableList<String> = mutableListOf()

        val resource: Resource = ClassPathResource("messages.txt")
        val file: File = resource.file
        val reader = BufferedReader(FileReader(file))

        var line: String?
        while (reader.readLine().also { line = it } != null) {
            questions.add(line!!)
        }

        reader.close()
        return questions
    }

}