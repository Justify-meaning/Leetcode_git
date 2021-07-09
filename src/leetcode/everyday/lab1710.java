package leetcode.everyday;
/**
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。
 * 若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 *
 * 示例 1：
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 *
 * 示例 2：
 * 输入：[3,2]
 * 输出：-1
 *
 * 示例 3：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Hcs
 * @Date 2021-7-9 10:15
 * @Version 1.0
 */

public class lab1710 {
    /**摩尔投票法
     * Boyer-Moore 投票算法的基本思想是：在每一轮投票过程中，从数组中删除两个不同的元素，
     * 直到投票过程无法继续，此时数组为空或者数组中剩下的元素都相等。
     * 如果数组为空，则数组中不存在主要元素；
     * 如果数组中剩下的元素都相等，则数组中剩下的元素可能为主要元素。
     * 再进行一次循环判断
     */
    public int majorityElement(int[] nums) {
        int mainnum = nums[0];    //可能的主要元素
        int cnt = 1;    //超出其他元素的出现次数
        int appear = 0; //检验最终出现次数
        for (int i = 1; i < nums.length; i++){
            if (nums[i] != mainnum){    //此时出现的数字与主要元素不同
                cnt--;
                if (cnt == 0){  //如果出现次数已经减为0，那么将新出现的数字设置为主要数字
                    mainnum = nums[i];
                    cnt = 1;
                }
            }
            else {
                cnt++;
            }
        }
        //遍历检验
        for (int num : nums){
            if (num == mainnum) {
                appear++;
                if (appear > nums.length / 2)
                    return num;
            }
        }

        return -1;
    }

    //哈希表实现
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
            if(map.get(num) > nums.length / 2)
                return num;
        }
        return -1;
    }



}