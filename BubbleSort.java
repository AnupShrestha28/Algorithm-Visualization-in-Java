public class BubbleSort extends SortAlgorithm
{

    public BubbleSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;
        boolean swapOccured;
        do {
            swapOccured = false;
            for(int i=0;i<N-1;i++) {
                if (array[i] > array[i + 1]) {
                    swap(i, i + 1);
                    swapOccured = true;

                    delegate.visualizeData(array);
                    if (!keepRunning) {
                        return;
                    }
                }
            }
        } while (swapOccured);



        tellDelegateSortIsComplete();
    }

    public void swap(int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
