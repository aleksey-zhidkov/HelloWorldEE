package helloworld

class NameFactory {

    public fun createName(): String? = readLine()

}

fun main(args: Array<String>) {
    val nameFactory = NameFactory()
    val name = nameFactory.createName()
    println("Hello $name!")
}