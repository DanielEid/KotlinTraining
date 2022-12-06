package extensions

import java.lang.StringBuilder

//Not use but for me
fun String.sayHello() = println("This variable contains! $this")  //Extention function

var StringBuilder.firstLetter: Char                               //Extention Property
    get() = get(0)
    set(value) = this.setCharAt(0, value)