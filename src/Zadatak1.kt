fun main() {
    val name: String = "John"
    val surname: String = "Smith"
    var email: String? = null
    var age: Int? = null

    age = 20

    println(email?.length)

    email = "JohnSmith@gmail.com"

    println(email?.length)
}