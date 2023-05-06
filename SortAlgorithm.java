import java.awt.image.BufferedImage;

public abstract class SortAlgorithm extends Thread
{
    protected AlgorithmDelegate delegate;
    protected int[] array;
    protected boolean keepRunning = true;
 
    public SortAlgorithm(AlgorithmDelegate del, int[] array)
    {
        delegate = del;
        this.array = array; 
    }

    public void cancel()
    {
        keepRunning = false;
    }

    public abstract void run();


    public void tellDelegateSortIsComplete()
    {
        if (delegate != null)
        {
            delegate.SortIsFinished();
            delegate.visualizeData(array); // makes sure we get to see the final version!
        }
    }

}
