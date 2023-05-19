import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    public static void main(String[] args) {
        int[] dataSizes = { 1000, 10000, 50000, 100000, 500000, 1000000};

        // Random Data

        for (int size : dataSizes) {
            int[] arr = generateRandomArray(size);
            System.out.println("Random Data:");
            measureSortingTime(arr, "Merge Sort", SortingComparison::mergeSort);
            measureSortingTime(arr, "Quick Sort", SortingComparison::quickSort);
            measureSortingTime(arr, "Heap Sort", SortingComparison::heapSort);
            measureSortingTime(arr, "Selection Sort", SortingComparison::selectionSort);
            measureSortingTime(arr, "Bubble Sort", SortingComparison::bubbleSort);
            measureSortingTime(arr, "Insertion Sort", SortingComparison::insertionSort);
            measureSortingTime(arr, "Shell Sort", SortingComparison::shellSort);
            System.out.println();
        }

        // Sorted Data

        for (int size : dataSizes) {
            int[] arr = generateSortedArray(size);
            System.out.println("Sorted(Sirali) Data:");
            measureSortingTime(arr, "Merge Sort", SortingComparison::mergeSort);
            measureSortingTime(arr, "Quick Sort", SortingComparison::quickSort);
            measureSortingTime(arr, "Heap Sort", SortingComparison::heapSort);
            measureSortingTime(arr, "Selection Sort", SortingComparison::selectionSort);
            measureSortingTime(arr, "Bubble Sort", SortingComparison::bubbleSort);
            measureSortingTime(arr, "Insertion Sort", SortingComparison::insertionSort);
            measureSortingTime(arr, "Shell Sort", SortingComparison::shellSort);
            System.out.println();
        }

        // Reverse Sorted Data

        for (int size : dataSizes) {
            int[] arr = generateReverseSortedArray(size);
            System.out.println("Reverse Sorted(Ters Sirali) Data:");
            measureSortingTime(arr, "Merge Sort", SortingComparison::mergeSort);
            measureSortingTime(arr, "Quick Sort", SortingComparison::quickSort);
            measureSortingTime(arr, "Heap Sort", SortingComparison::heapSort);
            measureSortingTime(arr, "Selection Sort", SortingComparison::selectionSort);
            measureSortingTime(arr, "Bubble Sort", SortingComparison::bubbleSort);
            measureSortingTime(arr, "Insertion Sort", SortingComparison::insertionSort);
            measureSortingTime(arr, "Shell Sort", SortingComparison::shellSort);
            System.out.println();
        }
    }

    // Helper method to generate a random array of given size
    public static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    // Helper method to generate a sorted array of given size
    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // Helper method to generate a reverse sorted array of given size
    public static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    // Helper method to measure the sorting time of an algorithm
    public static void measureSortingTime(int[] arr, String algorithmName, SortingAlgorithm sortingAlgorithm) {
        int[] arrCopy = Arrays.copyOf(arr, arr.length);
        long startTime = System.currentTimeMillis();
        sortingAlgorithm.sort(arrCopy);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println(algorithmName + " - " + arr.length + " - " + elapsedTime + " ms");
    }

    // Sorting Algorithms

    //Merge Sort
    public static void mergeSort(int[] arr) {
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSortHelper(arr, low, mid);
            mergeSortHelper(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int leftLength = mid - low + 1;
        int rightLength = high - mid;

        int[] leftArray = new int[leftLength];
        int[] rightArray = new int[rightLength];

        for (int i = 0; i < leftLength; ++i) {
            leftArray[i] = arr[low + i];
        }

        for (int j = 0; j < rightLength; ++j) {
            rightArray[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = low;
        while (i < leftLength && j < rightLength) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightLength) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }

    //Quick Sort
    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        while (low < high) {
            int pivotIndex = partition(arr, low, high);

            // Tail recursion on the smaller partition
            if (pivotIndex - low < high - pivotIndex) {
                quickSortHelper(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSortHelper(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    //Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    //Selection Sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    //Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;
        }
    }

    //Shell Sort
    public static void shellSort(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    @FunctionalInterface
    interface SortingAlgorithm {
        void sort(int[] arr);
    }
}

