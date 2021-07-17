package leetcode.test;

import java.util.Arrays;

/**
 * @Author Hcs
 * @Date 2021-7-17 14:33
 * @Version 1.0
 */
public class test_toStirng_newString {
    public static void main(String[] args) {
        char[] cs={'a', 'b', 'c', 'd'};
        System.out.println(Arrays.toString(cs));

        String.valueOf(cs);
        String str=new String(cs);
        System.out.println(str);
    }

}