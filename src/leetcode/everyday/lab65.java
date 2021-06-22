package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-6-17 19:39
 * @Version 1.0
 */

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 有效数字（按顺序）可以分成以下几个部分：
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 *
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 */
public class lab65 {
    public static void main(String[] args) {
        lab65.Solution solution = new lab65().new Solution();
        lab65.Solution2 solution2 = new lab65().new Solution2();
    }

    //自动状态转移机
    class Solution {
        public static final int S0 = 1;     //初始状态
        public static final int S1 = 2;     //符号
        public static final int S2 = 4;     //整数
        public static final int S3 = 8;     //左无整数小数点
        public static final int S4 = 16;    //小数
        public static final int S5 = 32;    //指数e
        public static final int S6 = 64;    //指数符号
        public static final int S7 = 128;   //有效数字

        public boolean isNumber(String s) {
            char[] c = s.toCharArray();
            int n = c.length;
            int state = 1;
            for(int i = 0; i < n; i++){
                switch(state){
                    case S0 :
                        if(c[i] == '+' || c[i] == '-')
                            state = S1;
                        else if(Character.isDigit(c[i]))
                            state = S2;
                        else if(c[i] == '.')
                            state = S3;
                        else
                            return false;
                        break;
                    case S1 :
                        if(Character.isDigit(c[i]))
                            state = S2;
                        else if(c[i] == '.')
                            state = S3;
                        else
                            return false;
                        break;
                    case S2 :
                        if(Character.isDigit(c[i]))
                            state = S2;
                        else if(c[i] == '.')
                            state = S4;
                        else if(c[i] == 'e' || c[i] == 'E')
                            state = S5;
                        else
                            return false;
                        break;
                    case S3 :
                        if(Character.isDigit(c[i]))
                            state = S4;
                        else
                            return false;
                        break;
                    case S4 :
                        if(Character.isDigit(c[i]))
                            state = S4;
                        else if(c[i] == 'e' || c[i] == 'E')
                            state = S5;
                        else
                            return false;
                        break;
                    case S5 :
                        if(c[i] == '+' || c[i] == '-')
                            state = S6;
                        else if(Character.isDigit(c[i]))
                            state = S7;
                        else
                            return false;
                        break;
                    case S6 :
                        if(Character.isDigit(c[i]))
                            state = S7;
                        else
                            return false;
                        break;
                    case S7 :
                        if(Character.isDigit(c[i]))
                            state = S7;
                        else
                            return false;
                }
            }
            return state == S2 || state == S4 || state == S7;
        }
    }

    class Solution2 {
        public boolean isNumber(String s) {
            boolean ans = true, occur = false;
            int n = s.length();
            for (int i=0; i<n; i++) {
                char ch = s.charAt(i);
                //考虑一个符号字符“+”和“-”的情况
                if (ch == '+' || ch == '-') {
                    if (!(i < n-1 && ((s.charAt(i+1)-'0' >= 0 && s.charAt(i+1)-'0' <= 9) || s.charAt(i+1) == '.')))
                        return false;
                }
                //一个点的情况 后面必须跟着数字
                else if (ch == '.') {
                    if (!((i > 0 && s.charAt(i-1)-'0'>=0 && s.charAt(i-1)-'0'<=9) ||
                            (i < n-1 && s.charAt(i+1)-'0'>=0 && s.charAt(i+1)-'0'<=9)) || occur)
                        return false;
                    occur = true;
                }
                else if (ch == 'e' || ch == 'E') {
                    if (i == 0 || i == n-1)
                        return false;
                    else {
                        for (int j=i+1; j<n; j++) {
                            char c = s.charAt(j);
                            if ((c == '+' || c == '-') && !(j == i+1 && j != n-1))
                                return false;
                            if (c == '.' || (c-'a'>=0 && c-'a'<=25) || (c-'A'>=0 && c-'A'<=25))
                                return false;
                        }
                        break;
                    }
                }
                else if (ch-'0' >= 0 && ch-'0'<=9) {
                    if (i < n-1 && (s.charAt(i+1) == '+' || s.charAt(i+1) == '-'))
                        return false;
                }
                else
                    return false;
            }
            return ans;
        }
    }
}