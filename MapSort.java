package com.reacher.test.sort;

import java.util.*;

/**
 * 类说明
 *
 * @author wanggw
 * @date 2017/2/23
 */
public class MapSort {

    public static <K, V> Map<K, V> sortByKey(Map<K, V> map, Compare<K> key) {
        if(null == map || 0 == map.size()) {
            return null;
        }
        return sort(map, new MapEntryCompareByKey(key));
    }

    public static <K, V> Map<K, V> sortByValue(Map<K, V> map, Compare<V> value) {
        if(null == map || 0 == map.size()) {
            return null;
        }
        return sort(map, new MapEntryCompareByValue(value));
    }

    private static <K, V> Map<K, V> sort(Map<K, V> map, Compare<MapEntry> compare) {
        Iterator<Map.Entry<K, V>> entries = map.entrySet().iterator();
        List<MapEntry> temps = new ArrayList<>();
        while(entries.hasNext()) {
            Map.Entry<K, V> entry = entries.next();
            MapEntry temp = new MapEntry(entry.getKey(), entry.getValue());
            temps.add(temp);
        }
        temps = QuickSort.sort(temps, compare);
        if(null == temps || 0 == temps.size()) {
            return null;
        }
        Map<K, V> linkedMap = new LinkedHashMap<>();
        for(MapEntry<K, V> temp: temps) {
            linkedMap.put(temp.getKey(), temp.getValue());
        }
        return linkedMap;
    }

    private static class MapEntry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    private static class MapEntryCompareByKey<K> implements Compare<MapEntry> {

        private Compare<K> key;

        public MapEntryCompareByKey(Compare<K> key) {
            this.key = key;
        }

        @Override
        public boolean compare(MapEntry value1, MapEntry value2) {
            return this.key.compare((K)value1.getKey(), (K)value2.getKey());
        }

    }

    private static class MapEntryCompareByValue<V> implements Compare<MapEntry> {

        private Compare<V> value;

        public MapEntryCompareByValue(Compare<V> value) {
            this.value = value;
        }

        @Override
        public boolean compare(MapEntry value1, MapEntry value2) {
            return this.value.compare((V)value1.getValue(), (V)value2.getValue());
        }
    }

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(8, "reacher");
        map.put(4, "kobe");
        map.put(7, "kiro");

        map = MapSort.sortByKey(map, new Compare<Integer>() {
            @Override
            public boolean compare(Integer value1, Integer value2) {
                return value1 - value2 > 0;
            }
        });

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Integer, String> entity = iterator.next();
            System.out.println(String.format("key: %d   value: %s", entity.getKey(), entity.getValue()));
        }

        map = MapSort.sortByValue(map, new Compare<String>() {
            @Override
            public boolean compare(String value1, String value2) {
                return value1.compareTo(value2) > 0;
            }
        });

        iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Integer, String> entity = iterator.next();
            System.out.println(String.format("key: %d   value: %s", entity.getKey(), entity.getValue()));
        }
    }
}
