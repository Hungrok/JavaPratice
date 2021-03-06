package com.my.pratice ;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyNetwork {


    public static void networkTestMain(int which){ // FOR try-catch wrapper

        try {
            switch(which) {
                case (0):
                    networkSocketTest();
                    break ;
                case (1):
                    networkClientTest();
                    break ;
                case (2):
                    networkServerTest();
                    break ;
                case (3):
                    networkHttpTest1();
                    break ;
                case (4):
                    networkHttpTest2();
                    break ;
                case (5):
                    networkHttpTest3();
                    break ;
                case (6):
                    networkNonblockTest1() ;
                    break ;
                case (7):
                    networkNonblockTest2();
                    break ;
            }
        }
        catch (MalformedURLException e) {
            System.out.printf("Exception-MalformedURLException \n");
        }
        catch (IOException e) {
            System.out.printf("Exception-IO \n");
        }
        catch (InterruptedException e) {
            System.out.printf("Exception-InterruptedException \n");
        }
    }

    public static void networkSocketTest() {
        // method-1 using URL
        InetSocketAddress isa = new InetSocketAddress("www.google.com",80) ;
        Socket sa1 = new Socket() ;
        // method-2 using IP address as String
        InetSocketAddress isb = new InetSocketAddress("192.168.0.1",80) ;
        Socket sa2 = new Socket() ;

    }


    public static void networkClientTest() throws IOException { // TCP client

        String server = "192.168.0.73" ; // ?????? IP ??????
        int port = 990 ; // ?????? ??? port number
        DataInputStream dis=null ;
        DataOutputStream dos=null ;
        InputStreamReader isr = null ;
        BufferedReader br = null ;
        char[] ar1 = new char[100] ;

        InetSocketAddress isa = new InetSocketAddress(server,port) ;
        Socket cs = new Socket() ; // for client socket

        System.out.printf("Connecting with server=%s ...",server) ;
        cs.connect(isa); // ????????? connect ?????? (????????? IP ??? port ????????? ?????? - connection oriented)
        // Thread Blocking until connected or timeout
        System.out.printf("Connected \n") ;


        if(!cs.isConnected()){
            System.out.printf("Connecting timeout\n") ;
            cs.close();
            return ;
        }

        // app - read - DataInputStream - InputStream - Socket
        // app - write - DataOutputStream - OutputStream - Socket
        dis = new DataInputStream(cs.getInputStream()) ;
        dos = new DataOutputStream(cs.getOutputStream()) ;

        // for stdin read
        isr = new InputStreamReader(System.in) ;
        br = new BufferedReader(isr) ;
        // write
        dos.writeUTF("Hello \n") ;
        while(true) {
            if(!cs.isConnected()){
                    System.out.printf("Connecting timeout\n") ;
                    break ;
            }
            String s1 = dis.readUTF() ;  // read from socket  --> Server also should use writeUTF 
            System.out.printf("@Server:%s\n",s1) ;
            s1 = s1.substring(0, 6) ;
            if(s1.equals("byebye")) {break ; }

            br.read(ar1) ; // stdin read
            String s2 = new String(ar1) ; // including array init value (0)
            System.out.printf("@Client:%s\n",s2) ;
            dos.writeUTF(s2) ; // write to socket
        }

        if(dis!=null) dis.close();
        if(dos!=null) dos.close();
        if(cs!=null) cs.close() ;
        if(br!=null) br.close();

    }

    public static void networkServerTest() throws IOException { // TCP server

        DataInputStream dis=null ;
        DataOutputStream dos=null ;
        InputStreamReader isr = null ;
        BufferedReader br = null ;
        ServerSocket ss=null ;
        Socket cs=null ;
        char[] ar1 = new char[100] ;
        // TCP server socket , ?????? port number ?????? (?????? protocol ??? well-known port ??????)
        ss = new ServerSocket(990) ; // bind-listen ?????? ?????? ??????
        cs = ss.accept() ; // client connect ?????? (??????) --> client session ??? socket ??? return
        System.out.printf("Connected with client \n") ;
        // appl - read - DataInputStream - InputStream - Socket
        // appl - write - DataOutputStream - OutputStream - Socket
        dis = new DataInputStream(cs.getInputStream()) ;
        dos = new DataOutputStream(cs.getOutputStream()) ;
        // for stdin read
        isr = new InputStreamReader(System.in) ;
        br = new BufferedReader(isr) ;
        while(true) {
            if(!cs.isConnected()){
                    System.out.printf("Connecting timeout\n") ;
                    break ;
            }

            String s1 = dis.readUTF() ; // read from socket
            System.out.printf("@Client:%s",s1) ;
            s1 = s1.substring(0, 6) ;
            if(s1.equals("byebye")) {break ; }

            br.read(ar1) ; // stdin read
            String s2 = new String(ar1) ; // including array init value (0)
            System.out.printf("@Server:%s\n",s2) ;
            dos.writeUTF(s2) ; // write to socket
        }

        if(dis!=null) dis.close();
        if(dos!=null) dos.close();
        if(cs!=null) cs.close() ;
        if(ss!=null) ss.close() ;
        if(br!=null) br.close();

    }

    public static void networkHttpTest1() throws MalformedURLException, IOException {

        BufferedReader br=null ;
        URL url=null ;
        HttpURLConnection huc=null ;

        // server response redirect as https
        url = new URL("http://www.daum.net") ;
        huc = (HttpURLConnection) url.openConnection() ;
        huc.disconnect();

    }

    public static void networkHttpTest2() throws MalformedURLException, IOException{

        BufferedReader br=null ;
        URL url=null ;
        HttpURLConnection huc=null ;

        url = new URL("https://www.daum.net") ;
        huc = (HttpURLConnection) url.openConnection() ;
        //huc.setRequestMethod("GET"); // does not need as default..
        if(huc.getResponseCode()!=200) {
            System.out.printf("Connection failed \n") ;
            huc.disconnect();
            return ;
        }
        br = new BufferedReader(new InputStreamReader(huc.getInputStream())) ;

        String s1=null ;
        StringBuilder sb = new StringBuilder () ;
        while (true) {
            s1 = br.readLine();
            if(s1==null) break ; // end of file
            sb.append(s1) ;
            sb.append('\n') ;
        }

        huc.disconnect();
        br.close();

        System.out.printf("%s",sb.toString()) ;

    }

    public static void networkHttpTest3() throws MalformedURLException, IOException{
        /* ????????? ??????????????????????????? - ?????????????????? (+3~10???) ?????? API
           . REST ??????
           . REST ??? http protocol ??? ???????????? ????????? ????????? ??????(DB) ??? field ???
             XML ?????? JSON notation ?????? response ?????? ???????????? ????????? ?????????
           . client ?????? ??? ????????? DB ????????? ???????????? ?????????
           . URL : http://newsky2.kma.go.kr
           . URN : /service/MiddleFrcstInfoService/getMiddleTemperature
             * resource ??? action ??? ???????????? operation ?????? (method) ?????? API ??? ??????
           . Parameter : ServiceKey, regID, tmFc, pageNo, numOfRows
             * ServiceKey ??? ??????????????? ????????? ?????? ????????? key ??????
             * regID : ??? ???????????? ??????
             * tmFc  : ????????????????????? (??????2??? 0600, 2000)
         */
        String url ="http://newsky2.kma.go.kr" ;
        String urn ="/service/MiddleFrcstInfoService/getMiddleTemperature" ;
        String myServiceKey="?ServiceKey=e2XNDwxs88BKbPyovt%2FSEusnDwZ5BY%2BZPFV0oTFZkHAjeMbxcp1Nx7mAyl%2Fs2aZMjw8rCNvA%2F4ycCuqO4BVgKQ%3D%3D" ;
        String parameter ="&regId=11D20501&tmFc=201905290600&pageNo=1&numOfRows=1" ;
        String total = url+urn+myServiceKey+parameter ;
        URL urlobj = new URL(total) ;
        HttpURLConnection huc = (HttpURLConnection) urlobj.openConnection() ;
        if(huc.getResponseCode()!=200) {
            System.out.printf("Connection failed \n") ;
            huc.disconnect();
            return ;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream())) ;

        String s1=null ;
        StringBuilder sb = new StringBuilder () ;
        while (true) {
            s1 = br.readLine();
            if(s1==null) break ; // end of file
            sb.append(s1) ;
            sb.append('\n') ;
        }

        huc.disconnect();
        br.close();

        System.out.printf("%s",sb.toString()) ;

    }

    // code is not completed
    public static void networkNonblockTest1() throws IOException{

        String server = "localhost" ;
        int port = 990 ;
        InetSocketAddress isa = new InetSocketAddress(server,port) ;

        ByteBuffer buffer = ByteBuffer.allocate(1000) ;
        SocketChannel client = SocketChannel.open() ;
        client.configureBlocking(false) ; // Non-blocking mode
        client.connect(isa) ;
        int readc  = client.read(buffer) ;
        int wrc = client.write(buffer) ;

    }
    // code is not completed
    public static void networkNonblockTest2() throws IOException, InterruptedException{

        String server = "localhost" ;
        int port = 990 ;
        InetSocketAddress isa = new InetSocketAddress(server,port) ;
        ByteBuffer buffer = ByteBuffer.allocate(1000) ;
        Selector selector=null; ;
        SocketChannel client=null ;
        SelectionKey clientKey=null ;

        selector = Selector.open();
        client = SocketChannel.open() ;
        client.configureBlocking(false) ; // Must be Non-blocking mode
        clientKey = client.register(selector, SelectionKey.OP_CONNECT) ;

        while(true) {

            if(selector.select()==0) {
                Thread.sleep(1);
                continue ;
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys() ;
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator() ;
            while(keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()) {} // DO something
                else if (selectionKey.isConnectable()) {} // DO something
                else if (selectionKey.isWritable()) {} // DO something
                else if (selectionKey.isReadable()) {} // DO something
            }
        }
    }
}


