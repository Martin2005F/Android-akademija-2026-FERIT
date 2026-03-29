fun main() {
    val crew = listOf(
        Astronaut("John", 30, 80.0),
        Astronaut("Ana", 25, null),
        Astronaut("Timmy", 18, 100.5),
        Astronaut("Emily", 35, 15.0)
    )

    val supplies = listOf(
        Resource("Oxygen", 70.0, 10.0),
        Resource("Water", 5.0, 15.0),
        Resource("Bread", 100.0, 20.0)
    )

    crew[0].tool = "Hammer"
    crew[2].tool = "Laser"

    println("--- MISSION START ---")

    val critical = supplies.filter { it.isCritical() }
    critical.forEach { println("CRITICAL RESOURCE: ${it.name}") }

    for (member in crew) {
        val report = executeMission(member) {
            if (it.performTask("Deep Space Scan")) {
                MissionReport.Success(5)
            } else {
                MissionReport.Fail("Insufficient requirements")
            }
        }

        when (report) {
            is MissionReport.Success -> report.printSummary()
            is MissionReport.Fail -> report.printWarning()
        }
    }

    println("\n--- FINAL STATUS ---")
    crew.forEach { it.getDetails() }
}

fun executeMission(
    astronaut: Astronaut,
    missionLogic: (Astronaut) -> MissionReport
): MissionReport {
    return missionLogic(astronaut)
}