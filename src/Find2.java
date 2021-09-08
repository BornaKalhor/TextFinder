// Borna Kalhor
// Ferdowsi University of Mashhad


import sun.awt.Mutex;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;


public class Find2 {
    private String line;
    private String path;
    Mutex mtx = new Mutex();
    ArrayList words = new ArrayList();
    ArrayList lines = new ArrayList();


    public void line2list(String path)
    {
        try
        {
            FileInputStream myfile = new FileInputStream(path);
            Scanner scan = new Scanner(myfile);

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


    public void find()
    {
        line2list("C:\\Users\\Borna\\Desktop\\Find2\\sample.txt");
        for(int i = 0 ; i <= lines.size()-1 ; i++)
        {

            for(int j = 0 ; j <= words.size()-1 ; j++)
            {

                String thisline = lines.get(i).toString();
                String thisword = words.get(j).toString();

                if( thisline.contains(thisword) )
                {
                    String result = "found the word " + thisword + " in line " + i + " at " + java.time.LocalDateTime.now() + "\n";
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
            FileWriter writer = new FileWriter("resultsss.txt", true);

            mtx.lock();
            try{ writer.append(data); }
            finally{ mtx.unlock(); }

            writer.close();

            System.out.println("Wrote in file at ");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


  /*  public void threadRunner(int number_of_threads)
    {
        if(number_of_threads > 5) number_of_threads = 5;
        for (int k ; k <= number_of_threads ; k++)
        {
            new Thread(new find());
            t.start();
        }
    }*/




}