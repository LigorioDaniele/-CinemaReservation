package Server;

import Common.BaseItems.FilmsItemList;
import Common.BaseItems.PalimpsestItemList;
import Common.BaseItems.SeatedItemList;
import Common.BaseItems.TimeItemList;
import Common.BaseItems.UserItem;
import SQL.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import XML.BaseXMLCommand;
import XML.Command;
import java.util.ArrayList;

public class RunServer extends Thread {
    ServerSocket Server;
    public SQLConnect _sqlConn = null;

    public RunServer(int port) throws Exception {
            Server = new ServerSocket(port);
            _sqlConn = new SQLConnect();
            if(_sqlConn.init()){
                    System.out.println("Server avviato sulla porta "+port);
                    this.start();
            }
            else {
                    System.out.println("Errore di connessione al server SQL!");
            }
    }
    public void run() {
        while (true) {
            try {
                System.out.println("In attesa dei Client...");
                Socket client = Server.accept();
                System.out.println("Connessione avvenuta con: "+ client.getInetAddress());
                ConnectClient c = new ConnectClient(client,_sqlConn);

            } catch (Exception e) {
                System.exit(1);
            }
        }
    }
}

class ConnectClient extends Thread {
    Socket client = null;
    SQLConnect _sqlConn;
    BufferedReader input = null;
    PrintStream output = null;
    public ConnectClient(Socket clientSocket, SQLConnect sqlConn) {
            this.client = clientSocket;
            this._sqlConn = sqlConn; 
            this.start();
    }
    public void run() {
        String line ="";
        String command = "";
        boolean ciclo = true;
        try {
            input = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
            output = new PrintStream(client.getOutputStream(), true);
            String [] argumentArray;
            String response;
            while(true){
                line = input.readLine();
                if(line != null){
                    BaseXMLCommand<String, String,  Object> commandResponse = Command.Decoder(line);
                    switch(commandResponse.getKey()){
                        case "Login":
                            UserItem userItemInput = (UserItem)commandResponse.getValue();
                            /*
                            * Funzione che controlla i dati di accesso dell'amministratore!
                            */
                            UserItem userItemOutput = UsersDAL.CheckUserLoginByUsername_Password(userItemInput.getUsername(), userItemInput.getPassword(), _sqlConn);
                            //Se l'utente viene trovato sul DB, ritorna l'oggetto dell'utente indietro. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("Login", null, userItemOutput));
                            output.println(response);
                            break;
                        case "GetFilmList":
                            /*
                            * Funzione ritorna la lista dei film disponibili
                            */
                            ArrayList<FilmsItemList> filmItemList = FilmsDAL.GetFilmsList(_sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetFilmList", null, filmItemList));
                            output.println(response);
                            break;
                        case "GetFilmidByTitle":
                            int filmId = FilmsDAL.GetFilmidByTitle(commandResponse.getValue().toString(), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetFilmidByTitle", null, filmId + ""));
                            output.println(response);
                            break;
                        case "GetSeatedList":
                            /*
                            * Funzione ritorna la lista delle prenotazioni per utente e film
                            */
                            argumentArray = commandResponse.getArgument().toString().split("###");
                            ArrayList<SeatedItemList> seatedItemList = SeatedDAL.GetSeatedList(Integer.parseInt(argumentArray[0]),Integer.parseInt(argumentArray[1]), Integer.parseInt(argumentArray[2]), argumentArray[3], Integer.parseInt(argumentArray[4]), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetSeatedList", null, seatedItemList));
                            output.println(response);
                            break;
                        case "UpdateSeatedByUser":
                            boolean seatedItemListToInsert = SeatedDAL.SetSeatedListByUserId(((SeatedItemList)commandResponse.getValue()), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("UpdateSeatedListStatus", null, seatedItemListToInsert + ""));
                            output.println(response);
                            break;
                        case "DeleteSeatedByUser":
                            boolean seatedItemListToDelete = SeatedDAL.DeleteSeatedListByUserId(((SeatedItemList)commandResponse.getValue()), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("DeleteSeatedListStatus", null, seatedItemListToDelete + ""));
                            output.println(response);
                            break;
                        case "GetTimeByDay":
                            ArrayList<TimeItemList> timeItemListByDay = PalimpsestDAL.GetTimeByDay(commandResponse.getArgument().toString(), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetSeatedList", null, timeItemListByDay));
                            output.println(response);
                            break;
                        case "GetFilmByDay":
                            ArrayList<FilmsItemList> filmItemListByDay = FilmsDAL.GetFilmsListByDay(commandResponse.getArgument().toString(), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetFilmList", null, filmItemListByDay));
                            output.println(response);
                            break;
                        case "CreateFilm":
                            boolean filmItemListToInsert = FilmsDAL.CreateFilm(((FilmsItemList)commandResponse.getValue()), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("CreateFilm", null, filmItemListToInsert + ""));
                            output.println(response);
                            break;
                        case "UpdateFilmByTitle":
                            boolean filmItemListToUpdate = FilmsDAL.UpdateFilmByTitle(((FilmsItemList)commandResponse.getValue()), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("UpdateFilmByTitle", null, filmItemListToUpdate + ""));
                            output.println(response);
                            break;
                        case "DeleteFilmFilmByTitle":
                            boolean filmItemListToDelete = FilmsDAL.DeleteFilmFilmByTitle(((FilmsItemList)commandResponse.getValue()), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("DeleteFilmFilmByTitle", null, filmItemListToDelete + ""));
                            output.println(response);
                            break;
                        case "GetPalinsestByFilmId":
                            ArrayList<PalimpsestItemList> PalimpsestByFilmId = PalimpsestDAL.GetPalimpsestByFilmId(commandResponse.getValue().toString(), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetPalinsestByFilmId", null, PalimpsestByFilmId));
                            output.println(response);
                            break;
                        case "GetPalimpsestByday_time_film_id":
                            argumentArray = commandResponse.getArgument().toString().split("###");
                            PalimpsestItemList PalimpsestByday_time_film_id = PalimpsestDAL.GetPalimpsestByday_time_film_id(argumentArray[0], argumentArray[1], argumentArray[2], _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("GetPalimpsestByday_time_film_id", null, PalimpsestByday_time_film_id));
                            output.println(response);
                            break;
                        case "SetPalimpsest":
                            boolean setPalimpsestResult = PalimpsestDAL.SetPalimpsest((PalimpsestItemList)commandResponse.getValue(), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("SetPalimpsest", null, setPalimpsestResult + ""));
                            output.println(response);
                            break;
                        case "DeletePalimpsest":
                            boolean deletePalimpsestResult = PalimpsestDAL.DeletePalimpsest((PalimpsestItemList)commandResponse.getValue(), _sqlConn); 
                            //Se la lista di film viene troata sul DB, ritorna l'oggetto. In caso contrario ritorna l'oggetto == null
                            response = Command.Encoder(new BaseXMLCommand("DeletePalimpsest", null, deletePalimpsestResult + ""));
                            output.println(response);
                            break;
                        case "GetUserItemList":
                            ArrayList<UserItem> userItemList = UsersDAL.GetUserItemList( _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("GetUserItemList", null, userItemList));
                            output.println(response);
                            break;
                        case "CreateUser":
                            boolean resultCreateUser = UsersDAL.CreateUser((UserItem)commandResponse.getValue(), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("CreateUser", null, resultCreateUser + ""));
                            output.println(response);
                            break;
                        case "UpdateUserByUsername":
                            boolean resultUpdateUserByUsername = UsersDAL.UpdateUserByUsername((UserItem)commandResponse.getValue(), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("UpdateUserByUsername", null, resultUpdateUserByUsername + ""));
                            output.println(response);
                            break;
                        case "DeleteUserByUsername":
                            boolean resultDeleteUserByUsername = UsersDAL.DeleteUserByUsername((UserItem)commandResponse.getValue(), _sqlConn); 
                            response = Command.Encoder(new BaseXMLCommand("DeleteUserByUsername", null, resultDeleteUserByUsername + ""));
                            output.println(response);
                            break;
                    }
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Connessione interrotta con il client "+client.getInetAddress());
            try {
                    output.close();
                    input.close();
                    client.close();
            }
            catch(IOException io){}
        }
    }
}
