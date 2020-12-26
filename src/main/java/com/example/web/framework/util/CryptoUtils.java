package com.example.web.framework.util;

import java.util.Random;

/**
 * 加密工具类.
 *
 * @author Lei
 * @since  1.0
 */
public class CryptoUtils {
    public static final char[] CODE_TABLE = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z' ,
            'A' , 'B' , 'C' , 'D' , 'E' , 'F' ,
            'G' , 'H' , 'I' , 'J' , 'K' , 'L' ,
            'M' , 'N' , 'O' , 'P' , 'Q' , 'R' ,
            'S' , 'T' , 'U' , 'V' , 'W' , 'X' ,
            'Y' , 'Z'
    };

    /**
     * 随机获取由字母数字组成的字符串.
     * @param length 字符串长度
     * @return 返回生成的字符串
     */
    public static String randomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append(CODE_TABLE[random.nextInt(CODE_TABLE.length)]);
        }
        return builder.toString();
    }
}

