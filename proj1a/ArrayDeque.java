public class ArrayDeque<T> {
    private int size;
    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int FRACTOR = 2;

    private int minusOne(int index) {
        return index - 1;
    }

    private int plusOne(int index) {
        return index + 1;
    }

    private int smaller(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    private int rebuild(T[] newArray) {
        int headLength = plusOne(nextLast);
        int tailLength = item.length - nextFirst;
        System.arraycopy(item, 0, newArray, 0, headLength);
        System.arraycopy(item, nextFirst, newArray, headLength + newArray.length - size,
                tailLength);
        return tailLength;
    }

    private void resizeFirst(int newSize) {
        T[] a = (T[]) new Object[newSize];
        nextLast = minusOne(nextLast);
        if (nextLast < 0) {
            System.arraycopy(item, 0, a, 0, size);
            nextLast = item.length;
            item = a;
        } else {
            /* split original array into head part(start from 0 to nextLast) and tail part
            start from nextFirst to the end of array*/
            int tailLength = rebuild(a);
            nextFirst = newSize - tailLength;
            item = a;
            nextLast = plusOne(nextLast);
        }
    }

    private void resizeLast(int newSize) {
        T[] a = (T[]) new Object[newSize];
        nextFirst = plusOne(nextFirst);
        if (nextFirst == 8) {
            System.arraycopy(item, 0, a, 0, size);
            item = a;
            nextFirst = item.length - 1;
        } else {
            int tailLength = rebuild(a);
            nextFirst = minusOne(newSize) - tailLength;
            item = a;
        }
    }

    private void shrinkArray(int shrinkSize) {
        int newSize = item.length - shrinkSize;
        T[] a = (T[]) new Object[newSize];
        if (item[minusOne(item.length)] == null) {
            int frontEmpty = plusOne(nextFirst);
            int endEmpty = item.length - nextLast;
            if (endEmpty >= frontEmpty) {
                System.arraycopy(item, 0, a, 0, nextLast);
            } else {
                System.arraycopy(item, 0, a, shrinkSize, item.length - plusOne(nextFirst));
            }
            item = a;
        } else {
            int headLength = nextLast;
            int tailLength = item.length - plusOne(nextFirst);
            System.arraycopy(item, 0, a, 0, headLength);
            System.arraycopy(item, item.length - tailLength,
                    a, newSize - tailLength, tailLength);
            item = a;
        }
    }


    public ArrayDeque() {
        size = 0;
        item = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
    }

    public void addFirst(T x) {
        item[nextFirst] = x;
        size = plusOne(size);
        if (size == item.length) {
            resizeFirst(size * FRACTOR);
        }
        if (minusOne(nextFirst) < 0) {
            nextFirst = minusOne(item.length);
        } else {
            nextFirst = minusOne(nextFirst);
        }

    }

    public void addLast(T x) {
        item[nextLast] = x;
        size = plusOne(size);
        if (size == item.length) {
            resizeLast(size * FRACTOR);
        }
        if (plusOne(nextLast) > minusOne(item.length)) {
            nextLast = 0;
        } else {
            nextLast = plusOne(nextLast);
        }
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        int index = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            if (index == item.length) {
                index = 0;
            }
            System.out.print(item[index]);
            System.out.print(" ");
            index += 1;
        }
    }
}