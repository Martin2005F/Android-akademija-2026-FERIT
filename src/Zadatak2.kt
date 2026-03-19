fun main(){
    val articleCode = 2;
    val price = 2
    val receivedMoney = 3

   val drink = when(articleCode){
        1 -> "Voda"
        2 -> "Cola"
        3 -> "Sok"
        4 -> "Kava"
       else -> "Wrong input"
    }

    val change = receivedMoney - price
    val amountShort = price - receivedMoney

    val canAfford = receivedMoney >= price

    if(canAfford){
        println("We are pouring $drink and your change is $change")
    }else{
        println("You are short $amountShort")
    }
}