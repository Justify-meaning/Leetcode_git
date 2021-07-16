package leetcode.everyday;


/**
 * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 * arr 中 第一个 元素必须为 1 。
 * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），
 * 都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
 *
 * 你可以执行以下 2 种操作任意次：
 * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
 * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
 *
 * 示例 1：
 * 输入：arr = [2,2,1,2,1]
 * 输出：2
 * 解释：
 * 我们可以重新排列 arr 得到 [1,2,2,2,1] ，该数组满足所有条件。
 * arr 中最大元素为 2 。
 *
 * 示例 2：
 * 输入：arr = [100,1,1000]
 * 输出：3
 * 解释：
 * 一个可行的方案如下：
 * 1. 重新排列 arr 得到 [1,100,1000] 。
 * 2. 将第二个元素减小为 2 。
 * 3. 将第三个元素减小为 3 。
 * 现在 arr = [1,2,3] ，满足所有条件。
 * arr 中最大元素为 3 。
 *
 * 示例 3：
 * 输入：arr = [1,2,3,4,5]
 * 输出：5
 * 解释：数组已经满足所有条件，最大元素为 5 。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^9
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Hcs
 * @Date 2021-7-15 10:47
 * @Version 1.0
 */
public class lab1846 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i+1] > arr[i]){
                arr[i+1] = arr[i] + 1;
            }
        }
        return arr[arr.length - 1];
    }

    //计数排序 + 贪心
    public int maximumElementAfterDecrementingAndRearranging2(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        //用cnt数组统计每个数字出现的次数，大于n的数字可以视为n，因为最大结果不会超过n
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            //当此位置没有数字时，错失数+1
            if (cnt[i] == 0) {
                ++miss;
            } else {
                //找到后面多出的数字补缺，及时溢出也只能补完miss个元素，miss不能小于0
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        //总数减去miss数
        return n - miss;
    }



}