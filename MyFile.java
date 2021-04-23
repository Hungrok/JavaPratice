package com.my.pratice ;



import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class MyFile {

    public static void fileTestMain(int which){ // FOR try-catch wrapper

        try {
            switch(which) {
                case (0):
                    practiceTest();
                    break ;
                case (1):
                    filecreateTest1();
                    break ;
                case (2):
                    filecreateTest2();
                    break ;
                case (3):
                    filereadTest1();
                    break ;
                case (4):
                    fileObjectTest1();
                    break ;
                case (5):
                    fileObjectTest2();
                    break ;
                case (6):
                    filenioTest();
                    break ;
                case (7):
                    filechannelTest1();
                    break ;
            }
        }
        catch (FileNotFoundException e) {
            System.out.printf("Exception-File Not Found \n");
        }
        catch (IOException e) {
            System.out.printf("Exception-IO \n");
        }
        catch (ClassNotFoundException e) {
            System.out.printf("Exception-Class Not Found \n");
        }
    }

    public static void practiceTest() throws IOException{

        System.out.printf("Please Input your text \n") ;

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s1 = "abc" ;
        String s2 = null ;

        while (s1 != null) { // infinite loop, never get EOF null
            s1 = br.readLine();
            System.out.printf("readLine:%s\n", s1);
            s2 = s1+s2 ;
            if (s1.equals("done"))
                break;
        }
        System.out.printf("your input is done  \n") ;
        System.out.printf("Select the mode, Write (1) or Append (2) ? \n") ;

        char[] ar1 = new char[20];
        boolean writemode = false ;
        br.read(ar1);
        if (ar1[0]=='1'){
            System.out.printf("Mode-write \n") ;
            writemode = true ;
        }
        else{
            System.out.printf("Mode-append \n") ;
            writemode = false ;
        }


        System.out.printf("File write or append...\n");

        FileWriter fw = null ;
        if (writemode)   // write mode
            fw = new FileWriter("sample4.txt",false) ;
        else // append mode
            fw = new FileWriter("sample4.txt",true) ;

        BufferedWriter bw = new BufferedWriter(fw) ;
        bw.write(s2) ; // replace if exist
        bw.close();

        System.out.printf("File read  \n");
        char[] buf = new char[100] ;
        int len ;
        String path = "sample4.txt";
        FileReader fr = new FileReader(path) ; // file open
        BufferedReader br2 = new BufferedReader(fr) ; // file read
        len = br2.read(buf) ;
        br.close();
        String s3 = new String(buf) ;
        System.out.printf("File read,len=%d,text=%s",len,s2) ;

    }

    public static void filecreateTest1() throws IOException{ // TEXT, character stream

        System.out.printf("File create1-character\n");
        // file descriptor for create - path 를 명시하지 않으면 project directory 를 기준으로 한다
        File fd1 = new File("sample1.txt") ;

        // create-open-write 순서로 파일 핸들링
        fd1.createNewFile() ; // create
        FileWriter fw = new FileWriter(fd1) ;  // open
        BufferedWriter bw = new BufferedWriter(fw) ;  // write
        bw.write("This is text (character) sample1.txt file \n"); // replace if exist
        bw.close();
    }

    public static void filecreateTest2() throws IOException { // TEXT, character stream

        System.out.printf("File create2-character\n");

        FileWriter fw = new FileWriter("sample2.txt") ; // open, 없으면 create 까지 적용한다
        BufferedWriter bw = new BufferedWriter(fw) ;
        bw.write("This is text (character) sample2.txt file\n") ; // replace if exist
        bw.close();
    }
    public static void filereadTest1()
            throws FileNotFoundException, IOException{ // TEXT, character stream

        System.out.printf("File read-character \n");
        // open-read 의 순서이며, file 이 존재하지 않으면 exception 발생한다

        char[] buf = new char[100] ;
        int len ;
        String path = "sample1.txt";
        File fd1 = new File("sample1.txt") ;

        // same with fr = new FileReader(fd1) ;
        FileReader fr = new FileReader(path) ; // file open
        BufferedReader br = new BufferedReader(fr) ; // file read
        len = br.read(buf) ;
        br.close();
        String s1 = new String(buf) ;
        System.out.printf("File read,len=%d,text=%s",len,s1) ;

    }

    public static void fileObjectTest1()
            throws FileNotFoundException, IOException, ClassNotFoundException{ // Object , Byte Stream

        System.out.printf("File Object-write/read \n");
        MyStudent sa1 = new MyStudent("Kwon",20,"3-4",65,56,78) ;

        FileOutputStream fos = new FileOutputStream("stdobj1.ser") ; // file create and open
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sa1);
        oos.close();
        fos.close();

        System.out.printf("Read Student object file \n");
        FileInputStream is = new FileInputStream("stdobj1.ser"); // file open
        ObjectInputStream ois = new ObjectInputStream(is);
        MyStudent sa = (MyStudent) ois.readObject(); // Type Casting
        ois.close();
        is.close();
    }

    public static void fileObjectTest2()
            throws FileNotFoundException, IOException, ClassNotFoundException{ // Object , Byte Stream

        System.out.printf("File Object List-write/read \n");
        MyStudent sa1 = new MyStudent("Kwon",20,"3-4",65,56,78) ;
        MyStudent sa2 = new MyStudent("Kim",21,"3-3",75,66,68) ;
        MyStudent sa3 = new MyStudent("Lee",22,"3-2",55,36,98) ;

        List<MyStudent> la1 = new LinkedList<MyStudent>() ;
        la1.add(sa1); la1.add(sa2); la1.add(sa3);

        FileOutputStream fos = new FileOutputStream("stdobjs.ser") ;
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(la1);
        oos.close();
        fos.close();

        System.out.printf("Read Student List objects file \n");
        FileInputStream is = new FileInputStream("stdobjs.ser"); // file open
        ObjectInputStream ois = new ObjectInputStream(is);
        List<MyStudent> la2 = (List<MyStudent>) ois.readObject();
        for(MyStudent a: la2) {
            System.out.printf("obj=%s \n",a.toString());
        }
        ois.close();
        is.close();
    }

    public static void filenioTest()
            throws FileNotFoundException, IOException{  // Files with Blocking mode

        System.out.printf("File NIO-blocking mode  \n");

        Path p1 = Paths.get("sample3.txt") ;
        Charset cs = StandardCharsets.UTF_16 ;
        String s2 = "abc"  ;
        char[] ar1 = new char[100] ;

        BufferedWriter bw = Files.newBufferedWriter(p1,cs) ;
        bw.write("대한민국은 민주공화국 이다 \n") ;
        bw.write("민주공화국은 국민이 주인이다 \n") ;
        bw.close();

        BufferedReader br = Files.newBufferedReader(p1,cs) ;
        br.read(ar1) ;
        String s1 = new String(ar1) ;
        System.out.printf("%s \n",s1) ;
        br.close();

        br = Files.newBufferedReader(p1,cs) ;
        while (s2!=null){
            s2 = br.readLine() ;
            System.out.printf("%s \n",s2) ;
        }
        br.close();
    }

    public static void filechannelTest1() throws IOException { // Files with Non-Blocking mode

        System.out.printf("File NIO-non blocking mode  \n");
        Path p1 = Paths.get("sample3.txt") ; // assuming file is exist
        Charset cs = StandardCharsets.UTF_16 ;

        ByteBuffer buffer = ByteBuffer.allocate(1000) ;
        FileChannel ch = (FileChannel) Files.newByteChannel(p1) ; // SeekAbleByteChannel
        while (true) {
            int count = ch.read(buffer) ; // -1 = EOF, 0 = not ready, x = read size
            System.out.printf("Read: count=%d, \n",count) ;
            System.out.printf("Buffer cap=%d,pos=%d,lim=%d \n"
                        ,buffer.capacity()
                        ,buffer.position()
                        ,buffer.limit()) ;
            if(count==-1) break ; // EOF (End Of File)
        }
    }
}

