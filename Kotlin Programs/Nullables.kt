fun nullableString() {
    var name: String? = null
    var name1: String? = "Sahil"
    println("Name String length " + name?.length)
    println("Name1 String length " +  name1?.length)
    // println(name!!::class.java.typeName)
    // Above statement gives output as 
    /* Exception in thread "main" java.lang.NullPointerException
        at MainKt.nullableString(main.kt:12)
    	at MainKt.main(main.kt:20)
    	at MainKt.main(main.kt) */
    println(name1!!::class.java.typeName)
    var length = name?.length ?: 0
    println("Default length "+length)
}

fun main() {
    val default = "Hello"
    println("${default} and welcome to Android Compose Camp")
    nullableString()
}