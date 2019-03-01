package com.nevergetme;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SeuLogin {
    public static void main(String[] args){
        String username;
        String password;
        String ID;
        String check;
        ArgsProperty argsProperty=new ArgsProperty();
        boolean sendState=false;
        int len=args.length;
        for(int i=0;i<len-1;){
            if(args[i].equals("-u")){
                argsProperty.setUsername(args[++i]);
            }else if(args[i].equals("-p")){
                argsProperty.setPassword(LoginIn.base64encode(args[++i]));
            }else if(args[i].equals("-s")){
                if(args[++i].equals("1")){
                    sendState=true;
                }else {
                    sendState=false;
                }
            }else if(args[i].equals("-i")){
                argsProperty.setID(args[++i]);
            }
            i++;
        }

        username=argsProperty.getUsername();
        password=argsProperty.getPassword();
        ID=argsProperty.getID();
        check=argsProperty.getCheck();
        argsProperty.saveProp();
        if(username==null||password==null){
            System.out.println("please set username or password");
        }else{
            String loginUrl="http://w.seu.edu.cn/index.php/index/login.php";
            String sendParam="username="+username+"&password="+password;
            final long timeInterval = 1000*60*20;
            final String IP_IP=LoginIn.getIP();
            boolean finalSendState = sendState;
            Runnable runnable = new Runnable() {
                public void run() {
                    while (true) {
                        LoginIn.sendPost(loginUrl,sendParam);
                        if(finalSendState){
                            System.out.println(LoginIn.sendPost("http://www.nevergetme.com/getMyComputer.php","check="+check+"&id="+ID+"&IP="+IP_IP));
                        }
                        try {
                            Thread.sleep(timeInterval);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}