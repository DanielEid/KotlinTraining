import java.awt.Button

/**
 * https://openclassrooms.com/fr/courses/5353106-initiez-vous-a-kotlin/5354811-declarez-et-initialisez-des-variables
 */
fun declarerEtInitialisezDesVariables() {
    val name: String = "Phil"   // Value not modifiable
    var age: Int = 27           // Variable modifiable
    var isDeveloper: Boolean = true     // Variable with type
    var actualTicket: String? = "Training ticket"   // Variable can be null


    // actualTicket.uppercase();    //Var can't be null so This must be clearly indicated when changing its content.
    actualTicket?.uppercase();      // Best way to modify it
    if (actualTicket != null) actualTicket.uppercase(); // Deprecated way to update it

    print("Our Develloper $name, is $age ,works as developer : $isDeveloper and actually works on $actualTicket") //How to print

    lateinit var myFutureVar: Any;               // The var will be initialithe for sure and not be null later
    fun myFutureInit() {
        myFutureVar = "The futur is now"
    }
// const val SERVER_URL: String = "https://my.api.com";      // Like public static final, and ";" is not a mandatory in Kotlin
}

fun main(args: Array<String>) {

    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")

    declarerEtInitialisezDesVariables();

}