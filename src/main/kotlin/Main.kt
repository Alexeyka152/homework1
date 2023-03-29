fun main()
{
    val text: String = readLine() ?: "";
    println();
    val sentences = GetSentences(text);
    val numberOfWords = GetNumberOfWords(sentences);

    println("Предложения в порядке убывания количества слов в них:");
    for (i in 0 until sentences.size)
    {
        println("Количество слов: ${numberOfWords[i]}. ${sentences[i].trimStart().trimEnd()}");
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
        numberOfWords[i] = sentences[i].trimStart().trimEnd().split(" ").size;
    }

    return numberOfWords;
}