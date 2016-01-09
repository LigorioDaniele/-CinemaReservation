package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class RunClient {
    String user,pass,_serverHost;
    int _serverPort;
    Socket _socket;

    BufferedReader _input;
    PrintStream _output;
    Login l;
    public RunClient(String serverHost,  int serverPort){
            this._serverHost = serverHost;
            this._serverPort = serverPort;
            try {
                // open a socket connection
                _socket = new Socket(_serverHost, _serverPort);
                // Apre i canali I/O
                _input = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
                _output = new PrintStream(_socket.getOutputStream(), true);
            }
            catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Server non trovato!", "Server Error",  JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
    }
    public void sendQuery(String query){
        _output.println(query);
    }
    public String getResponse(){
        String res = null;
        try {
            res = _input.readLine(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
