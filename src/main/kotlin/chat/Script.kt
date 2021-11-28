package chat

class Script(
    val title: String,
    val author: String,
    val version: String,
    val chats:MutableList<Chat>,
    val senders: MutableList<Sender>
)