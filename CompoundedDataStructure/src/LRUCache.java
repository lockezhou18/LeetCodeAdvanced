import java.util.HashMap;
import java.util.List;

//hashMap + DoubleLinkedList
//hashMap key -> key, val -> ListNode
//ListNode stores key and val
public class LRUCache {
    class ListNode {
        int key;
        int val;

        ListNode prev;
        ListNode next;

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> cache;
    int size;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        cache = new HashMap<>();
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        ListNode valNode = cache.get(key);
        if (valNode == null)
            return -1;

        this.remove(valNode);
        this.addFirst(valNode);

        return valNode.val;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) { //update
            ListNode valNode = cache.get(key);
            valNode.val = value;
            remove(valNode);
            addFirst(valNode);
            return;
        }

        ListNode node = new ListNode(key ,value); //put new
        cache.put(key, node);
        addFirst(node);
        size++;

        if (size == capacity + 1) {  //check if need to be evicted
            cache.remove(removeLast().key);
            size--;
        }
    }

    private void addFirst(ListNode node) {
        node.next = head.next;
        head.next.prev = node;

        node.prev = head;
        head.next = node;
    }

    private void remove(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private ListNode removeLast() {
        if (size == 0)
            throw new RuntimeException("cache is empty");
        ListNode removed = tail.prev;
        remove(removed);
        return removed;
    }
}
