//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
// 示例 1：
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
// 示例 2：
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
//
// 示例 3：
//输入：nums = [1]
//输出：[[1]]
//
// 提示：
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 
// 👍 1440 👎 0

package leetcode.editor.cn;

import java.util.*;

public class Permutations {
 public static void main(String[] args) {

     Solution solution = new Permutations().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);

        dfs(nums, len, 0, path, used, res);
        return res;

    }

    //参数依次为需要全排列的数组，数组长度，当前遍历的深度，当前已遍历的路径，已被选择的数组中的数字，结果列表
    private void dfs(int[] nums, int len, int depth,
                     Deque<Integer> path, boolean[] used,
                     List<List<Integer>> res){

        //当前遍历的深度与数组长度相同，说明数组中的数字都被遍历过
        if (depth == len){
            res.add(new ArrayList<>(path));
            return;
        }

        //依次添加数组中的每个数字
        for (int i = 0; i < len; i++){
        //    先判断当前要添加数字是否被选择过
            if (!used[i]){
            //    没有被选择过，在末端添加进当前双端队列路径
                path.addLast(nums[i]);
            //    同时将该数字置为已经被选择过
                used[i] = true;

                //继续向更深一层遍历
                dfs(nums, len, depth + 1, path, used, res);
                //    将当前添加的数字移除，回溯至上一层
                path.removeLast();
                //    同时置为未被选择过
                used[i] = false;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}