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
        BufferedImage img = ImageIO.read(new File("file path here"));
        AtomicInteger colorIndex = new AtomicInteger();
        Map<Color, String> indexByColor = new HashMap<>();

        indexByColor.put(new Color(0, 0, 0), "-1");

        System.out.println("{");
        for (int y = 0; y < img.getHeight(); ++y) {
            System.out.print("    {");
            for (int x = 0; x < img.getWidth(); ++x) {
                Color color = new Color(img.getRGB(x, y));
                String id = indexByColor.computeIfAbsent(color, c -> String.valueOf(colorIndex.addAndGet(11)));
                System.out.print("\"" + id + "\"");
                if (x != img.getWidth() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print("}");
            if (y != img.getHeight() - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }
        System.out.println("}");
    }
}