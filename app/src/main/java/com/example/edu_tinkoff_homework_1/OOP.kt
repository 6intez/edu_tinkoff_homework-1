package com.example.edu_tinkoff_homework_1
//-------Интерфейсы-----

interface Animal {
    val weight: Int
    val age: Int
}
interface Cat:Animal {
    val behavior: BehaviorType
}

interface Dog: Animal {
    val biteType: BiteType
}
// Перечисления

enum class BehaviorType {
    ACTIVE, PASSIVE
}

enum class BiteType {
    STRAIGHT, OVERSHOT, UNDERSHOT
}

// ---------Реализация интрефейсов ----------

class Husky(override val weight: Int, override val age: Int):Dog {
    override val biteType = BiteType.STRAIGHT
}

class Corgi (override val weight: Int, override val age: Int):Dog {
    override val biteType = BiteType.UNDERSHOT
}

class ScottishFold (override val weight: Int, override val age: Int):Cat {
    override val behavior = BehaviorType.PASSIVE
}

class Siamese (override val weight: Int, override val age: Int):Cat {
    override val behavior = BehaviorType.ACTIVE
}

//-----------Класс Зоомагазина----------

class PetShop {
    fun identifySpecies(animal: Animal): String{
        return when(animal){
            is Husky -> "Its a dog species: Husky"
            is Corgi -> "Its a dog species: Corgi"
            is ScottishFold -> "Its a cat species: ScottishFold"
            is Siamese -> "Its a cat species: Siamese"
            else -> "Unknown species"
        }
    }
}

//-------Main--------

fun main(){
    val petShop = PetShop()
    val husky = Husky(weight = 20, age = 7)
    val corgi = Corgi(weight = 6, age = 5)
    val siamese = Siamese(weight = 3, age = 2)
    val scottishFold = ScottishFold(weight = 5, age = 6)
    println(petShop.identifySpecies(husky))
    println(petShop.identifySpecies(siamese))
    println(petShop.identifySpecies(scottishFold))
    println(petShop.identifySpecies(corgi))
}