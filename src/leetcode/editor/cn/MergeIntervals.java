//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
// 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

// 示例 1：
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
// 示例 2：
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 提示：
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104
// Related Topics 数组 排序 
// 👍 1036 👎 0

package leetcode.editor.cn;

import java.util.*;

public class MergeIntervals {
 public static void main(String[] args) {

     Solution solution = new MergeIntervals().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
     public int[][] merge(int[][] intervals) {
         if (intervals.length == 0) {
             return new int[0][2];
         }
         Arrays.sort(intervals, new Comparator<int[]>() {
             public int compare(int[] interval1, int[] interval2) {
                 return interval1[0] - interval2[0];
             }
         });
         List<int[]> merged = new ArrayList<int[]>();
         for (int i = 0; i < intervals.length; ++i) {
             int L = intervals[i][0], R = intervals[i][1];
             //将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
             //如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
             if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                 merged.add(new int[]{L, R});
             } else { //否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
                 merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
             }
         }
         //将List<int[]>转换为二维数组
         return merged.toArray(new int[merged.size()][]);
     }


    //麻了，这样写会有问题
    //public int[][] merge(int[][] intervals) {
    //    //只有一个集合时
    //    if (intervals.length == 1){
    //        return intervals;
    //    }
    //
    //    //对数组进行排序
    //    Arrays.sort(intervals, new Comparator<int[]>() {
    //        public int compare(int[] interval1, int[] interval2) {
    //            return interval1[0] - interval2[0];
    //        }
    //    });
    //
    //    //用map保证起点不同，这样没有重叠部分
    //    Map<Integer, Integer> map = new HashMap();
    //        //如果前一个集合的结束值 比后一个集合的起始值大，说明两个集合可以合并,循环合并
    //    //换种写法：找不能合并的对象，剩下的都是可以合并的
    //    int i = 0;
    //    while (i < intervals.length - 1){
    //        //前一个结束值比后一个起始小，说明没有交集
    //        if (intervals[i][1] < intervals[i + 1][0]){
    //            //如果没有交集，直接放入map
    //            map.put(intervals[i][0], intervals[i][1]);
    //        }
    //        else{
    //            if (intervals[i + 1][1] > intervals[i][1]){
    //                //如果后一个值比前一个大，跟进末尾值，扩大集合范围
    //                intervals[i][1] = intervals[i + 1][1];
    //                //    也更新下一行的初始值，使其与上一行一致
    //                intervals[i + 1][0] = intervals[i][0];
    //            }
    //            else {
    //                intervals[i + 1][1] = intervals[i][1];
    //            }
    //
    //            //将其放入map，因为hashmap的key唯一，所以不会储存同一起点的数组
    //            map.put(intervals[i][0], intervals[i][1]);
    //        }
    //
    //        //再把最后的一个结果判断是否放入map中
    //        if (intervals[intervals.length - 2][1] < intervals[intervals.length - 1][0]){
    //            map.put(intervals[intervals.length - 1][0], intervals[intervals.length - 1][1]);
    //        }
    //        i++;
    //    }
    //
    //    //另一种遍历集合的方式
    //    //    新建一个二维数组用于储存结果
    //    int num = 0;
    //    int[][] result = new int[map.size()][2];
    //    for (int key : map.keySet()) {
    //        result[num][0] = key;
    //        result[num][1] = map.get(key);
    //        num++;
    //    }
    //    return result;
    //
        //将哈希表的值存入结果数组
        //Iterator iter = map.entrySet().iterator();       //获取key和value的set
        //while (iter.hasNext()) {
        //    Map.Entry entry = (Map.Entry) iter.next();        //把hashmap转成Iterator再迭代到entry
        //    int key = (int)entry.getKey();        //从entry获取key
        //    int val = (int)entry.getValue();      //从entry获取value
        //    result[]
        //}
    //
    //}
}
//leetcode submit region end(Prohibit modification and deletion)

}