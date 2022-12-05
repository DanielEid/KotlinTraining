import extensions.firstLetter
import extensions.sayHello
import utils.FACEBOOK_TOKEN
import utils.logMessage
import java.awt.Color
import java.lang.Exception
import java.lang.StringBuilder

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

private fun generezVosPremiereClasses() {

    class User(
        val email: String,
        var password: String,
        var age: Int
    )  //It's a class in one line, val private , var public

    val user: User = User("user@mail.com", "qwerty", 26);  //Object created

    user.age = 27; //setter
    println("${user.age}") //getter

    /*Custom getters and setters and private class field*/
    class UserCustom(email: String, private var password: String, var age: Int) {
        var email: String = email
            get() {
                println("User is getting his email")
                return field
            }
            set(value) {
                println("User is setting his email")
                field = value
            }
    }

    val userCustom = UserCustom("userCutom@mail.com", "qwerty", 30);
    print(userCustom.email)
}

private fun gerezDesChoixEtDesConditions() {
    /*IF & ELSE*/
    var a = 10;
    var b = 50;

    val result = if (a > b) {           //Works as "expretion" not "instruction" like java
        a++
        a                           //it's a return
    } else {
        b++; b
    }                  //if i want one line ; is used

    println("Result is: $result")

    /*SWITCH IN KOTLIN: WHEN*/

    fun printResponse(apiResponse: Int) = when (apiResponse) {
        200, 201, 202 -> println("OK")
        300, 301, 302 -> println("REDIRECTION")
        400, 404 -> println("ERROR")
        else -> println("UNKNOWN")
    }
    printResponse(404);  //2nd way on website


    /*WHEN FOR NUMBERS RANGE*/
    val numberToFind = 27
    when (numberToFind) {
        in 1..20 -> println("1<20")
        in 21..30 -> println("1<20")
        else -> println("out of bounds");
    }

    /*ENUMERATION*/
    val response: ApiResponse = ApiResponse.OK

    when (response) {
        ApiResponse.OK -> print("OK")
        ApiResponse.NOT_FOUND -> print("NOT_FOUND")
        ApiResponse.UNAUTHORIZED -> print("UNAUTHORIZED")
        ApiResponse.FORBIDDEN -> print("FORBIDDEN")
        ApiResponse.UNKNOWN -> print("UNKNOWN")
    }
}

enum class ApiResponse(val code: Int) {     /*ENUMERATION USED FOR gerezDesChoixEtDesConditions but can't be in fuction*/
    OK(200),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    UNKNOWN(0)
}

private fun iterezGraceAuxBoucles() {

    var isARainyDay = true;
    while (isARainyDay) {
        println("#RAINING#");
        isARainyDay = false
    }

    do {
        println("#RAINING#");
    } while (isARainyDay)

    /*LOOPS FOR*/

    val names = listOf("Jake Wharton", "Joe Birch", "Robert Martin");

    for (name in names) {  //ForEach
        println("This developer rocks : $name");
    }

    for (i in names.indices) { // For int i
        println("This developer with number $i rocks : ${names[i]}")
    }

    for ((index, value) in names.withIndex()) { // For int i
        println("This developer with number $index rocks : $value")
    }

    for (i in 1..3) {  //Other way like for int i
        println("I love this number : $i")
    }

    for (i in 0..3) println(i) //Other way like for int i

    for (i in 10 downTo 1 step 2) println("Index is :$i") //10 to 1 with step 2

    for (i in 1..10 step 2) println("Index is :$i") //1 to 10 with step 2

    // listOf
    val listOfNames = listOf("Jake Wharton", "Joe Birch", "Robert Martin")
    listOfNames[0] // => Jake Wharton
    //listOfNames[0] = "Mathieu Nebra" // => ERROR ! List is immutable

// mutableListOf
    val listOfNames1 = mutableListOf("Jake Wharton", "Joe Birch", "Robert Martin")
    listOfNames1[0] // => Jake Wharton
    listOfNames1[0] = "Mathieu Nebra" // => SUCCESS !

// setOf
    val setOfNames = setOf("Jake Wharton", "Joe Birch", "Robert Martin")
    setOfNames.first() // => Jake Wharton
    //setOfNames.add("Mathieu Nebra") // => ERROR ! Set is immutable

// mutableSetOf
    val setOfNames1 = mutableSetOf("Jake Wharton", "Joe Birch", "Robert Martin")
    listOfNames1.first() // => Jake Wharton
    listOfNames1.add("Mathieu Nebra") // => SUCCESS !
}

private fun smartCast() {

    /*SMART CAST*/

    //This three function are same

    fun getDefaultSizeOldSchool(anyObject: Any): Int {
        // Vérification du type
        if (anyObject is String) {
            return anyObject.length
            // Vérification du type
        } else if (anyObject is List<*>) {
            return anyObject.size
        }
        return 0
    }

    fun getDefaultSizeImproved(anyObject: Any) =
        if (anyObject is String) anyObject.length
        else if (anyObject is List<*>) anyObject.size
        else 0

    fun getDefaultSize(anyObject: Any) = when (anyObject) { //BEST
        is String -> anyObject.length
        is List<*> -> anyObject.size
        else -> 0
    }

    println(getDefaultSizeOldSchool(listOf("AA", "BB", "CC")))
    println(getDefaultSizeImproved(listOf("AA", "BB", "CC")))
    println(getDefaultSize("Unchained"))


    /*CONVERTION*/
    /*unsecure cast*/
    val anyObject: Any = "Hello Kotlin students !"
    val message = anyObject as String
    println(message)

    /*Secure cast but old school*/
    try {
        val message = anyObject as Int
        print(message)

    } catch (e: ClassCastException) {
        println("Error !")
    }
    /*Secure cast kotlin friendly*/
    val message0: Int? = anyObject as? Int
    print(message0)
}

