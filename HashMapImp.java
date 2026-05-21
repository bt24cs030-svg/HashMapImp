import java.util.*;

public class HashMapImp {

    static class HashMap<K, V> {

        private class Node {
            K key;
            V value;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int N;
        private LinkedList<Node>[] buckets;

        @SuppressWarnings("unchecked")
        public HashMap() {
            N = 4;
            buckets = new LinkedList[N];

            for (int i = 0; i < N; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction(K key) {
            int hc = key.hashCode();
            return Math.abs(hc) % N;
        }

        public void put(K key, V value) {
            int bi = hashFunction(key);

            for (Node node : buckets[bi]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }

            buckets[bi].add(new Node(key, value));
        }

        public V get(K key) {
            int bi = hashFunction(key);

            for (Node node : buckets[bi]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }

            return null;
        }

        public boolean containsKey(K key) {
            return get(key) != null;
        }

        public void remove(K key) {
            int bi = hashFunction(key);

            Iterator<Node> it = buckets[bi].iterator();

            while (it.hasNext()) {
                Node node = it.next();

                if (node.key.equals(key)) {
                    it.remove();
                    return;
                }
            }
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();

            for (LinkedList<Node> bucket : buckets) {
                for (Node node : bucket) {
                    keys.add(node.key);
                }
            }

            return keys;
        }

        public int size() {
            int count = 0;

            for (LinkedList<Node> bucket : buckets) {
                count += bucket.size();
            }

            return count;
        }

        public void display() {
            for (LinkedList<Node> bucket : buckets) {
                for (Node node : bucket) {
                    System.out.println(node.key + " -> " + node.value);
                }
            }
        }
    }

    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();

        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        System.out.println(map.get("Two"));

        map.display();
    }
}