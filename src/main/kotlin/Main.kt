import java.io.File
import java.io.FileWriter

fun main()
{
    //Даа я знаю что я ужасный программист, пожалуйста, не бейте сильно
    println("Если вы хотите считать текст из файла, поместите его в одну папку с Main.kt и введите название файла в формате name.txt, либо укажите путь к файлу в формате C:\\Users\\Documents\\name.txt")
    println("Если вы не хотите считывать текст из файла, можете просто ввести его в консоль.")
    val input: String = readLine() ?: "";
    println();
    if (input.split("\\").size > 1) //если есть хотя бы один слеш, то есть мнение, что это путь к файлу
    {
        getStatistic(File(input).readText(), input);
        println("Аналогичная статистика добавлена в исходный .txt файл");
    }
    else if (input.takeLast(4) == ".txt") //если слешей нет, но предложение заканчивается на .txt, то скорее всего это название файла)
    {
        getStatistic(File("src\\main\\kotlin\\$input").readText(), "src\\main\\kotlin\\$input");
        println("Аналогичная статистика добавлена в исходный .txt файл");
    }
    else //если просто введен текст
    {
        getStatistic(input);
    }
}

fun getStatistic(text: String, filePath: String = " ") {
    val sentences = text.trimEnd('.', '!', '?').split(".", "!", "?");
    val numberOfWords = sentences.map { it.trimStart().trimEnd().split(Regex("\\s+")).size }

    println("Предложения и количество слов в них:");

    val fileWriter = try
    {
        FileWriter(File(filePath), true)
    }
    catch (e: Exception)
    {
        null
    }

    for (i in 0 until sentences.size)
    {
        println("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
        if (filePath != " ")
        {
            //File(filePath).writeText("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
            fileWriter?.write("\n");
            fileWriter?.write("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
        }
    }
    fileWriter?.close();
}