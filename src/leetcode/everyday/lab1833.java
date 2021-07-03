package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-2 18:38
 * @Version 1.0
 */

import java.util.*;

/**
 * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
 * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。
 * Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
 * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
 * 注意：Tony 可以按任意顺序购买雪糕。
 *
 * 示例 1：
 * 输入：costs = [1,3,2,4,1], coins = 7
 * 输出：4
 * 解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
 *
 * 示例 2：
 * 输入：costs = [10,6,8,7,7,8], coins = 5
 * 输出：0
 * 解释：Tony 没有足够的钱买任何一支雪糕。
 *
 * 示例 3：
 * 输入：costs = [1,6,3,1,2,5], coins = 20
 * 输出：6
 * 解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
 *
 */
public class lab1833 {
    //map，这种方法不可行，因为hashmap不会根据key进行排序，如果需要key进行排序，要实现comparator接口,或者用treemap
    public int maxIceCream(int[] costs, int coins) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxIcecream = 0;
        for (int i = 0; i < costs.length; i++) {
            //  通过key判断该价格的雪糕是否已经出现过一次，如果是的话，读出value值并+1
            if (map.containsKey(costs[i])) {
                int value = map.get(costs[i]);
                map.put(costs[i], value + 1);
            }
            //  如果判断条件不生效说明雪糕价格是第一次加入map中
            else {
                map.put(costs[i], 1);
            }
        }

        //将map转换为list,进行排序,因为hashmap是无序集合类
        // 使用Collections工具类中的排序方法
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        //list集合根据key从小至大排列，因此可以通过遍历的方式从价格最便宜的雪糕开始遍历
        for (int i = 0; i< list.size(); i++) {
            int num = list.get(i).getKey();
            int count = list.get(i).getValue();
            //从价格最便宜的雪糕开始遍历，如果能支付这个价格的所有雪糕，更新剩余金钱coins和能够购买的雪糕数目maxIcecream
            if (coins - num * count > 0) {
                coins -= num * count;
                maxIcecream += count;
            } else {
                maxIcecream += coins / num;   //不足以支付这个价格的所有雪糕时，通过剩余金钱除价格得到商来计算能购买几个
                break;
            }
        }
        return maxIcecream;
    }
    //排序后遍历
    public int maxIceCream2(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        int n = costs.length;
        for (int i = 0; i < n; i++) {
            int cost = costs[i];
            if (coins >= cost) {
                coins -= cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }
    //  贪心算法
    public int maxIceCream3(int[] costs, int coins) {
        int[] freq = new int[100001];
        for (int cost : costs) {
            freq[cost]++;
        }
        int count = 0;
        for (int i = 1; i <= 100000; i++) {
            if (coins >= i) {
                int curCount = Math.min(freq[i], coins / i);
                count += curCount;
                coins -= i * curCount;
            } else {
                break;
            }
        }
        return count;
    }
}