package com.learn.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws Exception {
        //1.创建
        //服务器地址
        InetAddress serverIP = InetAddress.getByName("10.200.21.163");
        //服务器端口号
        int port = 8091;
        Socket socket = new Socket(serverIP,port);
        //2.获取输入流输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        //3.1处理数据(发送)
        bw.write("nihao");
//        bw.write("0x11 00 13 3c 00 04 61 62 63 64 00 04 61 62 63 64 00 04 61 62 63 64");
        bw.newLine();
        bw.flush();
        //3.2处理数据(接收)
        String str = br.readLine();
        System.out.println("服务端跟你说："+str);
        //4.关闭资源
        bw.close();
        socket.close();

    }
}
