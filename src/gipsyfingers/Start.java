/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gipsyfingers;


import Client.Login;
import SQL.SQLConnect;
import SQL.UsersDAL;
import Server.RunServer;
import Client.RunClient;
import Client.MainForm;
import Common.BaseItems.UserItem;
import java.awt.EventQueue;
import java.net.Socket;
import javax.swing.UIManager;
import XML.BaseXMLCommand;
import XML.Command;

/**
 *
 * @author Antonello Sanza
 */
public class Start {

    /**
        * @param args
        */
    public static void main(String[] args) {
        if(args.length > 0) {
            if(args[0].equals("Client")){
                    //Run Client
                    RunClient("localhost", 4444);
            }
            else {
                    //Run Server
                    RunServer(4444);
            }
        }
    }
    public static void RunClient(String serverHost, int serverPort){
        /*
        * User Interface Look 
        */
        try {
            String acrylTemplate = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
            String hifiTemplate = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
            UIManager.setLookAndFeel(acrylTemplate);
        } 
        catch (Exception e) {
                System.err.println("Look and feel not set.");
        }
        try {
            Login window = new Login(serverHost, serverPort);
            window.MainFormLogin.setVisible(true);
        } 
        catch (Exception e) {
                e.printStackTrace();
        }
    }
    public static void RunServer(int serverPort){
        try {
                RunServer server = new RunServer(serverPort);
        }
        catch(Exception e){

        }
    }
}
