import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.Date;

public class SortPanel extends JPanel implements AlgorithmDelegate
{
    private Date lastUpdate;
    private BufferedImage myCanvas;
    private boolean dirtyCanvas;
    private SortFrame myFrame;
    int visual_count;
    private int delay_ms;

    public SortPanel(SortFrame myFrame)
    {
        super();
        lastUpdate = new Date();
        delay_ms = 1;
        dirtyCanvas = true;
        setBackground(Color.lightGray);
        this.myFrame = myFrame;
        visual_count = 0;
    }


  
    public BufferedImage getCanvas()
    {
        if (dirtyCanvas)
        {
            System.out.println("(" + getWidth() + ", " + getHeight() + ")");
            myCanvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            dirtyCanvas = false;
        }
        return myCanvas;
    }

  
    public void SortIsFinished()
    {
        myFrame.endRunGUI();
        lastUpdate = new Date(0); // this sets the last update to 12:00am, 1/1/1970
    }

    public void setDelayMS(int delay)
    {
        delay_ms = delay;
    }

  
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        getCanvas();
        if (myCanvas == null)
            return;
        synchronized (myCanvas)
        {
            g.drawImage(myCanvas,0,0,this);
        }

    }

  
    public void prepForArrayWithSizeN(int N)
    {
        System.out.println("Prepping - new array size "+N);

    }

  
    public void visualizeData(int [] array)
    {
        Date now = new Date();
        visual_count++;
        if ((now.getTime() - lastUpdate.getTime())>17) //33 ms = 1/30 second
        {
            getCanvas();
            if (myCanvas== null)
                return;
            synchronized (myCanvas)
            {
                Graphics g = myCanvas.getGraphics();
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());

                int N = array.length;

                int border = 10;
                double xScale = (getWidth()-2.0*border)/N;
                double yScale = (getHeight()-2.0*border)/N;


                if (xScale>1)
                    for (int i=0; i<N; i++)
                    {
                        float hue = (float)array[i]/(float)array.length;

                        g.setColor(Color.getHSBColor(hue,1,1));
                        g.fillRect((int)(border+i*xScale),(int)(getHeight()-border-array[i]*yScale),(int)(xScale+1),(int)(array[i]*yScale));
                    }
                else
                    for (int i=0; i<N; i++)
                    {
                        float hue = (float)array[i]/(float)array.length;
                        g.setColor(Color.getHSBColor(hue,1,1));
                        g.drawLine((int)(border+i*xScale),(int)(getHeight()-border-array[i]*yScale),(int)(border+i*xScale),getHeight()-border);
                    }
            }
            repaint();
            lastUpdate = new Date();
        }
        try
        {
            Thread.sleep(delay_ms);
        }catch (InterruptedException iExp)
        {
            return;
        }
    }

   
    public void setDirtyCanvas()
    {
        dirtyCanvas = true;
    }


}
