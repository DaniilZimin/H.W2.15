package com.company;

import com.company.exception.OutOfRangeException;
import com.company.exception.RemovingNonExistingElementException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final int DEFAULT_CAPACITY = 10;

    private int[] ints;
    private int size = 0;

    public IntegerListImpl() {
        this.ints = new int[DEFAULT_CAPACITY];
    }

    public IntegerListImpl(int capacity) {
        this.ints = new int[capacity];
    }

    @Override
    public int add(int item) {
        increaseArray();
        ints[size++] = item;
        return item;
    }

    @Override
    public int add(int index, int item) {
        increaseArray();
        if (index < 0 || index > size) {
            throw new OutOfRangeException("Выход за пределы фактического количества элементов");
        }

        System.arraycopy(ints, index, ints, index + 1, size - index);
        ints[index] = item;
        size++;
        return item;
    }

    @Override
    public int set(int index, int item) {
        checkIndex(index);
        ints[index] = item;
        return item;
    }

    @Override
    public int remove(int item) {
        for (int i = 0; i < size; i++) {
            if (ints[i] == item) {
                return removeInd(i);
            }
        }
        throw new RemovingNonExistingElementException("Удаление не существующего элемента");
    }

    @Override
    public int removeInd(int index) {
        checkIndex(index);
        int s = ints[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(ints, index + 1, ints, index, size - 1 - index);
        }
        size--;
        return s;
    }

    @Override
    public boolean contains(int item) {
        int[] i = Arrays.copyOf(ints, size);
        quickSort(i, 0, i.length - 1);
        return containsBin(i, item);
    }

    @Override
    public int indexOf(int item) {
        for (int i = 0; i < size; i++) {
            if (ints[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int item) {
        for (int i = size - 1; i >= 0; i--) {
            if (ints[i] == item) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int get(int index) {
        checkIndex(index);
        return ints[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (this == otherList) {
            return true;
        }
        if (otherList == null) {
            return false;
        }
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (get(i) != otherList.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        ints = new int[DEFAULT_CAPACITY];
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(ints, size);
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private boolean containsBin(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("Выход за пределы фактического количества элементов");
        }
    }

    private void increaseArray() {
        if (size >= ints.length) {
            ints = Arrays.copyOf(ints, ints.length * 2);
        }
    }
}