class Network2 {




    public static void networkSocketTest() {
        // method-1 using URL
        InetSocketAddress isa = new InetSocketAddress("www.google.com",80) ;
        Socket sa1 = new Socket() ;
        // method-2 using IP address as String
        InetSocketAddress isb = new InetSocketAddress("192.168.0.1",80) ;
        Socket sa2 = new Socket() ;
    }


    public static void networkClientTest() {

        String server = "192.168.35.230" ;
        int port = 990 ;
        DataInputStream dis=null ;
        DataOutputStream dos=null ;
        InputStreamReader isr = null ;
        BufferedReader br = null ;
        char[] ar1 = new char[100] ;

        InetSocketAddress isa = new InetSocketAddress(server,port) ;
        Socket cs = new Socket() ;
        try {
            System.out.printf("Connecting with server=%s ...",server) ;
            cs.connect(isa);
            System.out.printf("Connected \n") ;
        }
        catch(IOException e) {System.out.printf("IOE \n");}

        if(!cs.isConnected()){
            System.out.printf("Connecting timeout\n") ;
            try {cs.close();}
            catch(IOException e) {System.out.printf("IOE \n");}
            return ;
        }
        try {
            dis = new DataInputStream(cs.getInputStream()) ;
            dos = new DataOutputStream(cs.getOutputStream()) ;
        }
        catch (IOException e) {System.out.printf("IOE \n");}

        try {
            isr = new InputStreamReader(System.in) ;
            br = new BufferedReader(isr) ;
            dos.writeUTF("Hello \n") ;
            while(true) {
                if(!cs.isConnected()){
                    System.out.printf("Connecting timeout\n") ;
                    break ;
                }
                String s1 = dis.readUTF() ;
                System.out.printf("@Server:%s\n",s1) ;
                s1 = s1.substring(0, 6) ;
                if(s1.equals("byebye")) {break ; }

                br.read(ar1) ;
                String s2 = new String(ar1) ; // including array init value (0)
                System.out.printf("@Client:%s\n",s2) ;
                dos.writeUTF(s2) ;
            }
        }
        catch (IOException e) {System.out.printf("IOE \n");}

        try {
            if(dis!=null) dis.close();
            if(dos!=null) dos.close();
            if(cs!=null) cs.close() ;
            if(br!=null) br.close();
        }
        catch (IOException e) {System.out.printf("IOE \n");}


    }

