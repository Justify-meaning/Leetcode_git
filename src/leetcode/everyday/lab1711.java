package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-7 10:19
 * @Version 1.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 *
 * 示例 1：
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 *
 * 示例 2：
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 */
public class lab1711 {


    public int countPairs(int[] deliciousness) {
        //Map<Integer, Integer> map = new HashMap<>();
        //int mod = 1000000007;
        //Long res = 0L;
        //int length = deliciousness.length;
        //for (int num : deliciousness) {
        //
        //    // 记录二的幂
        //    int powerOfTwo = 1;
        //
        //    // 遍历查看num的与那些数可以组成2的幂
        //    for (int i = 0; i <= 21; i++) {
        //        if (powerOfTwo >= num && map.containsKey(powerOfTwo - num)) {
        //            res += map.get(powerOfTwo - num);   //得到可以组成2的幂的数，并统计出现的次数，即组合方法
        //        }
        //        powerOfTwo *= 2;
        //    }
        //    // 给当前的数+1，没有则新建存进map表
        //    map.put(num, map.getOrDefault(num, 0) + 1);
        //}
        //return (int)(res % 1000000007);
        // 获取deliciousness中的极大值与极小值
        int min = deliciousness[0];
        int max = deliciousness[0];
        for (int num : deliciousness) {
            if (num < min){
                min = num;
            }
            else if (num > max){
                max = num;
            }
        }
        // 不使用HashMap去重而是使用数组存储（天知道为什么内存没有超限）
        int[] map = new int[max - min + 1];
        long res = 0;
        for (int num : deliciousness) {
            // s用于描述2的幂次，每次循环都*2
            for(int s = 1; ; s <<= 1) {
                int x = s - num;
                // 对应的补数小于数组中最小的数
                if(x < min)
                    continue;
                // 对应的补数大于数组中最大的数
                if(x > max)
                    break;
                // 给结果加上补数出现的次数
                res += map[x - min];
            }
            // 添加当前数进数组
            map[num - min]++;
        }
        return (int) (res % 1000000007);
    }


    //暴力解法超时
    public int countPairs2(int[] deliciousness) {
        //定义美味组合结果
        int deliciousCombine = 0;
        // 双重循环计算所有美味程度组合
        for (int i = 0; i < deliciousness.length; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                int tmp = deliciousness[i] + deliciousness[j];
                if ((tmp & tmp - 1) == 0) {  //是2的幂
                    deliciousCombine++;
                }
            }
        }
        return deliciousCombine % (10^9+7);
    }
}