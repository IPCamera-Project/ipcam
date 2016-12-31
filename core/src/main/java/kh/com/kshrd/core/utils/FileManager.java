package kh.com.kshrd.core.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class FileManager {

    public static List<String> readLines(File file, boolean ignoreComments) throws IOException {
        if (!file.exists() || !file.isFile()) {
            return new ArrayList<>();
        }

        List<String> lines = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (ignoreComments && !line.startsWith("#") && !lines.contains(line)) {
                    lines.add(line);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return lines;
    }

    public static void writeLines(Collection<String> lines, File file) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static boolean delete(File fileOrFolder) {
        boolean success = false;
        if (fileOrFolder.isDirectory()) {
            File [] files = fileOrFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        success |= delete(file);
                    } else {
                        success |= file.delete();
                    }
                }
            }
        }
        success |= fileOrFolder.delete();

        return success;
    }
    public static void main(String[] arg){
        try {
           for(String i : FileManager.readLines(new File("/Users/sophatvathana/Desktop/ipcam/hikvision/plugin.properties"),true)){
               System.out.println(i);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
