public class QuickSort extends  SortAlgorithm{

    public QuickSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;
        sort(0,N-1);
        tellDelegateSortIsComplete();
    }
    public void sort(int lowIndex, int highIndex){
        delegate.visualizeData(array);
        if(!keepRunning){
            return;
        }
        if(lowIndex < highIndex){
            int p = partition(lowIndex,highIndex);
            sort(lowIndex,p-1);
            sort(p+1,highIndex);
        }
    }
    public int partition(int lowIndex, int highIndex){
        int pivot = array[highIndex];
        int i = lowIndex;
        for(int j = lowIndex; j < highIndex; j++){
            if(array[j] < pivot){
                swap(i,j);
                i++;
            }
        }
        swap(i,highIndex);
        return i;
    }
    private void swap(int index1 , int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}


