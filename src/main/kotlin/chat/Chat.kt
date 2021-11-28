package chat

class Chat() {
    constructor(id: String) : this() {
        this.id = id
    }

    var id: String = ""
    val messages: MutableList<Message> = ArrayList()
    var senders: MutableList<Sender> = ArrayList()
    var pov: Sender = Sender("","")

    fun newInstance(): Chat{
        return Chat().also {
            it.id=this.id
            it.senders=this.senders
            it.pov=this.pov
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if(this.id == (other as Chat).id) return true
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}