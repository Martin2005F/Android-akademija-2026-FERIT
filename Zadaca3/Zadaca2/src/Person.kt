abstract  class Person (
    val name: String,
    val age:Int,
    val role: String

){
    abstract fun getDetails()

    fun greet() = "[$role] $name is on the board"
}