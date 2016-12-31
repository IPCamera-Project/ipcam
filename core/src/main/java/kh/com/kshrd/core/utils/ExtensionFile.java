package kh.com.kshrd.core.utils;

/**
 * Created by sophatvathana on 20/12/16.
 */
import java.io.File;
import java.io.FileFilter;

public class ExtensionFile implements FileFilter {

    private String extension;

    public ExtensionFile(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File file) {

        return file.getName().toUpperCase().endsWith(extension.toUpperCase());
    }

}