public class SelectionSort extends SortAlgorithm
{

    public SelectionSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(i, min);
            }
            delegate.visualizeData(array);
            if (!keepRunning) {
                return;
            }
        }


        tellDelegateSortIsComplete();
    }
    public void swap(int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
