sealed class MissionReport {
    data class Success(val discoveredItems:Int) : MissionReport(){
        fun printSummary(){
            println("Mission completed! Found: $discoveredItems items!")
        }
    }
    class Fail(val reason: String) : MissionReport(){
        fun printWarning(){
            println("Mission failed! Reason: $reason")
        }
    }
}