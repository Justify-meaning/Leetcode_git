package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-17 10:20
 * @Version 1.0
 */

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class jianzhi42 {
    /**
     * 动态规划
     * 因此我们只需要求出每个位置的 f(i)，然后返回f数组中的最大值即可。那么我们如何求 f(i) 呢？
     * 我们可以考虑 nums[i]单独成为一段还是加入 f(i-1) 对应的那一段，
     * 这取决于nums[i] 和 f(i−1)+nums[i] 的大小，我们希望获得一个比较大的，
     * 于是可以写出这样的动态规划转移方程：
     * f(i)=max{f(i−1)+nums[i],nums[i]}
     * @param nums
     * @return
     */

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i=1;i < nums.length;i++){
            dp[i] = Math.max(dp[i-1] + nums[i],nums[i]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
            int res = nums[0];
            for(int i = 1; i < nums.length; i++) {
                nums[i] += Math.max(nums[i - 1], 0);
                res = Math.max(res, nums[i]);
            }
            return res;
    }
}