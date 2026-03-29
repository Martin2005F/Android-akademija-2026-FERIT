data class Resource(
    val name: String,
    private var amount: Double,
    val criticalLevel: Double
){
    fun isCritical(): Boolean{
        return amount < criticalLevel;
    }

    fun hasEnough(needed: Double): Boolean{
        return amount >= needed
    }

    fun consume(quantity: Double){
        amount -= quantity
    }

    fun printStatus(){
        println("Resource $name: $amount left")
    }
}