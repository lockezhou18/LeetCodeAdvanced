public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        //c.c
        if (head == null)
            return null;
        //find size of the list, and set the last elements next to the head
        ListNode cur = head;
        int n = 1;

        while (cur.next != null) {
            cur = cur.next;
            n++;
        }

        cur.next = head;
        //k = n % k
        k = n % k;
        //move n - k + 1 times from the given head position, next should be the new head. so set the node's next to null
        int move = n - k + 1;
        cur = head;
        while (move-- > 0) {
           cur = cur.next;
        }

        ListNode newHead = cur.next;
        cur.next = null;

        return newHead;
    }
}
