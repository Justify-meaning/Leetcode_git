package leetcode.everyday;

/**
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
 * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
 * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
 * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 10^9 + 7 取余 后返回。
 * |x| 定义为：
 * 如果 x >= 0 ，值为 x ，或者
 * 如果 x <= 0 ，值为 -x
 *  
 * 示例 1：
 * 输入：nums1 = [1,7,5], nums2 = [2,3,5]
 * 输出：3
 * 解释：有两种可能的最优方案：
 * - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
 * - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
 * 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
 *
 * 示例 2：
 * 输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * 输出：0
 * 解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
 *
 * 示例 3：
 * 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * 输出：20
 * 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
 * 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 *  
 * 提示：
 * n == nums1.length
 * n == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= 10^5
 *
 */

import java.util.Arrays;

/**
 * @Author Hcs
 * @Date 2021-7-14 17:33
 * @Version 1.0
 */
public class lab1818 {

    /**
     * 计算公式
     *|nums1[i] - nums2[i]|-|nums1[i] - nums2[j]|
     * @param nums1
     * @param nums2
     * @return
     * - 「找到 `nums1` 中最接近 `nums2[i]` 的值」，
     * 这个值可能在二分查找过程中的 `mid-1`/`mid`/`mid+1` 中出现，为了不侵入二分查找，
     * 可以转化为「找到 `nums1` 中大于等于 `nums2[i]` 的最小下标 j」，
     * 那么该值可能在 `j`（`j < n`） 或 `j-1`（`j > 0`）
     * - 遍历过程中使用了取模操作，可能会导致最终 `sum < maxn`，
     * 因此结果要使用 `(sum - maxn + mod) % mod` 的形式
     */
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        Arrays.sort(rec);
        int sum = 0, maxn = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch(rec, nums2[i]);
            if (j < n) {
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
            }
        }
        return (sum - maxn + MOD) % MOD;
    }

    public int binarySearch(int[] rec, int target) {
        int low = 0, high = rec.length - 1;
        if (rec[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (rec[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1,70465,53287,5793,964};
        int[] nums2 = new int[]{2,34325,57622,79431,3456};
        lab1818 l_1818 = new lab1818();
        int absSum = l_1818.minAbsoluteSumDiff(nums1, nums2);
        System.out.println(absSum);
    }
    //错误思路：例如[1,28,21] [9,21,20]，最佳情况是将28替换为21，而已最大差值的思路则会寻求替换1，因此无法找到正确答案
    public int minAbsoluteSumDiff2(int[] nums1, int[] nums2) {
        int a = 1000000007;
        int absSum = 0; //定义绝对差值和
        int maxAbs = 0; //定义最大绝对差值
        int num2 = 0; //记录最大绝对差值num2对应的数字

        for(int i = 0; i < nums1.length; i++){
            int abs = Math.abs(nums1[i] - nums2[i]);
            absSum += abs;
            //System.out.println("此次计算的absSum为：" + absSum);
            //记录下差值最大的数字
            if (abs > maxAbs){
                maxAbs = abs;
                num2 = nums2[i];
            }
        }

        //特殊情况
        if (absSum == 0){
            return absSum;
        }

    //    找到与nums[2]下标最接近的数
        int minAbs = Math.abs(nums1[0] - num2); //找到最小绝对差值
        for (int j = 1; j < nums1.length; j++){
            //逐个计算找到最小差值
            int abs2 = Math.abs(nums1[j] - num2);
            minAbs = Math.min(minAbs, abs2);
        }

    //    找到最小差值后，计算最大差值和最小差值的差即可算出最后结果
        absSum = absSum - (maxAbs - minAbs);
        return absSum % a;
    }
}