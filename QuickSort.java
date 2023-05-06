public class QuickSort extends SortAlgorithm
{

    public QuickSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;
        sort(0,N-1);
        tellDelegateSortIsComplete();
    }

    public void sort(int lo, int hi){
        delegate.visualizeData(array);
        if (!keepRunning)
            return;
        if (lo < hi)
        {
            int p = partition(lo,hi);
            sort(lo,p-1);
            sort(p+1,hi);
        }
    }

    public int partition(int lo, int hi){
        int pivot = array[hi];
        int i = lo;
        for (int j = lo; j < hi; j++)
        {
            if (array[j] < pivot){
                swap(i,j);
                i++;
            }
        }
        swap(i,hi);
        return i;
    }

    public void swap(int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
