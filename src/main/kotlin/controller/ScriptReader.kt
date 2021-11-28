package controller

import chat.Chat
import chat.Message
import chat.Script
import chat.Sender
import java.io.File

class ScriptReader {
    companion object {
        private val chatStructures: MutableList<Chat> = ArrayList()
        private val chats: MutableList<Chat> = ArrayList()
        private val senders: MutableList<Sender> = ArrayList()
        private var title: String = "untitled"
        private var author: String = "no author"
        private var version: String = "0"

        fun read(file: String): Script {
            var currentChat = Chat()

            File(file).forEachLine { rawLine ->
                val line = rawLine.split("=")
                val code = line[0].split(" ")
                val value = if (line.size > 1) line[1] else null

                if (rawLine.isNotBlank()) {
                    when (code[0]) {
                        "title" -> {
                            title = value as String
                        }

                        "author" -> {
                            author = value as String
                        }

                        "version" -> {
                            version = value as String
                        }

                        "sender" -> {
                            senders.add(Sender(code[1], value as String))
                        }

                        "define" -> {
                            when (code[1]) {
                                "chat" -> {
                                    currentChat = Chat()
                                    currentChat.id = code[2]
                                    chatStructures.add(currentChat)
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
                            when (code[1]) {
                                "chat" -> {
                                    currentChat = getChat(code[2]).newInstance()
                                    chats.add(currentChat)
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

            return Script(title, author, version, chats, senders)
        }

        private fun getSender(id: String): Sender {
            return senders[senders.indexOf(Sender(id, ""))]
        }

        private fun getChat(id: String): Chat {
            return chatStructures[chatStructures.indexOf(Chat(id))]
        }
    }
}