package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-6-17 10:53
 * @Version 1.0
 */

/**
 * 亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
 * 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
 * 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
 * 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
 *
 * 输入：[5,3,4,5]
 * 输出：true
 * 解释：
 * 亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
 * 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
 * 如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
 * 如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
 * 这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 *
 */
public class lab877 {
    public static void main(String[] args) {
        lab877.Solution solution = new lab877().new Solution();
        int[] piles= new int[]{5,3,4,5};
        boolean a = solution.stoneGame(piles);
        System.out.println(a);
    }

    class Solution {
        public boolean stoneGame(int[] piles) {
            int length = piles.length;
            //二维数组dp记录的是从i到j堆石子中数目最大差值
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++){
                //dp[i][i]指的是石子只剩i这一堆，所以石子数目最大差值就是这一堆石子的数量
                dp[i][i] = piles[i];
            }

            for (int i = length - 2; i >= 0; i--){  //先从这一堆石子的末尾两个开始考虑，然后逐步扩展石子包括的范围
                for (int j = i + 1; j < length; j++){
                    // 选择了第i堆，意味着后续的差距石子的右端，选择了第j堆，意味着后续的差距在于石子的左端
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }
            }
        return dp[0][length-1] > 0;
        }
    }
}