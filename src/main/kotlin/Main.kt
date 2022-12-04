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

fun main(args: Array<String>) {

    println("Program arguments: ${args.joinToString()}")

    //declarerEtInitialisezDesVariables();
    //implementezDifferentesFonctions();
    //generezVosPremiereClasses();
    //gerezDesChoixEtDesConditions();
    //iterezGraceAuxBoucles();
    //smartCast();
}