    public static void networkServerTest() {

        DataInputStream dis=null ;
        DataOutputStream dos=null ;
        InputStreamReader isr = null ;
        BufferedReader br = null ;
        ServerSocket ss=null ;
        Socket cs=null ;
        char[] ar1 = new char[100] ;

        try {
            ss = new ServerSocket(990) ; // bind-listen
            cs = ss.accept() ; // Infinite wait, after accept we get Socket
            System.out.printf("Connected with client \n") ;
            dis = new DataInputStream(cs.getInputStream()) ;
            dos = new DataOutputStream(cs.getOutputStream()) ;
        }
        catch(IOException e) {
            System.out.printf("Exit server as already server socket we have\n");
            return ;
        }

        try {
            isr = new InputStreamReader(System.in) ;
            br = new BufferedReader(isr) ;
            while(true) {
                if(!cs.isConnected()){
                    System.out.printf("Connecting timeout\n") ;
                    break ;
                }

                String s1 = dis.readUTF() ;
                System.out.printf("@Client:%s",s1) ;
                s1 = s1.substring(0, 6) ;
                if(s1.equals("byebye")) {break ; }

                br.read(ar1) ;
                String s2 = new String(ar1) ; // including array init value (0)
                System.out.printf("@Server:%s\n",s2) ;
                dos.writeUTF(s2) ;

            }
        }
        catch (IOException e) {System.out.printf("IOE \n");}

        try {
            if(dis!=null) dis.close();
            if(dos!=null) dos.close();
            if(cs!=null) cs.close() ;
            if(ss!=null) ss.close() ;
            if(br!=null) br.close();
        }
        catch (IOException e) {System.out.printf("IOE \n");}

    }

