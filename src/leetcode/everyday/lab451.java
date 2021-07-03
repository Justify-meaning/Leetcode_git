package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-3 14:01
 * @Version 1.0
 */

import java.util.*;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 *
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 *
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 */
public class lab451 {
    public String frequencySort(String s) {
        //  使用hashmap记录出现的字符和频率
        Map<Character, Integer> hashmap = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++){
            //获取当前字符
            char c = s.charAt(i);
            //getOrDefault() 方法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。
            //getOrDefault() 方法的语法为：
            //hashmap.get(Object key, V defaultValue)
            int frequency = hashmap.getOrDefault(c, 0) + 1;
            hashmap.put(c, frequency);
        }

        //  存入hashmap后，要将字符进行排序
        List<Map.Entry<Character, Integer>> characterList = new ArrayList<>(hashmap.entrySet());
        characterList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }

        });



        //  定义返回的字符串
        StringBuilder sortString = new StringBuilder();
        for (Map.Entry<Character, Integer> characterIntegerEntry : characterList) {
            //tmp暂存每次字符出现的次数
            int tmp = hashmap.get(characterIntegerEntry.getKey());
            for (int a = 0; a < tmp; a++) {
                //根据出现次数，逐个添加字符
                sortString.append(characterIntegerEntry.getKey());
            }
        }

        return sortString.toString();
    }

    public String frequencySort2(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
        }
        //注入map的全部key储存为list进行排序（根据key排序的简单方法）
        List<Character> list = new ArrayList<Character>(map.keySet());
        list.sort((a, b) -> map.get(b) - map.get(a));

        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            int frequency = map.get(c);
            for (int j = 0; j < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}