//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
// 注意：给定 n 是一个正整数。 
//
// 示例 1：
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2：
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 记忆化搜索 数学 动态规划 
// 👍 1794 👎 0

package leetcode.editor.cn;
public class ClimbingStairs {
 public static void main(String[] args) {
  Solution solution = new ClimbingStairs().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 动态规划
    // 考虑最后一次爬楼梯可以爬一级或者两级，所以f(n) = f(n-1) + f(n-2)
    public int climbStairs(int n) {
        int[] climb = new int[n + 1];
        //爬上几级楼梯有几种方式(初始化前两级楼梯的情况）
        climb[0] = 1;
        climb[1] = 1;
        for (int j = 2; j <= n; j++){
            climb[j] = climb[j - 1] + climb[j - 2];
        }
        return climb[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}