    public static void networkHttpTest1() {

        BufferedReader br=null ;
        URL url=null ;
        HttpURLConnection huc=null ;

        try {
            url = new URL("http://www.daum.net") ;
            huc = (HttpURLConnection) url.openConnection() ;
            huc.disconnect();
        }
        catch (MalformedURLException e) {System.out.printf("URLE \n");}
        catch (IOException e) {System.out.printf("IOE \n");}
    }

    public static void networkHttpTest2() {

        BufferedReader br=null ;
        URL url=null ;
        HttpURLConnection huc=null ;

        try {
            url = new URL("https://www.daum.net") ;
            huc = (HttpURLConnection) url.openConnection() ;
            //huc.setRequestMethod("GET"); // does not need as default..
            if(huc.getResponseCode()!=200) {
                System.out.printf("Connection failed \n") ;
                huc.disconnect();
                return ;
            }
            br = new BufferedReader(new InputStreamReader(huc.getInputStream())) ;
        }
        catch (MalformedURLException e) {System.out.printf("URLE \n");}
        catch (IOException e) {System.out.printf("IOE \n");}

        String s1=null ;
        StringBuilder sb = new StringBuilder () ;
        while (true) {
            try { s1 = br.readLine();}
            catch (IOException e) {System.out.printf("IOE \n");}
            if(s1==null) break ; // end of file
            sb.append(s1) ;
            sb.append('\n') ;
        }
        try {
            huc.disconnect();
            br.close();
        }
        catch (IOException e) {System.out.printf("IOE \n");}

        System.out.printf("%s",sb.toString()) ;


    }

    public static void networkNonblockTest1() {

        String server = "localhost" ;
        int port = 990 ;
        InetSocketAddress isa = new InetSocketAddress(server,port) ;

        ByteBuffer buffer = ByteBuffer.allocate(1000) ;
        try {
            SocketChannel client = SocketChannel.open() ;
            client.configureBlocking(false) ; // Non-blocking mode
            client.connect(isa) ;
            int readc  = client.read(buffer) ;
            int wrc = client.write(buffer) ;
        }
        catch (IOException e) {System.out.printf("IOE \n");}

    }

    public static void networkNonblockTest2() {

        String server = "localhost" ;
        int port = 990 ;
        InetSocketAddress isa = new InetSocketAddress(server,port) ;
        ByteBuffer buffer = ByteBuffer.allocate(1000) ;
        Selector selector=null; ;
        SocketChannel client=null ;
        SelectionKey clientKey=null ;
        try {
            selector = Selector.open();
            client = SocketChannel.open() ;
            client.configureBlocking(false) ; // Must be Non-blocking mode
            clientKey = client.register(selector, SelectionKey.OP_CONNECT) ;
        }
        catch (IOException e) {System.out.printf("IOE \n");}

        while(true) {
            try {
                if(selector.select()==0) {
                    try {Thread.sleep(1);}
                    catch (InterruptedException e) {System.out.printf("IOE \n");}
                    continue ;
                }
            }
            catch (IOException e) {System.out.printf("IOE \n");}

            Set<SelectionKey> selectedKeys = selector.selectedKeys() ;
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator() ;
            while(keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()) {} // DO something
                else if (selectionKey.isConnectable()) {} // DO something
                else if (selectionKey.isWritable()) {} // DO something
                else if (selectionKey.isReadable()) {} // DO something
            }

        }
    }
}
