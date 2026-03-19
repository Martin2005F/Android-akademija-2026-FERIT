fun main(){
    val stepsInWeek = listOf(4500, 1000, 8000, 15000, 3000, 11000, 9500)
    var sum = 0;

    for(i in stepsInWeek){
        sum += i;
    }

    println("Your weekly steps sum is:$sum")

    var count = 0;
    while(count < stepsInWeek.size){
        if(stepsInWeek[count] >= 10000){
            println("First day above 10k is ${count + 1} day")
            break
        }
        count++
    }
}