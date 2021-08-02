//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
// 判断你是否能够到达最后一个下标。
//
// 示例 1：
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
//
// 示例 2：
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
//
// 提示：
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1281 👎 0

package leetcode.editor.cn;
public class JumpGame {
 public static void main(String[] args) {
  Solution solution = new JumpGame().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     /**
      * 能否跳到最后一个数组小标，取决与前面到的点，加上该点能跳的距离
      * 设想一下，对于数组中的任意一个位置 y，我们如何判断它是否可以到达？
      * 根据题目的描述，只要存在一个位置 x，它本身可以到达，
      * 并且它跳跃的最大长度为 x + nums[x]，这个值大于等于 y，即 x + nums[x]≥y，那么位置 y 也可以到达。
      *
      * @param nums
      * @return
      */

    public boolean canJump(int[] nums) {
        int result = nums[0];//记录能到达的最远下标
        for(int i = 0; i < nums.length; i++ ){
            //能到达的最远位置，为当前能到达的最远位置与当前下标与当前数组值之和二者之间的最大值（受限与result）
            if (i <= result){
                result = Math.max(result, i + nums[i]);
            }
            //当前结果大于等于最大下标值，说明可到达
            if (result >= nums.length - 1){
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}