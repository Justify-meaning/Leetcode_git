package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-8-16 16:07
 * @Version 1.0
 */
//最长回文子序列
public class lab516 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        //从末端的子序列开始，通过动态规划，最终得到整个子序列的最长回文子序列
        for (int i = n - 1; i >= 0; i--){
            dp[i][i] = 1;
            char c1 = s.charAt(i);
            for (int j = i + 1; j < n; j++){
                char c2 = s.charAt(j);
                //假如子序列外的左右端字符相等，显然可以将它们添加进入回文子序列,回文子序列长度增加
                if (c1 == c2){
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                //子序列外的左右端字符不相等,端点只可能取左端或右端作为子序列新的端点（只拓展检查的边界，不拓展回文子序列的长度）
                 else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}