//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
// 请你找出所有和为 0 且不重复的三元组。
// 注意：答案中不可以包含重复的三元组。

// 示例 1：
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
// 示例 2：
//输入：nums = []
//输出：[]
//
// 示例 3：
//输入：nums = [0]
//输出：[]
//
// 提示：
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 3426 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
 public static void main(String[] args) {

     Solution solution = new ThreeSum().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
 class Solution {
     public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // nums为空或长度小于3时，无法构成三元组
        if (nums == null || nums.length <= 2)
            return ans;
        // 对数组进行排序，从小到大
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++){  //注意循环的设置，需要满足三元组的要求
            //如果第一个数就大于0，那么后续的数都大于等于第一个数，所以不存在可以满足要求的三元组
            if (nums[0] > 0){
                break;
            }
            // 去除重复的数字 选择跳出循环,继续搜素不一样的数字
            if (i > 0 && nums[i] == nums[i-1]){
                continue;   //跳出本次循环
            }
        //    将三元组问题转化了两个数的值等于第三个数，将第三个数设为一个target值
            int target = -nums[i];  //当前值为target 因为和为0，所以target为负数
        //    使用双指针遍历正序数组
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                //满足要求的情况 将left，right，i添加进三元组
                if (nums[left] + nums[right] == target){
                    ans.add(new ArrayList<>(Arrays.asList(nums[left], nums[right], nums[i])));
                    // 添加了此组数据作为三元组后，left，right指针需要更新
                    left++;
                    right--;
                    // 找到相同的数据继续寻找下一个数
                    while (left < right && nums[left] == nums[left-1]) left++;
                    while (left < right && nums[right] == nums[right+1]) right--;
                }
                else if (nums[left] + nums[right] < target){
                    left++;
                }
                else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ans;
     }
 }
//leetcode submit region end(Prohibit modification and deletion)

}