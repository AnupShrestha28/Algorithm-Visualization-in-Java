public class MergeSort extends SortAlgorithm {
    public MergeSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;
        int[] bArray = new int[N];//temporary array
        mergeSort(array, bArray, 0, N-1);
        tellDelegateSortIsComplete();
    }

    private void mergeSort(int[] array, int[] bArray, int leftHalf, int rightHalf){
        if(leftHalf >= rightHalf){
            return;
        }
        int midIndex = (leftHalf + rightHalf) / 2;
        delegate.visualizeData(array);
        if(!keepRunning){
            return;
        }
        mergeSort(array, bArray, leftHalf, midIndex);
        mergeSort(array, bArray, midIndex + 1, rightHalf);
        merge(array, bArray, midIndex, leftHalf, rightHalf);
    }

    public void merge(int[] array, int[] bArray, int mid, int leftHalf, int rightHalf){
        int i = leftHalf;
        int j = mid + 1;
        int k = leftHalf;

        while(i <= mid && j <= rightHalf){
            if (array[i] <= array[j]) {
                bArray[k] = array[i];
                i++;
            }
            else {
                bArray[k] = array[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            bArray[k] = array[i];
            i++;
            k++;
        }

        while (j <= rightHalf) {
            bArray[k] = array[j];
            j++;
            k++;
        }

        for (k = leftHalf; k <= rightHalf; k++) {
            array[k] = bArray[k];
        }
    }
}
