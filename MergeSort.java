public class MergeSort extends SortAlgorithm
{

    public MergeSort(AlgorithmDelegate del, int[] array) {
        super(del, array);
    }

    @Override
    public void run() {
        int N = array.length;
        int[] bArray = new int[N];
        sort(array, bArray, N);
        tellDelegateSortIsComplete();
    }
    public void sort(int[] A, int[] B, int N){
        for (int w = 1; w < N; w *= 2){
            for (int i = 0; i < N; i = i + 2*w){
                delegate.visualizeData(array);
                if (!keepRunning)
                    return;
                merge(A, i, Math.min(i+w,N), Math.min(i+ 2*w,N), B);
            }
            CopyArray(B,A,N);
        }
    }

    public void merge(int[] A, int iLeft, int iRight, int iEnd, int[] B)
    {
        int i = iLeft;
        int j = iRight;

        for (int k = iLeft; k < iEnd; k++){
            if (i<iRight && (j >= iEnd || A[i] <= A[j])){
                B[k] = A[i];
                i++;
            }
            else{
                B[k] = A[j];
                j++;
            }
        }
    }

    public void CopyArray(int[] B, int[] A, int N){
    for (int i = 0; i < N; i++){
        A[i] = B[i];
    }
    }
}
