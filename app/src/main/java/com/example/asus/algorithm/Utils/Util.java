package com.example.asus.algorithm.Utils;

import java.util.List;

/**
 * Created by Asus on 2018/1/8.
 */

public class Util {
    public static String convertListToStr(List<String> list){
        String str = "";
        for (String s:list) {
            str += s;
            str += "  ";

        }

        return str;
    }
}
