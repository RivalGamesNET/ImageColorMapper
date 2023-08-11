package org.aplosh.wingsconverter

import java.awt.Color
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import javax.imageio.ImageIO

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val img = ImageIO.read(File("E:\\Code\\BlockGame\\WingsConverter\\wings.png"))
        val colorIndex = AtomicInteger()
        val indexByColor: MutableMap<Color, String> = HashMap()
        indexByColor[Color(0, 0, 0)] = "-1"
        println("{")
        for (y in 0 until img.height) {
            print("    {")
            for (x in 0 until img.width) {
                val color = Color(img.getRGB(x, y))
                val id = indexByColor.computeIfAbsent(color) { c: Color? -> colorIndex.addAndGet(11).toString() }
                print("\"" + id + "\"")
                if (x != img.width - 1) {
                    print(", ")
                }
            }

            print("}")
            if (y != img.height - 1) {
                println(",")
            } else {
                println()
            }
        }
        println("}")
    }
}