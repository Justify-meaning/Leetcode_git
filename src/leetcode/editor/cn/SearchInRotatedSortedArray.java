//整数数组 nums 按升序排列，数组中的值 互不相同 。 
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
// 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
// （下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
// 给你旋转后的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

// 示例 1：
//输入：nums = [4,5,6,7,0,1,2], target = 0
//输出：4
//
// 示例 2：
//输入：nums = [4,5,6,7,0,1,2], target = 3
//输出：-1 
//
// 示例 3：
//输入：nums = [1], target = 0
//输出：-1

// 提示：
// 1 <= nums.length <= 5000 
// -10^4 <= nums[i] <= 10^4 
// nums 中的每个值都 独一无二 
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转 
// -10^4 <= target <= 10^4 

// 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 1428 👎 0

package leetcode.editor.cn;
public class SearchInRotatedSortedArray {
 public static void main(String[] args) {

     Solution solution = new SearchInRotatedSortedArray().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right){  //必须有等号，如果数组只有一个数字时还能执行一次循环，判断target值是否就是数组中的那个数
            int mid = (left + right) / 2;
            if (target == nums[mid])
                return mid;
        //    根据题目条件，数组的左半部分或者右半部分必然有一部分有序
            else if (nums[mid] < nums[right]){   //如果中间值小于最右边值，那么说明右半部分有序
                //如果target值大于中间值小于右端值，那么必然在右半数组
                if (nums[mid] < target && target <= nums[right]){   //注意等号
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }else { //如果中间值大于最右边值，那么说明左半部分有序
                //如果target值大于左端值小于中间值，那么必然在左半数组
                if (nums[left] <= target && target < nums[mid]){    //注意等号
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
        }
        return -1;
     }
    // 超时。。。
    public int search_me(int[] nums, int target) {

        //使用二分查找的方法，从中间开始比较
        int left = 0;
        int right = nums.length - 1;
        int length = nums.length;

        if (length == 0) {
            return -1;
        }
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }

        while (left <= right){
            int mid = (left + right) / 2;
            //  此处设置二分查找的的数值
            if (nums[mid] == target){
                return mid;
            }
            //目标值大于中间值，但是大于最右端的值，此时如果存在符合结果的数组下标，那么一定在左半数组
            else if(target > nums[mid] && target > nums[right]){
                right = mid - 1;
            }else if (target > nums[mid] && target < nums[right]){//目标值大于中间值，小于最右端的值，在有半数组
                left = mid + 1;
            //    目标值小于中间值,大于最左端的值，如果存在符合要求的结果，在左半数组
            }else if (target < nums[mid] && target > nums[left]){
                right = mid - 1;
            }else if (target < nums[mid] && target < nums[left]){  //    目标值小于中间值,小于最左端的值，在右半数组
                left = mid + 1;
            }
        }
        return -1;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}