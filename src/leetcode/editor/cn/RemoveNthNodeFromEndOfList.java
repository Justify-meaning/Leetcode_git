//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？
// 示例 1：
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
//
// 示例 2：
//输入：head = [1], n = 1
//输出：[]
//
// 示例 3：
//输入：head = [1,2], n = 1
//输出：[1]

// 提示：
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1417 👎 0

package leetcode.editor.cn;

import javax.xml.stream.events.EndDocument;

public class RemoveNthNodeFromEndOfList {
 public static void main(String[] args) {

     Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
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
    // 实现思想：设计双指针，末尾指针end先指向正序第n个结果，然后first、end同时开始向后遍历，设置初始节点
    // 当cur指针指向末尾时(移动N次），pre指针指向的是倒数第n个节点的前一个
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode root = new ListNode();
        root.next = head;
        //设置哑节点作用在于：对于空链表和只有一个节点的链表的特殊情况可以减少很多次判断
        ListNode pre = root;
        ListNode cur = root;
        int i = 0;
        while (cur.next != null){
            cur = cur.next;
            i++;
            //指向倒数第n个节点的前一个
            if (i > n) {
                pre = pre.next;
            }
        }
        //将倒数第n+1个节点和倒数第n-1个节点链接起来
        pre.next = pre.next.next;

        return root.next;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}