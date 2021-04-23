package com.my.pratice ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MyStdIO {

    // System.out 은 JVM init 과정에서 PrintStream 객체가 assignment 되어짐
    // System.setOut() 을 통하여 다른 stream 으로 연결가능 (file 로 저장, graphic - textArea)
    static PrintStream myprt = System.out ;

    static void printTest1(){
        int a=10, b=20 ;
        myprt.println("a+b=" +(a+b) +"   a*b=" +a*b +"   boolean=" +(a==10) ) ;
        myprt.printf("a+b=%d\t\ta*b=%d\t\tboolean=%b \n",(a+b),(a*b),(a==10)) ;

        // Create Four Card elements by string
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
                "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

        ArrayList<String> deck = new ArrayList<String>();
        for (String rank : ranks) {
            for (String suit : suits)
                deck.add(rank + "of" + suit);
        }
        // Following 3 are Same
        myprt.println(deck); // == deck.toString()
        myprt.printf("%s\n",deck); // == deck.toString()
        myprt.printf("%s\n",deck.toString());
    }


    static void stdinTest1() {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        char[] ar1 = new char[100];
        String s2 = "abc";
        try {
            br.read(ar1);
            String s1 = new String(ar1);
            System.out.printf("read:%s \n", s1);
            //br.close();
            //isr = new InputStreamReader(System.in) ;
            //br = new BufferedReader(isr) ; // encounter IOException

            while (s2 != null) { // infinite loop, never get EOF null
                s2 = br.readLine();
                System.out.printf("readLine:%s\n", s2);
                if (s2.equals("done"))
                    break;
            }
            br.close();
        } catch (IOException e) {
            System.out.printf("IOE\n");
        }

    }
}

class makeLog { // utility 
	
	BufferedWriter bw = null;

	String logFileName = "휴게시간생성_" + this.getTimeStamp() + "_log.txt";
	
	static String localDir = System.getProperty("user.dir");
	
	private static String sLOG_FILE_PATH = localDir+File.separator + "log" ;

    String logs = "";
    
	FileWriter fwLog = null;
	PrintWriter pwLog = null;
	
	/*
	public void makeLogFile(String logMsg) throws IOException {
		
	      File resultFile = new File(localDir+File.separator + "log");
	      if (!resultFile.isDirectory()) {
	        resultFile.mkdir();
	      }
	      
	      //File fileLog = resultFile;
	      
	      //fwLog = new FileWriter(fileLog.getAbsolutePath(), true);
		  //pwLog = new PrintWriter(fwLog);
	      
	      
	      bw = new BufferedWriter(new FileWriter(localDir+File.separator + "log" + File.separator + logFileName));
	      //logs = logs + logMsg;
	      
			try {
				bw.write(logMsg);
				bw.flush();
				
				//pwLog.print(logMsg);
				//pwLog.flush();
			} catch (Exception ex) {
				System.out.println("LOG FAIL: " + ex.toString());
			} finally {
				try {
					if (bw != null)
						bw.close();
				} catch (Exception ex) {
					System.out.println("LOG CLOSE FAIL:" + ex.toString());
				}
			}

	      //bw.close();
	}*/
	
	
	protected static File getLogFile() throws Exception
	{
		// 로그 파일에 대한 정보를 가져온다.
		long lCurrentTime = System.currentTimeMillis();
		Date dateCurrentTime = new Date(lCurrentTime);
		
		Calendar calCurrentTime = new GregorianCalendar();
		calCurrentTime.setTime(dateCurrentTime);

		// 메세지를 로그파일에 씀
		String	strYear		=	Integer.toString(calCurrentTime.get(Calendar.YEAR));
		String	strMonth	=	new DecimalFormat("00").format(calCurrentTime.get(Calendar.MONTH) + 1);
		String	strDay		=	new DecimalFormat("00").format(calCurrentTime.get(Calendar.DAY_OF_MONTH));

		StringBuffer sbufferLogFile = new StringBuffer("");
		sbufferLogFile.append(sLOG_FILE_PATH);
		sbufferLogFile.append(File.separator);
		sbufferLogFile.append(strYear);
		sbufferLogFile.append(strMonth);
		sbufferLogFile.append(strDay);
		sbufferLogFile.append(".log");

		File fileLog = new File(sbufferLogFile.toString());

		return fileLog;
	}
	
	public static synchronized void print(Object obj)
	{
		FileWriter fwLog = null;
		PrintWriter pwLog = null;

		try
		{
			// 로그 파일에 대한 정보를 가져온다.
			File fileLog = getLogFile();

			fwLog = new FileWriter(fileLog.getAbsolutePath(), true);
			pwLog = new PrintWriter(fwLog);

			// 메세지를 로그파일에 씀
			pwLog.print(obj);
			pwLog.flush();
		}
		catch(Exception ex)
		{
			System.out.println("LOG FAIL: " + ex.toString());
		}
		finally
		{
			try
			{
				if(pwLog != null) pwLog.close();
				if(fwLog != null) fwLog.close();
			}
			catch(Exception ex)
			{
				System.out.println("LOG CLOSE FAIL:" + ex.toString());
			}
		}
	}

	public static String getTimeStamp() {
		String rtnStr = null;
		String pattern = "yyyyMMddhhmmss";
		try {
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());

			rtnStr = sdfCurrent.format(Long.valueOf(ts.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnStr;
	}

}
