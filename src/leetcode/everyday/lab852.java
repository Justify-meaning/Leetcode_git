package leetcode.everyday;

/**
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 *
 */

/**
 * @Author Hcs
 * @Date 2021-6-15 13:58
 * @Version 1.0
 */
public class lab852 {
    //创建一个实体对象
    public static void main(String[] args) {
        lab852.Solution solution = new lab852().new Solution();
        lab852.Solution2 solution2 = new lab852().new Solution2();
        lab852.Solution3 solution3 = new lab852().new Solution3();
    }

    class Solution {
        public int peakIndexInMountainArray(int[] arr) {
            //使用二分查找来查找峰顶值
            int left = 0, right = arr.length - 1;   //记录左右下标
            int medium = (left + right) / 2;
            while (medium != 0){
                // 已找到峰顶值
                if (arr[medium] > arr[medium + 1] && arr[medium] > arr[medium - 1])
                    return medium;
                // 如果比下一个值大，则说明已增长到峰顶值，那么峰顶在medium本身及其左边
                else if (arr[medium] > arr[medium + 1]){
                    right = medium;
                    medium = (left + right) / 2;
                }
                // 如果没有下一个值大，则说明未增长到峰顶值，峰顶在medium本身及其右边
                else if (arr[medium] < arr[medium + 1]){
                    left = medium;
                    medium = (left + right) / 2;
                }
            }
        return medium;
        }
    }

    //二分查找模板 题目条件说明至少有三个数的数组，且峰顶值只有一个 区间为闭区间
    class Solution2 {
        public int peakIndexInMountainArray(int[] arr) {
            int n = arr.length;
            int left = 1, right = n - 2, ans = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] > arr[mid + 1]) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return ans;
        }
    }

    class Solution3 {
        //使用二分查找 无需新建结果变量进行保存 区间为开区间
        public int peakIndexInMountainArray(int[] arr) {

            int low = 0;
            int high = arr.length -1;
            int mid = 0;
            while(low < high){
                // 防止数据溢出，但是如果循环体中出现low = mid时可能会存在死循环的问题（出现死循环需要改为向上取整）
                mid = low + (high - low) / 2;
                // 如果 arr[mid] < arr[mid + 1], 则下标i一定是在mid的右侧[mid+1, high]
                if(arr[mid] < arr[mid + 1]){
                    low = mid + 1;
                }else{
                    // 反之则下标i一定是在mid的左侧并且包括mid位置[low, mid]（题目数据保证 arr 是一个山脉数组）
                    high = mid;
                }
            }
            // 循环结束条件是 low = high , 因此返回low 或 high 都可以
            return low;
        }
    }

}