package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-8 10:32
 * @Version 1.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal的非空子数组。
 * 子数组 是数组的一段连续部分。
 *
 * 示例 1：
 * 输入：nums = [1,0,1,0,1], goal = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 示例 2：
 * 输入：nums = [0,0,0,0,0], goal = 0
 * 输出：15
 */
public class lab930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        // 前缀和+哈希表
        Map<Integer, Integer> sums = new HashMap<Integer, Integer>();   //统计前缀和的哈希表
        int sum = 0;    //初始前缀和为0
        int res = 0;    //记录结果
        for (int num : nums){
            //找到当前前缀和，出现次数+1
            sums.put(sum, sums.getOrDefault(sum, 0) + 1);
            sum += num; //更新前缀和的数值
            //更新结果数值,通过计算当前数值前缀和-目标值，找到剩余数值前缀和出现的数目，从未出现则置为0
            res += sums.getOrDefault(sum - goal, 0);
        }
        return res;
    }

    //  滑动窗口，永远滴神
    public int numSubarraysWithSum2(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) { //和等于目标值跳出循环
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {    //和小于目标值才跳出循环
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

}