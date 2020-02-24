import java.util.Scanner;

public class Main {
    static int[] mergeArray = new int[Integer.MAX_VALUE];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] Array = new int[N];

        for(int i=0; i<N; i++) {
            Array[i] = scanner.nextInt();
        }

        mergeSort(Array,0,N-1);

        for(int i=0; i<N; i++) {
            System.out.println(Array[i]);
        }

    }

    public static void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void bubbleSort(int[] array) {
        for(int i=array.length-1; i>=0; i--) {
            for(int j=0; j < i; j++) {
                if(array[j] > array[j+1]) {
                    swap(j,j+1,array);
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        int max = 0;
        int maxIndex = 0;

        for(int j=array.length-1; j>=0; j--) {
            for (int i=0; i<=j; i++) {
                if(array[i] > max) {
                    maxIndex = i;
                    max = array[i];
                }
            }
            swap(maxIndex,j,array);

            max=0;
            maxIndex=0;
        }

    }

    public static void insertionSort(int[] array) {

        for(int i=1; i<array.length; i++) {
            for(int j=0; j<i; j++) {
                if(array[i] < array[j]) {
                    int temp = array[i];
                    for(int k=j; k<i; k++) {
                        array[k+1] = array[k];
                    }
                    array[j] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] array,int p, int r) {
        if(p<r) {
            int q = (p+r) / 2;
            mergeSort(array, p ,q);
            mergeSort(array, q+1, r);
            merge(array, p, q, r);
        }
    }

    public static void merge(int[] array, int p, int q, int r) {
        int pIndex = p;
        int qIndex = q+1;

        int mergeArrayIndex = p;

        while(pIndex <= q && qIndex <= r) {
            if(array[pIndex] < array[qIndex]) {
                mergeArray[mergeArrayIndex++] = array[pIndex++];
            }
            else {
                mergeArray[mergeArrayIndex++] = array[qIndex++];
            }
        }

        if(pIndex > q) {
            for(int i=qIndex; i<=r; i++) {
                mergeArray[mergeArrayIndex++] = array[i];
            }
        }
        else {
            for(int i=pIndex; i<=q; i++) {
                mergeArray[mergeArrayIndex++] = array[i];
            }
        }

        for(int i=p; i<=r; i++) {
            array[i] = mergeArray[i];
        }
    }

}
