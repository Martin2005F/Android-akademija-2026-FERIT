import javax.tools.Tool

class Astronaut(
    name:String,
    age: Int,
    private var missionPower: Double?
) : Person(name, age, "Astronaut"), Maintainable{

    lateinit var tool: String

    fun hasEnoughPower(required: Double): Boolean{
        val current = missionPower ?: 0.0
        return current >= required
    }

    fun consumePower(amount: Double){
        val current = missionPower ?: 0.0
        missionPower = current - amount
    }

    fun isToolReady() : Boolean{
        return this::tool.isInitialized
    }

    fun performTask(taskName: String) : Boolean{
        val energyRequired = 25.0

        if(!isToolReady()){
            println("[FAIL]: $name does not have a tool assigned!")
            return false
        }

        if(!hasEnoughPower(energyRequired)){
            println("[FAIL]: $name does not have enough power [$missionPower]")
            return  false
        }

        consumePower(energyRequired)
        println("[SUCCESS]: $name is using $tool for $taskName")
        return true;
    }

    override fun getDetails() {
        println("[$role] | Name:$name | Age:$age | Power:${missionPower ?: "N/A"}")
    }

    override fun repair() {
        println("$name is repairing..")
        missionPower = 100.0
    }
}