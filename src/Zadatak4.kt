import java.util.Locale
import java.util.Locale.getDefault

fun main(){
    println("Input your username")
    val userName = readln()

    val correctedUserName = UsernameCorection(userName)

    if(isValid(correctedUserName)){
        println("Your username $userName is valid")
    }else{
        println("Your username $correctedUserName is not valid")
    }

}

fun UsernameCorection(userName: String): String {
   return userName.trim().lowercase()
}

fun isValid(userName: String) : Boolean{
    if(userName.isBlank()
        || !userName.all { it.isLetterOrDigit()  || it == '_'}
        || userName.contains(" ")
        || !userName[0].isLetter()
        || userName.length < 5
        || userName.length > 15){
        return false;
    }else{
        return true;
    }
}