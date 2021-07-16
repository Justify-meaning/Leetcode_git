package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-16 10:41
 * @Version 1.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 统计一个数字在排序数组中出现的次数。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *  
 */
public class jianzhi55 {

//    寻找插入点，简洁版
//    public int search(int[] nums, int target) {
//        return helper(nums, target) - helper(nums, target - 1);
//    }
//    int helper(int[] nums, int tar) {
//        int i = 0, j = nums.length - 1;
//        while(i <= j) {
//            int m = (i + j) / 2;
//            if(nums[m] <= tar) i = m + 1;
//            else j = m - 1;
//        }
//        return i;
//    }
//}

    //二分查找 找到target值后也需要继续二分，找两侧的端点
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        //left_target最后一次会left+1，找到的恰好是第一个符合target的数字（如果有），然后跳出循环
        //而right_target最后一次定位的是target右边的一个数（返回的是left），left>right才跳出循环
        int left_target = twosort(nums, target, left, right, false);
        int right_target = twosort(nums, target, left, right, true);
        return right_target - left_target;
    }

    public int twosort(int[] nums, int target, int left, int right, boolean leftorright){
        while (left <= right){
            int mid = left + (right - left) / 2;
            //找到target值，在左边数组继续寻找左边界
            if (nums[mid] == target && leftorright == false){
                right = mid - 1;
            //    找到target值，在右边数组继续寻找右边界
            }else if (nums[mid] == target && leftorright == true){
                left = mid + 1;
            }
            //比目标值大，缩小区域，在左半边继续寻找target
            if (nums[mid] > target){
                right = mid - 1;
            }
            if (nums[mid] < target){
                left = mid + 1;
            }
        }
        return left;
    }

    //搜索两个边界值，即两边第一个不为target的值
    //public int search(int[] nums, int target) {
    //    // 搜索右边界 right
    //    int i = 0, j = nums.length - 1;
    //    while(i <= j) {
    //        int m = (i + j) / 2;
    //        if(nums[m] <= target) i = m + 1;
    //        else j = m - 1;
    //    }
    //    int right = i;
    //    // 若数组中无 target ，则提前返回
    //    if(j >= 0 && nums[j] != target) return 0;
    //    // 搜索左边界 right
    //    i = 0; j = nums.length - 1;
    //    while(i <= j) {
    //        int m = (i + j) / 2;
    //        if(nums[m] < target) i = m + 1;
    //        else j = m - 1;
    //    }
    //    int left = j;
    //    return right - left - 1;
    //}

    public int search2(int[] nums, int target) {
        //存入哈希表进行查找
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (int i : map.keySet()){
            if (i == target){
                return map.get(i);
            }
        }

        return 0;
    }
}