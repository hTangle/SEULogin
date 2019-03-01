package com.nevergetme;

import java.io.*;
import java.util.Properties;

public class ArgsProperty {
    Properties props=new Properties();
    boolean existState=false;
    String username,password,ID;
    public ArgsProperty(){
        try {
            props.load(new FileInputStream("prop.ini"));
        } catch (IOException e) {
            existState=false;
            return;
            //e.printStackTrace();
        }

        username=getUsername();
        password=getPassword();
        ID=getID();
        if(username==null||password==null){

        }else{
            existState=true;
        }
    }
    public String getPassword(){
        return props.getProperty("password");
    }
    public String getUsername(){
        return props.getProperty("username");
    }
    public void setUsername(String username){
        props.setProperty("username",username);
        this.username=username;
    }
    public void setPassword(String password){
        props.setProperty("password",password);
        this.password=password;
    }
    public void setID(String ID){
        props.setProperty("ID",ID);
        this.ID=ID;
    }
    public String getID(){
        return props.getProperty("ID");
    }
    public String getCheck(){
        return props.getProperty("check");
    }
    public void saveProp(){
        try {
            props.store(new FileOutputStream("prop.ini"),"user properity");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
