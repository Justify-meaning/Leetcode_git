//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。
// 示例 1：
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
//
// 示例 2：
// 输入：lists = []
//输出：[]
//
// 示例 3：
// 输入：lists = [[]]
//输出：[]
//
// 提示：
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 
// 👍 1361 👎 0

package leetcode.editor.cn;



import java.util.PriorityQueue;

public class MergeKSortedLists {
 public static void main(String[] args) {

     Solution solution = new MergeKSortedLists().new Solution();
 }
 //leetcode submit region begin(Prohibit modification and deletion)

    //Definition for singly-linked list.
  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class Solution {
    // 重写一个对象用于优先级队列
    class Status implements Comparable<Status>{
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr){
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2){
            return this.val - status2.val;
        }
    }
    //优先级队列
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists(ListNode[] lists) {
        //遍历多个链表的头结点，存入优先级队列，其中链表最小的值排在最前面
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
            //新建一个哑节点和用于输出结果的头结点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()){
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null){
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}