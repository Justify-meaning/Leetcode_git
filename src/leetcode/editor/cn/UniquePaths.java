//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
// 问总共有多少条不同的路径？
//
// 示例 1：
//输入：m = 3, n = 7
//输出：28 
//
// 示例 2：
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
//
// 示例 3：
//输入：m = 7, n = 3
//输出：28
//
// 示例 4：
//输入：m = 3, n = 3
//输出：6
// 提示：
// 1 <= m, n <= 100 
// 题目数据保证答案小于等于 2 * 109 
// 
// Related Topics 数学 动态规划 组合数学 
// 👍 1063 👎 0

package leetcode.editor.cn;
public class UniquePaths {
 public static void main(String[] args) {
  Solution solution = new UniquePaths().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     /**
      * 我们用 f(i, j)表示从左上角走到 (i, j) 的路径数量，其中 i 和 j 的范围分别是 [0, m) 和 [0, n)。
      * 由于我们每一步只能从向下或者向右移动一步，因此要想走到 (i, j)(i,j)，如果向下走一步，那么会从 (i-1, j) 走过来；
      * 如果向右走一步，那么会从 (i, j-1) 走过来。因此我们可以写出动态规划转移方程：
      * f(i, j) = f(i-1, j) + f(i, j-1)
      * @param m
      * @param n
      * @return
      */

    public int uniquePaths(int m, int n) {
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++){
        //    走到第一列的任意位置路径数量都只有一条
            result[i][0] = 1;
        }

        for (int j = 0; j < n; j++){
        //    走到第一行的任意位置路径数量都只有一条
            result[0][j] = 1;
        }

        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                //走到（i,j）点相当于（i-1,j）向下走一步和（i，j-1）向右走一步
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }
        //返回动态规划数组右下角的值，就是统计得到的全部路径数量
        return result[m - 1][n - 1];

    }

     //数学排列组合的计算方法
     public int uniquePaths2(int m, int n) {
         //只跟第几行第几列有关，从m+n-2步中抽出m-1步
         long ans=1;
         for(int i=0;i<Math.min(m-1,n-1);i++){
             ans*=m+n-2-i;
             ans/=i+1;
         }
         return (int)ans;
     }
}
//leetcode submit region end(Prohibit modification and deletion)

}