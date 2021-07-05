//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 示例 1：
//输入：nums = [1,2,3]
//输出：[1,3,2]
//
// 示例 2：
//输入：nums = [3,2,1]
//输出：[1,2,3]
//
// 示例 3：
//输入：nums = [1,1,5]
//输出：[1,5,1]
//
// 示例 4：
//输入：nums = [1]
//输出：[1]
//
// 提示：
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1194 👎 0

package leetcode.editor.cn;
public class NextPermutation {
 public static void main(String[] args) {

     Solution solution = new NextPermutation().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        //首先从后向前查找第一个顺序对 (i,i+1)(i,i+1)，满足 a[i] < a[i+1]a[i]<a[i+1]。
        // 这样「较小数」即为 a[i]a[i]。此时 [i+1,n)[i+1,n) 必然是下降序列。
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }

        //如果找到了顺序对，那么在区间 [i+1,n)[i+1,n) 中从后向前查找第一个元素 jj 满足 a[i] < a[j]a[i]<a[j]。
        // 这样「较大数」即为 a[j]a[j]。
        if (i >= 0){
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        //转置的范围是[i+1,end]
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    private void reverse(int[] nums, int a, int b){
        while (a < b){
            swap(nums, a, b);
            a++;
            b--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}