package com.route.gpstrackerc40gsat.SOLID

import android.widget.Button
import java.util.Date


/***
 *  SOLID
 *
 *  'S'ingle Responsiplity Principle (Any function or class Should have Only One Responsiplity )
 *
 *  'O'pen For Extension / Closed For Modification
 *
 *  'L'iskov Substitution Principle   // Substitutability is a principle in object-oriented
 *  programming stating that, in a computer program,
 *  if S is a subtype of T, then objects of type T may be replaced with objects of type S
 *
 *  'I'nterface Segregation -> OnClick Listener
 *
 *  'D'ependency Inversion Principle
 *
 *
 *  Design Patterns (  Creational , Structural , Behavioral )
 *                  Builder and Factory
 *                  // Room.databaseBuilder()
 */
interface OnClickListener {
    fun onClick()
    fun onLongClickListener()
    fun onDoubleClickListener()
    fun onSwipeClickListener()
}

val onClickListener = object : OnClickListener {
    override fun onClick() {
        // On Click Implementation
    }

    override fun onLongClickListener() {
        TODO("Not yet implemented")
    }

    override fun onDoubleClickListener() {
        TODO("Not yet implemented")
    }

    override fun onSwipeClickListener() {
        TODO("Not yet implemented")
    }
}

interface OnSwipeClickListener {
    fun onSwipeClick()
}

interface OnClickListenerV2 {
    fun onClick()
}

interface OnLongClickListener {
    fun onLongClick()
}

interface OnDoubleClickListener {
    fun onDoubleClick()
}

// 3 Resposibility
//         Holds Data
//         Print
//         Database

// Maintainable X
// Scalable X

interface Shape {
    fun draw()
}

class Rectangle : Shape {
    override fun draw() {
        print("Shape : Draw Rectangle ")
    }
}

class Square : Shape {
    override fun draw() {
        print("Shape : Draw Square ")
    }
}

class Triangle : Shape {
    override fun draw() {
        print("Shape : Draw Triangle ")
    }
}

class Circle : Shape {
    override fun draw() {
        print("Shape : Draw Circle ")
    }
}

class ShapePrinter {

    // New Type -> Triangle
    fun printShape() {
        // Rectangle - Square
        val shape: Shape = Circle()
        shape.draw()
    }
}


data class Task(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val date: Date? = null,
    val isDone: Boolean? = false
)

class TaskDatabaseDao() {
    fun saveTaskIntoDatabase() {
        //Todo
    }

    fun getTasksFromDatabase() {
        // Todo
    }
}

class TaskLogger() {
    fun printTaskIntoLogCat() {
        //Todo
    }
}

data class PersonalComputer(
    val cpu: String? = null,
    val ram: String? = null,
    val hardDisk: String? = null,
    val vga: String? = null,
    val motherboard: String? = null
)

class PersonalComputerBuilder {
    private var cpu: String? = null
    private var ram: String? = null
    private var hardDisk: String? = null
    private var vga: String? = null
    private var motherboard: String? = null

    fun cpu(model: String): PersonalComputerBuilder {
        cpu = model
        return this
    }

    fun ram(model: String): PersonalComputerBuilder {
        ram = model
        return this
    }

    fun hardDisk(model: String): PersonalComputerBuilder {
        hardDisk = model
        return this
    }

    fun motherboard(model: String): PersonalComputerBuilder {
        motherboard = model
        return this
    }

    fun vga(model: String): PersonalComputerBuilder {
        vga = model
        return this
    }

    fun build(): PersonalComputer {
        return PersonalComputer(cpu, ram, hardDisk, vga, motherboard)
    }
}

data class Laptop(
    val cpu: String? = null,
    val ram: String? = null,
    val hardDisk: String? = null,
    val vga: String? = null,
    val motherboard: String? = null
)

interface LaptopFactory {
    fun create(): Laptop
}

class DellLaptopFactory() : LaptopFactory {
    override fun create(): Laptop {
        return Laptop(
            "Dell Intel Core I7 - 12-700H",
            ram = "ASUS 16 GB 3200MHz",
            hardDisk = "Kingston 500 SSD",
            vga = "Nvidia RTX 4060",
            motherboard = "DELL motherboard"
        )
    }
}

class AsusLaptopFactory() : LaptopFactory {
    override fun create(): Laptop {
        return Laptop(
            "Asus Intel Core I7 - 12-700H",
            ram = "ASUS 16 GB 3200MHz",
            hardDisk = "Kingston 500 SSD",
            vga = "Nvidia RTX 4060",
            motherboard = "ASUS motherboard"
        )
    }
}


fun main() {
    val builder = PersonalComputerBuilder()
        .cpu("Intel Core I7-13700H")
        .vga("Nvidia RTX 3060")
        .ram("ADATA 1X16 GB")
        .motherboard("ASUS")
        .hardDisk("500 GB SSD")
    // Cart  -> Chair + Table
    // Order

    val pc = builder.build()

    val dellLaptop = DellLaptopFactory().create()
    val asusLaptop = AsusLaptopFactory().create()

}