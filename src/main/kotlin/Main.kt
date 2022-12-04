import java.awt.Button

/**
 * https://openclassrooms.com/fr/courses/5353106-initiez-vous-a-kotlin/5354811-declarez-et-initialisez-des-variables
 */
private fun declarerEtInitialisezDesVariables() {
    val name: String = "Phil"   // Value not modifiable
    var age: Int = 27           // Variable modifiable
    var isDeveloper: Boolean = true     // Variable with type
    var actualTicket: String? = "Training ticket"   // Variable can be null


    // actualTicket.uppercase();    //Var can't be null so This must be clearly indicated when changing its content.
    actualTicket?.uppercase();      // Best way to modify it
    if (actualTicket != null) actualTicket.uppercase(); // Deprecated way to update it

    println("Our Develloper $name, is $age ,works as developer : $isDeveloper and actually works on $actualTicket") //How to print

    lateinit var myFutureVar: Any;               // The var will be initialithe for sure and not be null later
    fun myFutureInit() {
        myFutureVar = "The futur is now"
    }
// const val SERVER_URL: String = "https://my.api.com";      // Like public static final, and ";" is not a mandatory in Kotlin
}

private fun implementezDifferentesFonctions() {

    /*COURSES FUNCTION*/
    fun minOf(
        a: Int,
        b: Int
    ): Any {                                         //If the function have a body, you must indicate it return type
        return if (a < b) a else if (a == b) "a:$a = b:$b" else b
        //return if( a < b ) a ; else if (a==b) "a:$a = b:$b" ; else b      //Works too, more lisible
    }

    fun minOf2(a: Int, b: Int): Int = if (a < b) a else b;   //Functions without body
    fun minOf3(a: Int, b: Int): Int = if (a < b) a else b;
    fun funLooksVal() = "hello"; // Same as a val/var but without () and in this case the return type is not defined
    fun funLooksVal2(): Unit = println("Unit is like void")

    /*COURSE EXECUTION*/
    println(minOf(1, 1))
    println(funLooksVal());

}


fun main(args: Array<String>) {

    println("Program arguments: ${args.joinToString()}")

    declarerEtInitialisezDesVariables();
    implementezDifferentesFonctions();

}