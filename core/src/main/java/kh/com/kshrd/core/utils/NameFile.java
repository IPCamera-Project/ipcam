package kh.com.kshrd.core.utils;

import java.io.File;
import java.io.FileFilter;
/**
 * Created by sophatvathana on 20/12/16.
 */
public class NameFile implements FileFilter{

    private String name;

    public NameFile(String name) {
        this.name = name;
    }

    @Override
    public boolean accept(File file) {
        return file.getName().equalsIgnoreCase(name);
    }


}
