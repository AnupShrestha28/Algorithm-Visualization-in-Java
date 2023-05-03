public class InsertionSort extends SortAlgorithm
{

    public InsertionSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;

        for (int i = 1; i < N; i++)
        {
            for (int j = i; j>0 && array[j-1] > array[j]; j-- ){
                swap(j, j-1);
                delegate.visualizeData(array);
                if (!keepRunning){
                    return;
                }
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