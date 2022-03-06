//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 示例 1：
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
// 示例 2：
//输入：nums = [1]
//输出：1
//
// 示例 3：
//输入：nums = [0]
//输出：0
//
// 示例 4：
//输入：nums = [-1]
//输出：-1
//
// 示例 5：
//输入：nums = [-100000]
//输出：-100000
//
// 提示：
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 3486 👎 0

package leetcode.editor.cn;
public class MaximumSubarray {
 public static void main(String[] args) {

     Solution solution = new MaximumSubarray().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     /**
      *这题用动态规划可以写,要找到最大子数组连续和
      * max = sum[i-1] + nums[i]
      * 前面的子数组之和加上当前下标值，如果比原最大值大，则更新最大子数组连续和
      *当前面的子数组之和小于0时，可以舍弃掉
      */
    public int maxSubArray(int[] nums) {
        //定义前置数组和
        int sum = 0;
        //定义中间结果
        int result = 0;
        //定义最终返回值
        int max = nums[0];

        for (int i = 0; i < nums.length; i++){

            //如果前置和小于0，说明可以舍弃
            sum = Math.max(sum, 0);
            result = sum + nums[i];
            max = Math.max(result, max);
            sum += nums[i];
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


    //[-2,1,-3,4,-1,2,1,-5,4]
    //f(n) = max(f(n-1) + num, num) f(n-1) 代表之前最大的连续子数组
    public int maxSubArray2(int[] nums) {
        //用于记录每次前置数组和
        int sum = 0;
        int max = nums[0];
        //前置数组和每次比之前小的时候，要重新计算
        for (int num : nums) {
            //
            sum = Math.max(sum + num, num);
            max = Math.max(sum, max);
        }
        return max;
    }

    //不优化空间
    public int maxSubArray3(int[] nums) {
        int len = nums.length;
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[len];
        dp[0] = nums[0];

        for (int i = 1; i < len; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }

        // 也可以在上面遍历的同时求出 res 的最大值，这里我们为了语义清晰分开写，大家可以自行选择
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

