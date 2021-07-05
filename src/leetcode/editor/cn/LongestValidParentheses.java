//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
// 
// 示例 1：
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
//
// 示例 2：
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
//
// 示例 3：
//输入：s = ""
//输出：0
//
// 提示：
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')'
// Related Topics 栈 字符串 动态规划 
// 👍 1353 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class LongestValidParentheses {
 public static void main(String[] args) {

     Solution solution = new LongestValidParentheses().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     //还有一种动态规划的方法 转移方程比较难想

     //题目要表达的思路：例如：s = )(()())，我们用栈可以找到，
     //位置 2 和位置 3 匹配，
     //位置 4 和位置 5 匹配，
     //位置 1 和位置 6 匹配，
     //这个数组为：2,3,4,5,1,6 这是通过栈找到的，我们按递增排序！1,2,3,4,5,6
     //找出该数组的最长连续数列的长度就是最长有效括号长度！
     //所以时间复杂度来自排序：O(nlogn)。
     public int longestValidParentheses(String s) {
         if (s == null || s.length() == 0) return 0;
         Deque<Integer> stack = new ArrayDeque<>();
         stack.push(-1);
         //System.out.println(stack);
         int res = 0;
         for (int i = 0; i < s.length(); i++) {
             if (s.charAt(i) == '(') stack.push(i);
             else {
                 stack.pop();
                 if (stack.isEmpty()) stack.push(i);
                 else {
                     res = Math.max(res, i - stack.peek());
                 }
             }
         }
         return res;
     }


    // 题目有歧义，我做的是最长连续匹配括号长度
    public int longestValidParentheses2(String s) {
        char[] c = s.toCharArray();
        //定义两个栈分别存放左括号和右括号
        Stack<Character> stack1 = new Stack<>();
        //定义有效最长括号子串长度
        int max = 0;
        int num = 0;    //暂存该次括号字串长度
        for (int a = 0; a < c.length; a++){
            char i = c[a];
            if (i == '('){
                stack1.push(i);
            }else {
                //要判断stack1是否已有'('
                if (!stack1.isEmpty()){
                    stack1.pop();
                    num += 2;
                    max = num;
                    if (a + 1 != c.length && c[a+1] == ')'){
                        num = 0;
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
        }
        return max;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}