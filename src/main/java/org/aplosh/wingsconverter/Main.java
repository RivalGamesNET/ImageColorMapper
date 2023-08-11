package org.aplosh.wingsconverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws Exception {
        // Read an image from a file
        BufferedImage img = ImageIO.read(new File(""));

        // AtomicInteger used to generate unique color IDs
        AtomicInteger colorIndex = new AtomicInteger();

        // Map to store color-to-ID mappings
        Map<Color, String> indexByColor = new HashMap<>();

        // Initialize the map with a default color ID
        indexByColor.put(new Color(0, 0, 0), "-1");

        // Print the start of a JSON-like array
        System.out.println("{");

        // Iterate through each pixel in the image
        for (int y = 0; y < img.getHeight(); ++y) {
            // Start printing an inner array
            System.out.print("    {");

            for (int x = 0; x < img.getWidth(); ++x) {
                // Get the color of the current pixel
                Color color = new Color(img.getRGB(x, y));

                // Compute or retrieve the color's ID, generating a new one if necessary
                String id = indexByColor.computeIfAbsent(color, c -> String.valueOf(colorIndex.addAndGet(11)));

                // Print the color's ID
                System.out.print("\"" + id + "\"");

                // Add a comma if not the last pixel in the row
                if (x != img.getWidth() - 1) {
                    System.out.print(", ");
                }
            }

            // Close the inner array for the current row
            System.out.print("}");

            // Add a comma if not the last row
            if (y != img.getHeight() - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }

        // Print the end of the JSON-like array
        System.out.println("}");
    }
}