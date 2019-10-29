package com.kabanov.generating_tool;

import java.io.File;
import java.io.IOException;

/**
 * @author Kabanov Alexey
 */
public class FilesGeneratorLauncher {

    public static void main(String[] args) throws IOException {

        File dest = new File(args[0]);
        int numberOfLines = Integer.parseInt(args[1]);
        int maxLineSize = Integer.parseInt(args[2]);

        if (!dest.exists()) {
            dest.createNewFile();
        }

        FilesGenerator filesGenerator = new FilesGenerator(new StringsGenerator());
        filesGenerator.generate(dest, numberOfLines, maxLineSize);
    }
}
