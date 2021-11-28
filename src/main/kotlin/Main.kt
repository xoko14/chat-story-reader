import controller.PlaceholderReaderAndPrinter

fun main(args: Array<String>) {
    val placeholder = PlaceholderReaderAndPrinter()
    placeholder.run(args[0])
}