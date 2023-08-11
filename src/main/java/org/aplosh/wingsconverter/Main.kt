package org.aplosh.wingsconverter

import java.awt.Color
import java.io.File
import java.util.concurrent.atomic.AtomicInteger
import javax.imageio.ImageIO

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        // Read an image from a file
        val img = ImageIO.read(File(""))

        // AtomicInteger used to generate unique color IDs
        val colorIndex = AtomicInteger()

        // MutableMap to store color-to-ID mappings
        val indexByColor: MutableMap<Color, String> = HashMap()

        // Initialize the map with a default color ID
        indexByColor[Color(0, 0, 0)] = "-1"

        // Print the start of a JSON-like array
        println("{")

        // Iterate through each pixel in the image
        for (y in 0 until img.height) {
            // Start printing an inner array
            print("    {")

            for (x in 0 until img.width) {
                // Get the color of the current pixel
                val color = Color(img.getRGB(x, y))

                // Compute or retrieve the color's ID, generating a new one if necessary
                val id = indexByColor.computeIfAbsent(color) { _: Color? -> colorIndex.addAndGet(11).toString() }

                // Print the color's ID
                print("\"" + id + "\"")

                // Add a comma if not the last pixel in the row
                if (x != img.width - 1) {
                    print(", ")
                }
            }

            // Close the inner array for the current row
            print("}")

            // Add a comma if not the last row
            if (y != img.height - 1) {
                println(",")
            } else {
                println()
            }
        }

        // Print the end of the JSON-like array
        println("}")
    }
}