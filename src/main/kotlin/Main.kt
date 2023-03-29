import java.io.File
import java.io.FileWriter

fun main()
{
    println("Если вы хотите считать текст из файла, поместите его в одну папку с Main.kt и введите название файла в формате name.txt, либо укажите путь к файлу в формате C:\\Users\\Documents\\name.txt")
    println("Если вы не хотите считывать текст из файла, можете просто ввести его в консоль.")
    val input: String = readLine() ?: "";
    println();

    if (input.split("\\").size > 1) //если есть хотя бы один слеш, то есть мнение, что это путь к файлу
    {
        GetStatistic(File(input).readText(), input);
        println("Аналогичная статистика добавлена в исходный .txt файл");
    }
    else if (input.takeLast(4) == ".txt") //если слешей нет, но предложение заканчивается на .txt, то скорее всего это название файла)
    {
        GetStatistic(File("src\\main\\kotlin\\$input").readText(), "src\\main\\kotlin\\$input");
        println("Аналогичная статистика добавлена в исходный .txt файл");
    }
    else //если просто введен текст
    {
        GetStatistic(input);
    }
}

fun GetSentences(text: String): List<String> //принимает текст, а возвращает список из предложений
{
    return text.trimEnd('.', '!', '?').split(".", "!", "?"); //сначала через trimEnd убираем последний знак, что бы избежать создания предложения "".
}

fun GetNumberOfWords(sentences: List<String>): Array<Int> //принимает список из предложений, а возвращает количество строк в каждом предложении в виде массива интов
{
    val numberOfWords = Array<Int>(sentences.size) {0};
    for (i in 0 until sentences.size)
    {
        numberOfWords[i] = sentences[i].trimStart().trimEnd().split(Regex("\\s+")).size;
        //Regex("\\s+") нужен для того что бы предложение типа "Привет,  мир!" (там два пробела) не распознавалось как предложение из трех слов
    }

    return numberOfWords;
}

fun GetStatistic(text: String, filePath: String = " ")
{
    val sentences = GetSentences(text);
    val numberOfWords = GetNumberOfWords(sentences);
    val fileWriter = FileWriter(filePath, true);

    println("Предложения и количество слов в них:");
    for (i in 0 until sentences.size)
    {
        println("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
        if (filePath != " ")
        {
            //File(filePath).writeText("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
            fileWriter.write("\n");
            fileWriter.write("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
        }
    }
    fileWriter.close();
}