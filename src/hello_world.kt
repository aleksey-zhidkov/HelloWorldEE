package helloworld

class Name(val firstName: String,
           val middleName: String?,
           val lastName: String?)

class NameFactory {

    public fun createName(): Name? = readLine()?.let {
        val nameParts = it.split(" ")
        val firstName = nameParts[0]

        val (middleName, lastName) =
                when (nameParts.size()) {
                    1 -> Pair(null, null)
                    2 -> Pair(null, nameParts[1])
                    else -> Pair(nameParts[1], nameParts[2])
                }

        Name(firstName, middleName, lastName)
    }

}

fun main(args: Array<String>) {
    val nameFactory = NameFactory()
    val name = nameFactory.createName()
    println("Hello $name!")
}