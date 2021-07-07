package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-6 10:26
 * @Version 1.0
 */

import java.util.*;

/**
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说，
 * orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，
 * 其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 * 示例 2：
 * 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * 输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * 解释：
 * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
 * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
 *
 * 示例 3：
 * 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * 输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 */

public class lab1418 {
    public List<List<String>> displayTable(List<List<String>> orders) {
        //定义nameSet用于储存菜品名称，定义foodMap用于储存桌号和点餐数量，点餐数量也用一个hash表保存
        Set<String> nameSet = new HashSet<String>();
        Map<Integer, Map<String, Integer>> foodMap = new HashMap<Integer, Map<String, Integer>>();
        for (List<String> order : orders){
            //记录菜品名称
            nameSet.add(order.get(2));
            int id = Integer.parseInt(order.get(1));
            //找到桌号id，如果以及有记录菜品名称和数量的map就读取，没有就新建一个hashmap用于存储
            Map<String, Integer> map = foodMap.getOrDefault(id, new HashMap<String, Integer>());
            //读取到菜品数量+1加入到map中,如果之前没有记录，则赋值为0后+1
            map.put(order.get(2), map.getOrDefault(order.get(2),0) + 1);
            //foodMap添加桌号id和map(菜品名称，数量)
            foodMap.put(id, map);
        }
    //    提取菜品名称，并按字母顺序排列
        List<String> names = new ArrayList<String>();
        for (String name : nameSet){
            names.add(name);
        }
        Collections.sort(names);

    //    提取桌号，并按餐桌桌号升序排列
        List<Integer> ids = new ArrayList<>();
        for (int id : foodMap.keySet()){
            ids.add(id);
        }
        Collections.sort(ids);

    //    填写点菜展示表
        List<List<String>> table = new ArrayList<List<String>>();
        //表头记录菜名
        List<String> header = new ArrayList<String>();
        header.add("Table");
        for (String name : names){
            header.add(name);
        }
        //把菜名列表添加到展示表的第一行
        table.add(header);
        for (int i = 0; i < foodMap.size(); i++){
            int id = ids.get(i);
            //获取菜品名称和数量存入cnt集合中
            Map<String, Integer> cnt = foodMap.get(id);
            List<String> row = new ArrayList<String>();
        //    把获取到的桌号id填入row集合中
            row.add(Integer.toString(id));
            //遍历菜名
            for (int j = 0; j < nameSet.size(); j++){
                row.add(Integer.toString(cnt.getOrDefault(names.get(j),0)));
            }
            //将当前桌号的菜品和数量填入table集合中
            table.add(row);
        }
        return table;
    }

    public List<List<String>> displayTable2(List<List<String>> orders) {
        Map<String, Map<String, Integer>> map = new TreeMap<>(Comparator.comparing(Integer::valueOf));//table -> (food -> count)
        TreeSet<String> foods = new TreeSet<>();
        for (List<String> order : orders) {
            String customer = order.get(0), table = order.get(1), food = order.get(2);
            map.computeIfAbsent(table, k -> new HashMap<>()).merge(food, 1, Integer::sum);
            foods.add(food);
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(foods);
        ans.add(header);
        for (Map.Entry<String, Map<String, Integer>> entry : map.entrySet()) {
            List<String> row = new ArrayList<>();
            row.add(entry.getKey());
            for (String food : foods) {
                row.add(String.valueOf(entry.getValue().getOrDefault(food, 0)));
            }
            ans.add(row);
        }
        return ans;
    }
}