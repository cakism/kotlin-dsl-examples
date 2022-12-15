package dsl1


/*
    MODELS
 */

data class Fatburen(val ships: List<Ship>)

data class Ship(val people: List<Developer>, val items: List<Item>)

data class Developer(val name: String, val likesCoffee: Boolean)

interface Item

data class Blinds(val percentageOfLightBlocked: Int) : Item

interface Equipment : Item

data class Monitor(val screenSize: Int) : Equipment


// Annotate fun's and prop's you want accessible with @FatburenDsl !
@DslMarker
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class FatburenDsl

/*
    BUILDERS
 */

// Represents top level DSL
fun fatburen(init: FatburenBuilder.() -> Unit): Fatburen {
    val builder = FatburenBuilder()
    builder.init()
    return builder.build()
}

@FatburenDsl
class FatburenBuilder {
    private val ships = mutableListOf<Ship>()

    fun ship(init: ShipBuilder.() -> Unit) {
        val ship = ShipBuilder()
        ship.init()
        ships.add(ship.build())
    }

    fun build(): Fatburen = Fatburen(ships)
}

@FatburenDsl
class ShipBuilder {
    // Mark it internal instead of private to allow setting the value directly
    private val developers = mutableListOf<Developer>()
    private val items = mutableListOf<Item>()

    // Add default impl of init to allow for only passing a value without function body
    fun developer(name: String = "", likesCoffee: Boolean = false, init: DeveloperBuilder.() -> Unit = {}) {
        val builder = DeveloperBuilder(name, likesCoffee)
        builder.init()
        developers.add(builder.build())
    }

    // Add default impl of init to allow for only passing a value without function body
    // @FatburenDsl
    fun blinds(percentageOfLightBlocked: Int = 0, init: BlindsBuilder.() -> Unit = {}) {
        val builder = BlindsBuilder(100)
        builder.init()
        items.add(builder.build())
    }

    fun monitor(init: MonitorBuilder.() -> Unit) {
        val builder = MonitorBuilder(34)
        builder.init()
        items.add(builder.build())
    }

    fun build(): Ship = Ship(developers.toList(), items.toList())
}

class BlindsBuilder(initialPercentageOfLightBlocked: Int) {
    var percentageOfLightBlocked: Int = initialPercentageOfLightBlocked
    fun build() = Blinds(percentageOfLightBlocked)
}

class MonitorBuilder(private val screenSizeInit: Int) {
    var screenSize: Int = screenSizeInit
    fun build() = Monitor(screenSize)
}

class DeveloperBuilder(initialName: String, initialLikesCoffee: Boolean) {
    var name: String = initialName
    private val likesCoffee: Boolean = initialLikesCoffee

    fun build(): Developer = Developer(name, likesCoffee)
}

fun main() {
    val aLovelyPlaceToWork = fatburen {
        ship {
            blinds(100)
            monitor {
                screenSize = 34
                blinds {  }
            }
            developer( likesCoffee = true) {
                name = "JNEZ"
                // blinds {  } // Should not work, even if we want it to!
            }
        }
    }
}
