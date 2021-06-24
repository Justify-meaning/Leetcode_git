//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

// 示例 1：
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
//
// 示例 2：
//输入：l1 = [], l2 = []
//输出：[]
//
// 示例 3：
//输入：l1 = [], l2 = [0]
//输出：[0]

// 提示：
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1764 👎 0

package leetcode.editor.cn;
public class MergeTwoSortedLists {
 public static void main(String[] args) {

     Solution solution = new MergeTwoSortedLists().new Solution();
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //定义最后用于输出的节点和过程节点
        ListNode fin = new ListNode();
        ListNode tmp = new ListNode();
        //设置哑节点可以减少判空次数
        //先判空
       if (l1 == null){
            return l2;
       }else if (l2 == null){
            return l1;
       }

        //找到合适的头结点（l1,l2之间小的那个）
        if (l1.val <= l2.val){
            fin = l1;
            tmp = l1;
            l1 = l1.next;
        }else {
            fin = l2;
            tmp = l2;
            l2 = l2.next;
        }
        //l1和l2不为空时，找到两者之间较小的节点进行链接
        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                tmp.next = l1;
                l1 = l1.next;
                tmp = tmp.next;
            }else {
                tmp.next = l2;
                l2 = l2.next;
                tmp = tmp.next;
            }
        }
        //其中一个为空后，tmp节点可以直接链接另一个链表
        if (l1 == null){
            tmp.next = l2;
        }else {
            tmp.next = l1;
        }
        return fin;
    }

    //public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    //    ListNode prehead = new ListNode(-1);
    //
    //    ListNode prev = prehead;
    //    while (l1 != null && l2 != null) {
    //        if (l1.val <= l2.val) {
    //            prev.next = l1;
    //            l1 = l1.next;
    //        } else {
    //            prev.next = l2;
    //            l2 = l2.next;
    //        }
    //        prev = prev.next;
    //    }
    //
    //    // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
    //    prev.next = l1 == null ? l2 : l1;
    //
    //    return prehead.next;
    //}
}
//leetcode submit region end(Prohibit modification and deletion)

}