class MyFile2 { //  참고, try-catch wrapper 미 사용시
    public static void filecreateTest1() { // TEXT, character stream

        // file descriptor for create
        File fd1 = new File("/javaiproject/sample1.txt") ;
        FileWriter fw=null ;
        BufferedWriter bw=null ;

        try {
            fd1.createNewFile() ;
        }
        catch(IOException e){
            System.out.printf("IOE \n") ;
        }

        try {
            fw = new FileWriter(fd1) ;
        }
        catch(IOException e){
            System.out.printf("IOE 2 \n") ;
        }

        bw = new BufferedWriter(fw) ;
        try {
            bw.write("ccdcdcdcddccd\n");
            bw.close();
        }
        catch(IOException e){
            System.out.printf("IOE 3 \n") ;
        }

    }

    public static void filecreateTest2() { // TEXT, character stream

        FileWriter fw = null ;
        BufferedWriter bw = null ;

        System.out.printf("Write text file \n"); // file create and open

        try {
            fw = new FileWriter("sample1.txt") ; // at project
        }
        catch(IOException e){
            System.out.printf("IOE\n") ;
        }

        bw = new BufferedWriter(fw) ;
        try {
            bw.write("cdjcdkcdk\n");
            bw.close();
        }
        catch(IOException e){
            System.out.printf("IOE\n") ;
        }

    }
    public static void filereadTest1() { // TEXT, character stream


        FileReader fr=null ;
        BufferedReader br=null ;

        char[] buf = new char[100] ;
        int len ;
        String path = "/javaworkspace/sample.txt";
        File fd1 = new File("/javaworkspace/sample.txt") ;

        try {
            // same with fr = new FileReader(fd1) ;
            fr = new FileReader(path) ; // file open
        }
        catch(FileNotFoundException e){
            System.out.printf("FNE \n") ;
            return ;
        }
        try {
            br = new BufferedReader(fr) ;
            len = br.read(buf) ;
            br.close();
            String s1 = new String(buf) ;
            System.out.printf("File read,len=%d,text=%s",len,s1) ;
        }
        catch(IOException e){
            ;
        }


    }

