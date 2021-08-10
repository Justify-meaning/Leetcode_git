//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
// 垂直线 i 的两个端点分别为 (i,ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
// 说明：你不能倾斜容器。
// 示例 1：
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2：
//输入：height = [1,1]
//输出：1
//
// 示例 3：
//输入：height = [4,3,2,1,4]
//输出：16
//
// 示例 4：
//输入：height = [1,2,1]
//输出：2
//
// 提示：
// n = height.length 
// 2 <= n <= 3 * 104 
// 0 <= height[i] <= 3 * 104 
// 
// Related Topics 数组 双指针 
// 👍 2537 👎 0

package leetcode.editor.cn;
public class ContainerWithMostWater {
 public static void main(String[] args) {
  Solution solution = new ContainerWithMostWater().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     public int maxArea(int[] height){
     //    原理：因此所有消去的状态的面积都 < S(i, j)<S(i,j)。通俗的讲，我们每次向内移动短板，所有的消去状态都不会导致丢失面积最大值 。
     //    双指针做法：定义首尾两个指针，高度较低的指针像中间推进（考虑增加纵轴长度对于增加面积的影响大于减少横轴长度）
         int start = 0;
         int end = height.length - 1;
         int area = Math.min(height[start], height[end]) * (end - start);
         while (end > start){
             //如果头指针指向的数字大于尾指针指向的数字，则尾指针向前移动
             if (height[start] >= height[end]){
                 end--;
                 area = Math.max(area, Math.min(height[start], height[end]) * (end - start));
             }
             // 否则头指针向前移动
             else {
                 start++;
                 area = Math.max(area, Math.min(height[start], height[end]) * (end - start));
             }
         }
        return area;
     }
    //public int maxArea(int[] height) {
    //    //双重暴力循环 超时
    //    int area = 0;
    //    for (int i = 0; i < height.length; i++){
    //        for (int j = i + 1; j < height.length; j++){
    //            if (height[j] >= height[i]){    //后面的竖直线大于等于，以前面的高度作为纵轴长度
    //                area = Math.max(area, height[i] * (j - i));
    //            }
    //            else {  //否则以后面的竖直线作为纵轴长度
    //                area = Math.max(area, height[j] * (j - i));
    //            }
    //        }
    //    }
    //    return area;
    //}
}
//leetcode submit region end(Prohibit modification and deletion)

}