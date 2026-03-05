package DataStructureDesign;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 460 - LFU Cache.
 */
public class LFUCache {
    private static class Entry {
        private final int frequency;
        private final int value;

        private Entry(int frequency, int value) {
            this.frequency = frequency;
            this.value = value;
        }
    }

    private final Map<Integer, Entry> cache;
    private final Map<Integer, LinkedHashSet<Integer>> frequencies;
    private int minFrequency;
    private final int capacity;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.frequencies = new HashMap<>();
        this.minFrequency = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        Entry entry = cache.get(key);
        int frequency = entry.frequency;
        int value = entry.value;

        Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if (keys.isEmpty()) {
            frequencies.remove(frequency);
            if (minFrequency == frequency) {
                minFrequency++;
            }
        }

        cache.put(key, new Entry(frequency + 1, value));
        frequencies.putIfAbsent(frequency + 1, new LinkedHashSet<>());
        frequencies.get(frequency + 1).add(key);

        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (cache.containsKey(key)) {
            Entry entry = cache.get(key);
            cache.put(key, new Entry(entry.frequency, value));
            get(key);
            return;
        }

        if (cache.size() == capacity) {
            Set<Integer> keys = frequencies.get(minFrequency);
            int keyToEvict = keys.iterator().next();
            cache.remove(keyToEvict);
            keys.remove(keyToEvict);
            if (keys.isEmpty()) {
                frequencies.remove(minFrequency);
            }
        }

        minFrequency = 1;
        cache.put(key, new Entry(1, value));
        frequencies.putIfAbsent(1, new LinkedHashSet<>());
        frequencies.get(1).add(key);
    }
}