    public static void fileObjectTest1() { // Object , Byte Stream

        MyStudent sa1 = new MyStudent("Kwon",20,"3-4",65,56,78) ;

        try {
            System.out.printf("Write object file \n");
            FileOutputStream fos = new FileOutputStream("stdobj1.ser") ; // file create and open
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(sa1);
            oos.close();
            fos.close();
        }
        catch (IOException e) { e.printStackTrace(); }

        try {
            System.out.printf("Read object file \n");
            FileInputStream is = new FileInputStream("stdobj1.ser"); // file open
            ObjectInputStream ois = new ObjectInputStream(is);
            MyStudent sa = (MyStudent) ois.readObject();
            ois.close();
            is.close();
        }
        catch (FileNotFoundException e){
            System.out.printf("FNE \n");
        }
        catch (IOException e){
            System.out.printf("IOE \n");
        }
        catch (ClassNotFoundException e){
            System.out.printf("CNE \n");
        }

    }

    public static void fileObjectTest2() { // Object , Byte Stream

        MyStudent sa1 = new MyStudent("Kwon",20,"3-4",65,56,78) ;
        MyStudent sa2 = new MyStudent("Kim",21,"3-3",75,66,68) ;
        MyStudent sa3 = new MyStudent("Lee",22,"3-2",55,36,98) ;

        List<MyStudent> la1 = new LinkedList<MyStudent>() ;
        la1.add(sa1);
        la1.add(sa2);
        la1.add(sa3);

        try {
            System.out.printf("Write objects file \n");
            FileOutputStream fos = new FileOutputStream("stdobjs.ser") ;
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(la1);
            oos.close();
            fos.close();
        }
        catch (IOException e) { e.printStackTrace(); }

        try {
            System.out.printf("Read objects file \n");
            FileInputStream is = new FileInputStream("stdobjs.ser"); // file open
            ObjectInputStream ois = new ObjectInputStream(is);
            List<MyStudent> la2 = (List<MyStudent>) ois.readObject();
            for(MyStudent a: la2) {
                System.out.printf("obj=%s \n",a.toString());
            }
            ois.close();
            is.close();
        }
        catch (FileNotFoundException e){
            System.out.printf("FNE \n");
        }
        catch (IOException e){
            System.out.printf("IOE \n");
        }
        catch (ClassNotFoundException e){
            System.out.printf("CNE \n");
        }

    }

    public static void filenioTest() { // Files with Blocking mode

        Path p1 = Paths.get("sample3.txt") ;
        Charset cs = StandardCharsets.UTF_16 ;
        BufferedWriter bw ;
        BufferedReader br ;
        String s2 = "abc"  ;
        char[] ar1 = new char[100] ;

        try {
            bw = Files.newBufferedWriter(p1,cs) ;
            bw.write("대한민국은 민주공화국 이다 \n") ;
            bw.write("민주공화국은 국민이 주인이다 \n") ;
            bw.close();
        }
        catch (FileNotFoundException e){
            System.out.printf("FNE \n");
        }
        catch (IOException e){
            System.out.printf("IOE \n");
        }

        try {
            br = Files.newBufferedReader(p1,cs) ;
            br.read(ar1) ;
            String s1 = new String(ar1) ;
            System.out.printf("%s \n",s1) ;
            br.close();

            br = Files.newBufferedReader(p1,cs) ;
            while (s2!=null){
                s2 = br.readLine() ;
                System.out.printf("%s \n",s2) ;
            }
            br.close();

        }
        catch (FileNotFoundException e){
            System.out.printf("FNE \n");
        }
        catch (IOException e){
            System.out.printf("IOE \n");
        }

    }

    public static void filechannelTest1() { // Files with Non-Blocking mode

        Path p1 = Paths.get("sample3.txt") ; // assuming file is exist
        Charset cs = StandardCharsets.UTF_16 ;

        ByteBuffer buffer = ByteBuffer.allocate(1000) ;
        try {
            FileChannel ch = (FileChannel) Files.newByteChannel(p1) ; // SeekAbleByteChannel
            while (true) {
                int count = ch.read(buffer) ; // -1 = EOF, 0 = not ready, x = read size
                System.out.printf("Read: count=%d, \n",count) ;
                System.out.printf("Buffer cap=%d,pos=%d,lim=%d \n"
                        ,buffer.capacity()
                        ,buffer.position()
                        ,buffer.limit()) ;
                if(count==-1) break ; // EOF (End Of File)
            }

        }
        catch (IOException e){ System.out.printf("IOE \n");}

    }
}