private fun maitrisezLesExceptions() {

    fun substractNumber(a: Int, b: Int) = if (a >= b) a - b else throw Exception("A is smaller than B")

    // println(substractNumber(10,16))  // Exception not handled
    try {
        println(substractNumber(10, 16)) // Exception handled
    } catch (e: Exception) {
        println(e.message);
    }

    val result = try {
        substractNumber(10, 20)
    } catch (e: Exception) {
        0
    }
    print(result) //Exception as a valuer


    // Nothing est le Type retourné d'une expression "throw", Opérateur Elvis (?:) : action conditionelle si null
    class User(val email: String?, val password: String?)

    fun fail(message: String): Nothing = throw IllegalStateException(message) //Nothing typeof a throw

    val user = User("toto@gmail.com", "azerty")
    val password = user.password ?: fail("Password required")
    val email = user.email ?: fail("Email required")
}

private fun ameliorezVosFonctions() {

    /*PAR DEFAULT*/
    fun manyArgs(caller: String, number1: Int, number2: Int) =
        if (number1 > number2) number1 else if (number2 > number1) number2 else "two numbers equals"

    fun manyArgs(number2: Int) = manyArgs("You", 10, number2) // Surcharge, Overloading a method ... in java style
    // fun manyArgs(caller: String = "Overload in Kotlin", number1: Int = 0, number2: Int = 5)   //Overload style in Kotlin, ... but here it's doesn't works and I don't know why????
    manyArgs(
        caller = "Me",
        number1 = 10,
        number2 = 5
    ); //Call with parameters name for more visibily, not really usefull with modern IDE

    // @JvmOverloads // @JvmOverloads  for compatibily with java
    fun oldSchool(
        a: Int = 1,
        b: Int = 2
    ): Int {  // It's seem it's like this, no overide but defaults parameters in the function
        return a + b
    }
    println(oldSchool());
    println(oldSchool(10, 10));


    /*FONCTIONS TIMIDES*/
    /**
     * Les fonctions timides sont des fonctions se trouvant a l'interieur d'autres fonctions pour diverses raisons
     * Ce que je fait depuis le debut du cours...
     * */

    /*LE TOP DU TOP*/

    /*top level function = like static but better*/
    logMessage("This is a top level function") // Like static functions, can be call in WHOLE kotlin project and super easy
    logMessage(FACEBOOK_TOKEN); //For const in whole project it's like top-level function
}

private fun decouvrezLesExtentions() {
    val string: String = "Bonjour"
    val stringBuilder: StringBuilder = StringBuilder("Bonsoir")
    string.sayHello()  //Extends the String class without heritage ! Like "top-level function"
    println(stringBuilder.firstLetter)
}

private fun enrichissezVosClasses() {

    //open class Button(var color: String){         //open indicate the class can be inherit , do the same for functions
    open class Button(var color: String = "FFF") {         //open indicate the class can be inherit , do the same for functions, with default parameters
        init {
            println("Button created")
        }

        /* 2nd constructor, if a button is created without the color, this default parameter is applied
        * But it's better to user default parameters*/
        constructor() : this("FFF");
        open fun show() = "Show"
        open fun hide() = "hide"

    }

    class CircularButton(color: String) : Button(color) {  //Do not indicate that a var, because the parent class did it
        override fun show() = super.show() + "circular"
        override fun hide() = super.hide() + "circular"
    }

    /*Data class and companions object under this function*/

    val user = User.newInstanceAfterSignUp("toto@gmail.com", "azerty")
    val secondUser = User.Generator.newInstanceAfterSignUp("tata@gmail.com", "youhou")

    /*Singleton instance*/
    println(MySingleton.getSingleton())

}

/*
    *Data class: kotlin know this object is use to store data, so it implement the functions used for storage (equals, hashcode,...)
*/
data class User(var email: String, var password: String, var isConnected: Boolean) {
    /*
    * Allow us to access to methods inside a class ,even if the object is not created*/
    companion object Generator { // Can have a name
        fun newInstanceAfterSignUp(email: String, password: String) = User(email, password, isConnected = true)
    }
}

object MySingleton {
    fun getSingleton(): String = "Singleton"
    fun saveSingleton(string: String) = "Save singleton"
}

fun main(args: Array<String>) {

    println("Program arguments: ${args.joinToString()}")

    //declarerEtInitialisezDesVariables();
    //implementezDifferentesFonctions();
    //generezVosPremiereClasses();
    //gerezDesChoixEtDesConditions();
    //iterezGraceAuxBoucles();
    //smartCast();
    //maitrisezLesExceptions()
    //ameliorezVosFonctions();
    //decouvrezLesExtentions()
    //enrichissezVosClasses();
}