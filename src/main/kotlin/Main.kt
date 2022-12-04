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
    val numberToFind= 27
    when(numberToFind){
        in 1..20-> println("1<20")
        in 21..30-> println("1<20")
        else -> println("out of bounds");
    }

    /*ENUMERATION*/
    val response: ApiResponse = ApiResponse.OK

    when(response){
        ApiResponse.OK -> print("OK")
        ApiResponse.NOT_FOUND -> print("NOT_FOUND")
        ApiResponse.UNAUTHORIZED -> print("UNAUTHORIZED")
        ApiResponse.FORBIDDEN -> print("FORBIDDEN")
        ApiResponse.UNKNOWN -> print("UNKNOWN")
    }
}
enum class ApiResponse (val code: Int) {     /*ENUMERATION USED FOR gerezDesChoixEtDesConditions but can't be in fuction*/
    OK(200),
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    UNKNOWN(0)
}

fun main(args: Array<String>) {

    println("Program arguments: ${args.joinToString()}")

    //declarerEtInitialisezDesVariables();
    //implementezDifferentesFonctions();
    //generezVosPremiereClasses();
    //gerezDesChoixEtDesConditions();

}