package kh.com.kshrd.core.utils;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return (str == null) || str.isEmpty();
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
