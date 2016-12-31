package kh.com.kshrd.core.utils;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class DirectoryFile implements FileFilter {

    @Override
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
