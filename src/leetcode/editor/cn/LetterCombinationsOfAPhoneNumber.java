//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

// 示例 1：
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
// 示例 2：
//输入：digits = ""
//输出：[]
//
// 示例 3：
//输入：digits = "2"
//输出：["a","b","c"]
// 提示：
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1361 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {

 public static void main(String[] args) {
     Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
     String digits = "475";
     solution.letterCombinations(digits);
     List<String> list = solution.res;
     for (int i = 0; i < list.size(); i++){
         System.out.print(list.get(i) + "　");
     }
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     private String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"}; //记录每个数字对应的字母
     private StringBuilder stringBuilder = new StringBuilder(); //记录每种情况的字符变化过程
     private List<String> res = new ArrayList<>();  //结果数组


     public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0){
            return res;
        }
        backtrack(digits, 0);
        return res;
     }

     //回溯函数 字符串遍历至跳出循环后，逐位回溯，最终找到所有数字对应的数组
     public void backtrack(String digits, int index){   // 传入数字串以及每次启动循环是第几位
         //字符串的字母数已经与拨号数相同时，说明已经遍历完一种情况，可以给结果数组添加一个参数
         if (stringBuilder.length() == digits.length() ){
             res.add(stringBuilder.toString());
             return;
         }

         //先把当前数字代表的几个字母记为一个字符串，用于后续遍历
         String tmp = map[digits.charAt(index) - '2'];
         //对当前数字所代表的字符串进行循环
         for (char ch : tmp.toCharArray()){
             stringBuilder.append(ch);
             backtrack(digits, index + 1);  //找到下一位数字继续进行流程，知道字符串长度等于数字串长度即可跳出循环进行回溯
             stringBuilder.deleteCharAt(stringBuilder.length() - 1);    //删去最后一位进行回溯
         }
     }


}

//leetcode submit region end(Prohibit modification and deletion)

}