//给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
// 字母异位词指字母相同，但排列不同的字符串。
//
// 示例 1:
//输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出: [["bat"],["nat","tan"],["ate","eat","tea"]] 
//
// 示例 2:
//输入: strs = [""]
//输出: [[""]]
//
// 示例 3:
//输入: strs = ["a"]
//输出: [["a"]]

// 提示：
// 1 <= strs.length <= 104 
// 0 <= strs[i].length <= 100 
// strs[i] 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 排序 
// 👍 787 👎 0

package leetcode.editor.cn;

import java.util.*;

public class GroupAnagrams {
 public static void main(String[] args) {

     Solution solution = new GroupAnagrams().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            //获取当前遍历到的字符串，转化为字符数组
            char[] array = str.toCharArray();
            //对字符数组进行排序
            Arrays.sort(array);
            //将排序后的字符串设置为map中的一个key
            String key = new String(array); //这里要用new String方法，不能用toString(),数组直接使用toString()的结果也是[类型@哈希值]
            //在map中找到这个key对应的value（一个存放String类型的list数组,代表一种字母异位词类型），如果没有就创建
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            //在这个对应key的value下添加当前遍历到的字符串
            list.add(str);
            //将此键值对添加进map
            map.put(key, list);
        }
        //分类返回map中的value值
        return new ArrayList<List<String>>(map.values());

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}