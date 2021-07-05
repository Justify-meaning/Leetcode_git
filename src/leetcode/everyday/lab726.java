package leetcode.everyday;

/**
 * @Author Hcs
 * @Date 2021-7-5 10:46
 * @Version 1.0
 */

import java.util.*;

/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 *
 * 示例 1:
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 *
 * 示例 2:
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 *
 * 示例 3:
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 */
public class lab726 {
    /**
     * 在处理入参 s 的过程中，始终维护着一个哈希表 map，map 中 实时维护 着某个「原子」对应的实际「数值」（即存储格式为 {H:2,S:1}）；
     * 由于相同原子可以出在 s 的不同位置中，为了某个「数值」对「原子」的累乘效果被重复应用，
     * 我们这里应用一个”小技巧“：为每个「原子」增加一个”编号后缀“。即实际存储时为 {H_1:2, S_2:1, H_3:1}。
     *
     * 根据当前处理到的字符分情况讨论：
     * 符号：直接入栈；
     * 原子：继续往后取，直到取得完整的原子名称，将完整原子名称入栈，同时在 map 中计数加 11；
     * 数值：继续往后取，直到取得完整的数值并解析，然后根据栈顶元素是否为 ) 符号，决定该数值应用给哪些原子：
     * 如果栈顶元素不为 )，说明该数值只能应用给栈顶的原子
     * 如果栈顶元素是 )，说明当前数值可以应用给「连续一段」的原子中
     * 对 map 的原子做 “合并” 操作：{H_1:2, S_2:1, H_3:1} => {H:3, S:1} ；
     *
     * 使用「优先队列（堆）」实现字典序排序，并构造答案。
     */

    class Node {
        String s; int v;
        Node (String _s, int _v) {
            s = _s; v = _v;
        }
    }
    public String countOfAtoms(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        //创建一个map和双端队列
        Map<String, Integer> map = new HashMap<>();
        Deque<String> d = new ArrayDeque<>();
        int idx = 0;
        for (int i = 0; i < n; ) {
            char c = cs[i];
            if (c == '(' || c == ')') {
                //从队列尾端插入数据
                d.addLast(String.valueOf(c));
                i++;
            } else {
                if (Character.isDigit(c)) {
                    // 获取完整的数字，并解析出对应的数值 只将数字存入队列中
                    int j = i;
                    while (j < n && Character.isDigit(cs[j])) j++;
                    String numStr = s.substring(i, j);
                    i = j;
                    int cnt = Integer.parseInt(String.valueOf(numStr));

                    // 如果栈顶元素是 )，说明当前数值可以应用给「连续一段」的原子中
                    if (!d.isEmpty() && d.peekLast().equals(")")) {
                        List<String> tmp = new ArrayList<>();

                        d.pollLast(); // pop ) 移除队列中最后一个元素
                        //直到找到左括号之前，说明括号内的原子对应的个数
                        while (!d.isEmpty() && !d.peekLast().equals("(")) {
                            String cur = d.pollLast();
                            //通过乘位数来得到数字的大小
                            map.put(cur, map.getOrDefault(cur, 1) * cnt);
                            tmp.add(cur);
                        }
                        d.pollLast(); // pop ( 弹出左括号

                        for (int k = tmp.size() - 1; k >= 0; k--) {
                            d.addLast(tmp.get(k));
                        }

                        // 如果栈顶元素不是 )，说明当前数值只能应用给栈顶的原子
                    } else {
                        String cur = d.pollLast();
                        map.put(cur, map.getOrDefault(cur, 1) * cnt);
                        d.addLast(cur);
                    }
                } else {
                    // 获取完整的原子名
                    int j = i + 1;
                    while (j < n && Character.isLowerCase(cs[j])) j++;
                    String cur = s.substring(i, j) + "_" + String.valueOf(idx++);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                    i = j;
                    d.addLast(cur);
                }
            }
        }

        // 将不同编号的相同原子进行合并
        Map<String, Node> mm = new HashMap<>();
        for (String key : map.keySet()) {
            String atom = key.split("_")[0];
            int cnt = map.get(key);
            Node node = null;
            if (mm.containsKey(atom)) {
                node = mm.get(atom);
            } else {
                node = new Node(atom, 0);
            }
            node.v += cnt;
            mm.put(atom, node);
        }

        // 使用优先队列（堆）对 Node 进行字典序排序，并构造答案
        PriorityQueue<Node> q = new PriorityQueue<Node>((a, b)->a.s.compareTo(b.s));
        for (String atom : mm.keySet()) q.add(mm.get(atom));

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Node poll = q.poll();
            sb.append(poll.s);
            if (poll.v > 1) sb.append(poll.v);
        }

        return sb.toString();
    }
}