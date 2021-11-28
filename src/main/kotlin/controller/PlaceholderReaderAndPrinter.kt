package controller

import chat.Chat
import chat.Message
import chat.Sender
import java.io.File

class PlaceholderReaderAndPrinter {
    private val chats: MutableList<Chat> = ArrayList()
    private val senders: MutableList<Sender> = ArrayList()

    fun run(file: String){
        var currentChat = Chat()

        File(file).forEachLine {rawLine ->
            val line = rawLine.split("=")
            val code = line[0].split(" ")
            val value = if(line.size>1) line[1] else null

            if(rawLine.isNotBlank()) {
                when (code[0]) {
                    "name" -> {
                        println("Story: $value")
                    }

                    "author" -> {
                        println("Author: $value")
                    }

                    "version" -> {
                        println("Version: $value")
                    }

                    "sender"-> {
                        senders.add(Sender(code[1], value as String))
                    }

                    "define" -> {
                        when(code[1]){
                            "chat" ->{
                                currentChat = Chat()
                                currentChat.id = code[2]
                                chats.add(currentChat)
                            }
                        }
                    }

                    "join" -> {
                        val sendersId = (value as String).split(" ")
                        sendersId.forEach { id ->
                            currentChat.senders.add(getSender(id))
                        }
                    }

                    "pov" -> {
                        currentChat.pov = getSender(code[1])
                    }

                    "start" -> {
                        when(code[1]){
                            "chat" ->{
                                currentChat = getChat(code[2])
                            }
                        }
                    }

                    "send" -> {
                        currentChat.messages.add(Message(getSender(code[1]), value as String))
                    }

                    else -> println("Error")
                }
            }
        }

        chats.forEach { chat ->
            chat.messages.forEach { message ->
                println("${message.sender.name}: ${message.message}")
            }
        }
    }

    private fun getSender(id: String): Sender{
        return senders[senders.indexOf(Sender(id,""))]
    }

    private fun getChat(id: String): Chat{
        return chats[chats.indexOf(Chat(id))]
    }
}