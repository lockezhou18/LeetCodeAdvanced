import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Repeatable read, kill the new transaction that attempting to write the resource. - remove transactions and writes
public class KVStore {
    Map<Integer, Integer> dataMap = new HashMap<>();
    Map<Integer, Map<Integer, Integer>> transactionMap = new HashMap<>(); //key - txId, value - Operations and current val
    Set<Integer> modifyResources = new HashSet<>();
    Map<Integer, Map<Integer, Integer>> writeMap = new HashMap<>(); //key -> transactionId, val -> put operations and new val
    private int id;
    public int begin() {//TODO: Integrate ID generator
        transactionMap.put(id, new HashMap<>());
        writeMap.put(id, new HashMap<>());
        return id++;
    }

    public void commit(int txid) { //only write keys that are updated by the transaction to the data source
        if (!transactionMap.containsKey(txid)) {
            System.out.println("transaction does not exist");
            return;
        }
        Map<Integer, Integer> writes = writeMap.get(txid);
        for (Integer key : writes.keySet()) {
            dataMap.put(key, writes.get(key));
            modifyResources.remove(key);
        }
        removeTransaction(txid);
    }

    public int get(int txid, int key) {
        if (!transactionMap.containsKey(txid)) {
            System.out.println("transaction does not exist");
            return -1;
        }
        if (transactionMap.get(txid).containsKey(key)) {
            return transactionMap.get(txid).get(key);
        }

        int val = dataMap.getOrDefault(key, -1);
        transactionMap.get(txid).put(key, val); //read to current transaction
        return val;
    }

    private void removeTransaction(int txid) {
        writeMap.remove(txid);
        transactionMap.remove(txid);
    }

    public void put(int txid, int key, int val) {
        if (modifyResources.contains(key)) { //TODO: Customermize Exception and throw Excption if conflicts occurred
            removeTransaction(txid);
            return;
            //throw new RuntimeException("key:" + key + " is modifying.");
        }

        modifyResources.add(key);
        writeMap.get(txid).put(key, val);
        transactionMap.get(txid).put(key, val);
    }
}
