package com.openxu.tjjh.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TcpServer {

	static ServerSocket ss = null;
	static Socket s = null;
	public static void main(String[] args){
		readAndSendThread.start();  
		//readAndSendThread1.start();  
//		readThread.start();
		
	}
	private static void a(int[] a){
		a[2] = 10;
	}

	private static Thread readThread = new Thread(){
		public void run() {
			while(true){
				try{
					ss = new ServerSocket(11113);
					s = ss.accept();   //�õ��ͻ��˶������� ʽ
					String ip = s.getInetAddress().getHostAddress();
					System.out.println(ip+"已经连接connected");
					
					//��ȡ�ͻ��˷���4�����
					InputStream in = s.getInputStream();
					byte[] buf = new byte[1024];
					while(true){
						int len = in.read(buf);
						System.out.println("收到"+ip+"的数据"+new String(buf,0,len,"UTF-8"));
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						if(ss!=null)
								ss.close();
						if(s!=null)
							s.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		};
	};
	private static Thread readAndSendThread1 = new Thread(){
		public void run() {
			while(true){
				try{
					ss = new ServerSocket(11114);
					s = ss.accept();   //�õ��ͻ��˶������� ʽ
					String ip = s.getInetAddress().getHostAddress();
					System.out.println(ip+"已经连接connected");
					
					//��ȡ�ͻ��˷���4�����
					InputStream in = s.getInputStream();
					byte[] buf = new byte[1024];
					while(true){
						int len = in.read(buf);
//						if(len == -1)
//							continue;
						System.out.println("数据长度"+len);
						String command = new String(buf, 0, len).trim();
						System.out.println("收到"+ip+"的数据"+command);
						//��!��ݸ�ͻ���
						OutputStream out = s.getOutputStream();
						out.write(command.toUpperCase().getBytes());
						out.flush();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						if(ss!=null)
								ss.close();
						if(s!=null)
							s.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		};
	};
	
	
	private static Thread readAndSendThread = new Thread(){
		public void run() {
			while(true){
				try{
					ss = new ServerSocket(11113);
					s = ss.accept();   //�õ��ͻ��˶������� ʽ
					String ip = s.getInetAddress().getHostAddress();
					System.out.println(ip+"已经连接connected");
					
					//��ȡ�ͻ��˷���4�����
					InputStream in = s.getInputStream();
					byte[] buf = new byte[1024];
					while(true){
						int len = in.read(buf);
						System.out.println("收到"+ip+"的数据"+new String(buf,0,len,"UTF-8"));
						//��!��ݸ�ͻ���
						Random random = new Random();
						int x = random.nextInt()%3;
						OutputStream out = s.getOutputStream();
						out.write(x==1?"E".getBytes():"K".getBytes());
						out.flush();
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						if(ss!=null)
								ss.close();
						if(s!=null)
							s.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		};
	};
}
