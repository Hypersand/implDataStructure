package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyHashMap<K, V> {

    private K[] keyArr;
    private V[] valueArr;

    private int capacity;

    private int index;

    public MyHashMap() {
        index = 0;
        capacity = 1;
        keyArr = (K[]) new Object[capacity];
        valueArr = (V[]) new Object[capacity];
        Map<String, Integer> map = new HashMap();
    }

    public V put(K key, V value) {

        //이미 존재하는 키
        int tmpIndex = findIndex(key);
        if (tmpIndex != -1) {
            valueArr[tmpIndex] = value;
            return value;
        }


        if (index == capacity) {
            increaseSize();
        }

        keyArr[index] = key;
        valueArr[index] = value;
        index++;

        return value;
    }

    private int findIndex(K key) {
        for (int i = 0; i< keyArr.length; i++) {
            if (keyArr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private void increaseSize() {
        capacity *=2;
        Object[] tmpKeyArr = new Object[capacity];
        copyArr(keyArr, tmpKeyArr);
        keyArr = (K[]) new Object[capacity];
        copyArr(tmpKeyArr, keyArr);

        Object[] tmpValueArr = new Object[capacity];
        copyArr(valueArr, tmpValueArr);
        valueArr = (V[]) new Object[capacity];
        copyArr(tmpValueArr, valueArr);
    }

    private void copyArr(Object[] fromArr, Object[] toArr) {
        for (int i = 0; i < fromArr.length; i++) {
            toArr[i] = fromArr[i];
        }
    }

    public int size() {
        return index;
    }

    public V get(K key) {
        int tmpIndex = findIndex(key);

        return valueArr[tmpIndex];
    }

    public V remove(K key) {
        int tmpIndex = findIndex(key);

        if (tmpIndex == -1) {
            return null;
        }

        V value = valueArr[tmpIndex];

        for (int i = tmpIndex; i < index - 1; i++) {
            keyArr[i] = keyArr[i + 1];
            valueArr[i] = valueArr[i + 1];
        }
        this.index--;

        return value;
    }

    public boolean containsKey(K key) {
        for (K tmpKey : keyArr) {
            if (tmpKey == key) {
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        for (V tmpValue : valueArr) {
            if (tmpValue == value) {
                return true;
            }
        }

        return false;
    }

    public void clear() {
        index = 0;
        Arrays.fill(keyArr, null);
        Arrays.fill(valueArr, null);
    }

    public boolean isEmpty() {
        if (index == 0) {
            return true;
        }

        return false;
    }
}
