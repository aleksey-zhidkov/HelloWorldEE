package helloworld

import kotlin.properties.Delegates

data class Name(val firstName: String,
                val middleName: String?,
                val lastName: String?)

trait NameFactory {

    fun createName(): Name?

    fun defaultName(): Name

}

class ConsoleNameFactory : NameFactory {

    public override fun createName(): Name? = readLine()?.let {
        println("[LOG] Creating name...")
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

    override fun defaultName() = Name("", null, null)

}

class CachingNameFactory(delegate: NameFactory = ConsoleNameFactory()) : NameFactory by delegate {

    private val name by Delegates.lazy {
        delegate.createName()
    }

    override fun createName(): Name? = name

}

fun main(args: Array<String>) {
    app(CachingNameFactory())
}

private fun app(nameFactory: NameFactory) {
    println("Hello! I'm Bond. James Bond.")
    val name = nameFactory.createName() ?: nameFactory.defaultName()

    val line1 = "I'm ${name.firstName}..."
    val line2 = name.lastName?.let { "${name.lastName} ${name.firstName}..." } ?: ""
    val line3 = name.middleName?.let { "${name.lastName} ${name.firstName} ${name.middleName}" } ?: ""
    println(+"""
    Hello!
     $line1
     $line2
     $line3
    """)

    nameFactory.createName()
}

fun String.plus() = this.trim().split("\n").
        map { it.trim() }.
        join("\n")