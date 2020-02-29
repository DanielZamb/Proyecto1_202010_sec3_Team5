package model.logic;

public class Shell {
    public static void sort(Comparable[] list) {
        int N = list.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) { // el arreglo es grande o su size >= 3
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h &&  less(list[j], list[j-h]); j -= h) {
                    exch(list, j, j - h);
                }
            }
            h = h/3;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }
}
