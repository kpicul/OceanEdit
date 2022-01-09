// This file is part of OceanEdit.
//
// OceanEdit is free software: you can redistribute it and/or modify it under the terms of the GNU General
// Public License as published by the FreeSoftware Foundation, either version 2 of the License, or (at
// your option) any later version.
//
// OceanEdit is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
// the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along with OceanEdit. If not, see
// <https://www.gnu.org/licenses/>.

package com.oceanedit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that contains file operations.
 */
public class FileOperations {
    /**
     * Reads given file.
     * @param file file that is to be read.
     * @return File content.
     * @throws IOException Exception that happens if something goes wrong when reading file.
     */
    public static String readFile(File file) throws IOException {
        FileReader reader = new FileReader(file);
        StringBuilder builder = new StringBuilder();
        int data;
        while ((data = reader.read()) != -1) {
            builder.append((char) data);
        }

        return builder.toString();
    }

    /**
     * Writes file.
     * @param file File that is to be written into.
     * @param text Text that is going to be written.
     * @throws IOException Input output exception.
     */
    public static void writeFile(File file, String text) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(text);
        writer.close();
    }
}
