//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 

// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。
//
// 示例 1：
//输入：s = "()"
//输出：true
//
// 示例 2：
//输入：s = "()[]{}"
//输出：true
//
// 示例 3：
//输入：s = "(]"
//输出：false
//
// 示例 4：
//输入：s = "([)]"
//输出：false
//
// 示例 5：
//输入：s = "{[]}"
//输出：true 
//
// 提示：
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2461 👎 0

package leetcode.editor.cn;

import java.util.Stack;

public class ValidParentheses {
 public static void main(String[] args) {

     Solution solution = new ValidParentheses().new Solution();
     solution.isValid("([])");
 }
 //leetcode submit region begin(Prohibit modification and deletion){
 class Solution {
     public boolean isValid(String s) {
         if (s.length() == 0){
             return true;
         }
         Stack<Character> stack = new Stack<>();
         for (char c : s.toCharArray()) {
             //注意这里else if保证不会每次都判断最后一个if（使得判断结果恒为false)
             if (c == '(') {
                 stack.push(')');
             }
             else if (c == '[') {
                 stack.push(']');
             }
             else if (c == '{') {
                 stack.push('}');
             }
             //两种错误情况：stack为空：没有合适与其匹配的符号；stack顶部元素与传入的c不符，意味着符号不匹配
             else if (stack.empty() || c != stack.pop()) {
                 return false;
             }
         }
         //输出完匹配符号后，栈恰好为空，则符合要求
         if (stack.empty()){
             return true;
         }
         //其余情况可能包括有多余的符号，这些统统返回false
         return false;
     }

    //public boolean isValid(String s) {
    //    //通过消去符号，判断最后字符串长度是否为0来判断字符串是否有效
    //    int length = s.length() / 2;
    //    //这里不能在循环内部设置s.length / 2，因为每次都有可能会更新字符串
    //    for (int i = 0; i < length; i++){
    //        s.replace("()","").replace("[]", "").replace("{}", "");
    //    }
    //    return s.length() == 0;
    //}
}
//leetcode submit region end(Prohibit modification and deletion)

}