import controller.ScriptReader

fun main(args: Array<String>) {
    val script = ScriptReader.read(args[0])
    script.chats.forEach { chat ->
        println("\nPOV: ${chat.pov.name}")
        chat.messages.forEach { message ->
            println("${message.sender.name}: ${message.message}")
        }
    }
}