package com.kabanov.generating_tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author Kabanov Alexey
 */
public class FilesGenerator {

    private StringsGenerator stringsGenerator;

    public FilesGenerator(StringsGenerator stringsGenerator) {
        this.stringsGenerator = stringsGenerator;
    }

    public void generate(File file, long numberOfLines, int maxLineSize) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {

            Random random = new Random();
            for (int i = 0; i < numberOfLines; i++) {
                printWriter.println(stringsGenerator.generateRandomString(random.nextInt(maxLineSize) + 1));
            }
        }
    }
}
