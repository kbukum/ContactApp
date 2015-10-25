package org.oopdev.contact.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kamilbukum on 24/10/15.
 */
public class ValidationUtil {
    /**
     * Check String value is null or empty
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.trim() == "";
    }

    public static boolean isEmpty(List<?> list){
        return list == null || list.size() == 0;
    }
}
