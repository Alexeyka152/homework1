import java.io.File
import java.io.FileWriter

fun main() {
    // Да, я знаю, что я ужасный программист. Пожалуйста, не бейте сильно.
    println("Если вы хотите считать текст из файла, поместите его в одну папку с Main.kt и введите название файла в формате name.txt")
    println("Если вы не хотите считывать текст из файла, можете просто ввести его в консоль.")
    val input: String = readLine() ?: ""
    println()

    val fileWriter = try {
        FileWriter("src\\main\\kotlin\\$input", true)
    } catch (e: Exception) {
        null
    }
    val finalText = try{
        File("src\\main\\kotlin\\$input").readText()
    } catch (e: Exception) {
        input
    }
    val sentences = finalText.trimEnd('.', '!', '?').split(".", "!", "?")
    val numberOfWords = sentences.map { it.trimStart().trimEnd().split(Regex("\\s+")).size }
    println("Предложения и количество слов в них:")
    for (i in 0 until sentences.size) {
        println("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}")
        fileWriter?.write("\n")
        fileWriter?.write("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}")
    }
    if (fileWriter != null){
        println("Аналогичная статистика записана в исходный .txt файл")
    }
    fileWriter?.close()
}
