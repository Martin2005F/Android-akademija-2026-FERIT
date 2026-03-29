object TransactionLogger{
    fun log(message: String){
        println("[LOG] $message")
    }
}

class BankAccount(
    val accountNumber: String
){
    var balance: Double = 0.0

    init{
        numberOfAccounts++
    }

    fun deposit(amount: Double){
        balance += amount
        TransactionLogger.log("Deposit of $amount on account $accountNumber. New balance $balance")

    }

    fun withdraw(amount: Double){
        if(amount > balance){
            TransactionLogger.log("Withdraw of $amount failed on account $accountNumber. Balance: $balance")
        }else{
            balance -= amount
            TransactionLogger.log("Withdraw of $amount on account $accountNumber. New balance: $balance")
        }
    }

    companion object{
        var numberOfAccounts = 0
    }

}

fun main(){
    val account1 = BankAccount("1")
    val account2 = BankAccount("2")
    val account3 = BankAccount("3")
    val account4 = BankAccount("4")

    account1.deposit(500.0)
    account2.deposit(500.0)
    account3.deposit(500.0)
    account4.deposit(500.0)

    account1.withdraw(200.0)
    account2.withdraw(200.0)
    account3.withdraw(200.0)
    account4.withdraw(200.0)

    println("Account ${account1.accountNumber} balance: ${account1.balance}")
    println("Account ${account2.accountNumber} balance: ${account2.balance}")
    println("Account ${account3.accountNumber} balance: ${account3.balance}")
    println("Account ${account4.accountNumber} balance: ${account4.balance}")
    println("Total accounts created: ${BankAccount.numberOfAccounts}")
}