package org.example;

public class MyArrayList<T> {
    T[] arr = null;
    int currentIndex;
    int capacity;

    public MyArrayList() {
        this.currentIndex = 0;
        this.capacity = 1;
        this.arr = (T[]) new Object[capacity];
    }

    public void increaseSize() {
        capacity *= 2;
        Object[] tmpArr = new Object[capacity];
        copyContent(arr, (T[]) tmpArr);

        arr = (T[]) new Object[capacity];
        copyContent((T[]) tmpArr, arr);
    }

    public void copyContent(T[] fromArr, T[] toArr) {
        for (int i = 0; i < fromArr.length; i++) {
            toArr[i] = fromArr[i];
        }
    }

    public boolean add(T t) {
        if (this.capacity == this.currentIndex) {
            increaseSize();
        }

        arr[currentIndex++] = t;
        return true;
    }

    public int size() {
        return this.currentIndex;
    }

    public T get(int index) {
        return arr[index];
    }

    public T remove(int index) {
        T result = arr[index];
        arr[index] = null;

        currentIndex--;

        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }

        return result;
    }

    public boolean contains(T t) {
        for (T tmp : arr) {
            if (tmp == t) {
                return true;
            }
        }

        return false;
    }

    public int indexOf(T t) {
        for (int i = 0; i < currentIndex; i++) {
            if (arr[i] == t) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        arr = (T[]) new Object[1];
        currentIndex = 0;
    }

    public boolean isEmpty() {

        if (currentIndex == 0) {
            return true;
        }

        return false;
    }
}
