//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
// 说明：每次只能向下或者向右移动一步。
//
// 示例 1：
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
//
// 示例 2：
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
//
// 提示：
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 100 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 954 👎 0

package leetcode.editor.cn;
public class MinimumPathSum {
 public static void main(String[] args) {
  Solution solution = new MinimumPathSum().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 使用动态规划，结果数组每个位置的最小值为min（result[i-1][j] + grid[i][j], result[i][j-1] + grid[i][j])
    public int minPathSum(int[][] grid) {
        //动态规划数组 注意：这里行列长度不一样

        int rows = grid.length, columns = grid[0].length;
        int[][] result = new int[rows][columns];

        if(grid == null || rows == 0 || columns == 0){
            return 0;
        }

        //初始化左上角的值
        result[0][0] = grid[0][0];


        //给第一列标记数据
        for (int i = 1; i < rows; i++){
            result[i][0] = result[i - 1][0] + grid[i][0];
        }
        //给第一行标记数据
        for (int j = 1; j < columns; j++){
            result[0][j] = result[0][j - 1] + grid[0][j];
        }

        //动态规划计算result
        for (int i = 1; i < rows; i++){
            for (int j = 1; j < columns; j++){
                result[i][j] = Math.min(result[i-1][j] + grid[i][j], result[i][j-1] + grid[i][j]);
            }
        }

        return result[rows - 1][columns - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}