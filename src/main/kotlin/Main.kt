import controller.ScriptReader

fun main(args: Array<String>) {
    val script = ScriptReader.read(args[0])

    println("${script.title} by ${script.author}")
    println("Version ${script.version}")

    script.chats.forEach { chat ->
        println("\nPOV: ${chat.pov.name}")
        chat.messages.forEach { message ->
            println("${
                if(message.sender==chat.pov){
                    "Me"
                } else{
                    message.sender.name
                }
            }: ${message.message}")
        }
    }
}