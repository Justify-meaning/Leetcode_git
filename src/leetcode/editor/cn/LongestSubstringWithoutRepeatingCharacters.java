//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。//
// 示例 1:
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
// 示例 2:
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
// 示例 3:
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

// 示例 4:
//输入: s = ""
//输出: 0

// 提示：
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 5615 👎 0

package leetcode.editor.cn;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
 public static void main(String[] args) {
  Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //滑动窗口机制解题
        HashSet<Character> set = new HashSet<Character>();
        int end = -1;  //记录终点的滑动指针,初始值为-1，相当于左边界的左侧
        int ans = 0; //记录最长滑动子串的长度

        for (int i = 0; i < s.length(); i++){
            if (i != 0){
                set.remove(s.charAt(i - 1)); //找到重复的字符，因此左侧应该找到下一个字符开始遍历；后续优化应该直接跳转的重复字符的下一个字符
            }

            while (end + 1 < s.length() && !set.contains(s.charAt(end + 1))){   //长度未超出字符串长度时并且没出现过此字母
            //    不断移动终点指针
                set.add(s.charAt(end + 1));
                ++end;
            }
        //    第i到终点指针处中间的字符为一个极长的无重复字符字串
            ans = Math.max(ans, end - i + 1);   //没执行一次循环得到一个ans值，保证记录下来的子串长度为极长的无重复字符子串
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}