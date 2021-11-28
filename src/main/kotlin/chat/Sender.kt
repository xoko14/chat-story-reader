package chat

class Sender (
    val id: String,
    val name: String
        )
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if(this.id == (other as Sender).id) return true
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}