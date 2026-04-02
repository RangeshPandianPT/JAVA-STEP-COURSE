import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;
    int originalIndex;

    public Asset(String name, double returnRate, double volatility, int index) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
        this.originalIndex = index;
    }
}

public class PortfolioSorter {

    public static void mergeSort(List<Asset> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private static void merge(List<Asset> list, int left, int mid, int right) {
        List<Asset> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (compareMerge(list.get(i), list.get(j)) <= 0) {
                temp.add(list.get(i++));
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    private static int compareMerge(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(a.returnRate, b.returnRate);
        }
        return Integer.compare(a.originalIndex, b.originalIndex);
    }

    public static void quickSort(List<Asset> list, int low, int high) {
        if (high - low <= 10) {
            insertionSort(list, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOfThree(list, low, high);
            Collections.swap(list, pivotIndex, high);
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<Asset> list, int low, int high) {
        Asset pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compareQuick(list.get(j), pivot) < 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    private static int compareQuick(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate);
        }
        return Double.compare(a.volatility, b.volatility);
    }

    private static int medianOfThree(List<Asset> list, int low, int high) {
        int mid = (low + high) / 2;

        if (compareQuick(list.get(low), list.get(mid)) > 0) Collections.swap(list, low, mid);
        if (compareQuick(list.get(low), list.get(high)) > 0) Collections.swap(list, low, high);
        if (compareQuick(list.get(mid), list.get(high)) > 0) Collections.swap(list, mid, high);

        return mid;
    }

    private static void insertionSort(List<Asset> list, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = list.get(i);
            int j = i - 1;

            while (j >= low && compareQuick(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>();
        assets.add(new Asset("AAPL", 12, 0.3, 0));
        assets.add(new Asset("TSLA", 8, 0.5, 1));
        assets.add(new Asset("GOOG", 15, 0.2, 2));

        mergeSort(assets, 0, assets.size() - 1);
        quickSort(assets, 0, assets.size() - 1);
    }
}