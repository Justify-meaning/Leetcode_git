//给你一个字符串 s，找到 s 中最长的回文子串。

// 示例 1：
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 示例 2：
//输入：s = "cbbd"
//输出："bb"
// 示例 3：
//输入：s = "a"
//输出："a"
// 示例 4：
//输入：s = "ac"
//输出："a"

// 提示：
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3728 👎 0

package leetcode.editor.cn;
public class LongestPalindromicSubstring {
 public static void main(String[] args) {
     Solution solution = new LongestPalindromicSubstring().new Solution();
 }

 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     //以某个元素为中心，分别计算偶数长度的回文最大长度和奇数长度的回文最大长度。
     public String longestPalindrome(String s) {
         if (s == null || s.length() < 1) {
             return "";
         }

         //初始化回文串的左端和右端
         int start = 0;
         int end = 0;

         for (int i = 0; i < s.length() - 1; i++) {
             int len1 = PalindromeHelper(s, i, i); //回文字符串长度为奇数的情况,
             int len2 = PalindromeHelper(s, i, i + 1); //回文字符串长度为偶数的情况，所谓中点夹在两数的中间
             int len = Math.max(len1, len2);
         //    计算回文串的起点和终点
             if (len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
             }
         }
         // 注意：这里的end+1是因为 java自带的左闭右开的原因
         return s.substring(start, end + 1);
    }
     //从中心开始 分别向左右移动两个指针
     public int PalindromeHelper(String s, int left, int right) {
         // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
         // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
         // 跳出循环的时候恰好满足 s.charAt(left) ！= s.charAt(right)
         //左右未超出字符串的边界，同时新拓展的的字符仍能组成回文串的一部分
         while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
             left--;
             right++;
         }
        // 即使不是回文数的时候 也会自减自增一次，一次计算回文数的公式为
        // (right-left+1)-2
        return right - left - 1;
     }
}
//leetcode submit region end(Prohibit modification and deletion)

}