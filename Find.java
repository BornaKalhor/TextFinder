import sun.awt.Mutex;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;


public class Find{
    private String line;
    private String path;
    int start_time;
    int start_line = 0;
    int finish_line = 2465;
    Mutex mtx = new Mutex();
    ArrayList words = new ArrayList();
    ArrayList lines = new ArrayList();


    public void line2list(String path)
    {
        try
        {
            File strm = new File(path);

            Scanner scan = new Scanner(strm);

            while(scan.hasNextLine())
            {
                lines.add(scan.nextLine());
            }
        }
        catch(IOException e)
        {
            e.getCause();
        }

    }


    public void find(String mypath)
    {
        line2list(mypath);
        for(int i = 0 ; i <= lines.size()-1 ; i++)
        {

            for(int j = 0 ; j <= words.size()-1 ; j++)
            {

                String thisline = lines.get(i).toString();
                String thisword = words.get(j).toString();

                if( thisline.contains(thisword) )
                {
                    String result =
                            "found the word ["
                                    + thisword
                                    + "] in line "
                                    + i
                                    + " by "
                                    + " at "
                                    + new java.util.Date() + "\n";

                    System.out.println(result);
                    write2file(result);
                }
            }
        }

    }


    public void write2file(String data)
    {
        try
        {
            //FileOutputStream myfile = new FileOutputStream(path, true);
            FileWriter writer = new FileWriter("C:\\Users\\Borna\\Desktop\\Workflow\\Find\\src\\results.txt", true);

            mtx.lock();                 // wait(sem)
            try{ writer.append(data); }
            finally{ mtx.unlock(); }    // signal(sem)

            writer.close();

            System.out.println("Wrote in file at " + new java.util.Date());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    public void threadRunner(int number_of_threads)
    {
        if(number_of_threads > 5) number_of_threads = 5;
        Thread[] threads = new Thread[number_of_threads]; int k;
        for (k=1 ;  k<= number_of_threads-1 ; k++)
        {
            threads[k] = new Thread
            (new Runnable()
                {
                    @Override
                    public void run() {

                        find("C:\\Users\\Borna\\Desktop\\Workflow\\Find\\src\\sample.txt");

                    }
                }
            );

            threads[k].start();
        }
    }




}