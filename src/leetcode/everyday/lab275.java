package leetcode.everyday;

/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）
 * 总共有 h 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
 *
 * 示例:
 * 输入: citations = [0,1,3,5,6]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 *      由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 *  
 * 说明:
 * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 *
 * 进阶：
 * 这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
 * 你可以优化你的算法到对数时间复杂度吗？
 */

/**
 * @Author Hcs
 * @Date 2021-7-12 10:17
 * @Version 1.0
 */
public class lab275 {
    public int hIndex(int[] citations) {
        //左指针，右指针二分查找 要保证引用次数超过论文 而目标是包括尽可能多的论文数
        int left = 0, right = citations.length - 1;
        //储存结果
        int res = 0;

        while(left <= right){
            int mid = (left + right) / 2;

            //引用次数小于论文数，要求：减少包括的论文数，增加引用次数，在右半部分数组进行查找
            if (citations[mid] < citations.length - mid){
                left = mid + 1;
            }
            else {  //因为要包括尽可能多的论文
                res = citations.length - mid;
                right = mid - 1;   //mid下标的数符合要求，应该保留作为右端点
            }
        }

        return res;
    }

    public int hIndex2(int[] citations) {
        int len = citations.length;
        int start = 0;
        int end = len - 1;
        int res = 0;

        while (start <= end) {
            int mid = (start + end) >> 1;
            int paperCnt = len - mid;
            // len - mid 代表从mid到len到论文数量，若想len - mid作为hIndex
            // 则要求从mid到len中最低引用的那篇论文的citations就大于等于len - mid（也就是citations[mid]>=paperCnt）。
            // 若满足这个条件，我们尝试能不能让hIndex更大些，也就是mid往左一些，那么我们就调整end = mid - 1
            // 若条件不满足，我们让mid向右面移动，这样paperCnt就少了，同时citations[mid]也大了，我们再看看从mid开始到len能不能作为hIndex
            if (paperCnt <= citations[mid]) {
                res = paperCnt;
                end = mid - 1;
            } else
                start = mid + 1;
        }
        return res;
    }
}