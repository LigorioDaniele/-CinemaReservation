package Client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Common.BaseItems.UserItem;
import XML.BaseXMLCommand;
import XML.Command;
import java.net.Socket;
import javax.swing.JPasswordField;

public class Login {
    public String _serverHost;
    public int _serverPort;
    RunClient _runCLient;
    public JFrame MainFormLogin;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public Login(String serverHost, int serverPort){
        try {
            this._serverHost = serverHost;
            this._serverPort = serverPort;
            _runCLient = new RunClient(_serverHost, _serverPort);
            initialize();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
        * Initialize the contents of the frame.
        */
    private void initialize() {

            MainFormLogin = new JFrame();
            MainFormLogin.setTitle("Gestione Cinema");
            MainFormLogin.setBounds(100, 100, 248, 151);
            MainFormLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MainFormLogin.setResizable(false);
            MainFormLogin.getContentPane().setLayout(null);

            JLabel lblInsertLoginData = new JLabel("Inserisci nome utente e password:");
            lblInsertLoginData.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblInsertLoginData.setBounds(10, 11, 274, 14);
            MainFormLogin.getContentPane().add(lblInsertLoginData);

            JLabel lblNewLabel = new JLabel("Username");
            lblNewLabel.setBounds(10, 36, 65, 14);
            MainFormLogin.getContentPane().add(lblNewLabel);

            JLabel lblPassword = new JLabel("Password");
            lblPassword.setBounds(10, 62, 65, 14);
            MainFormLogin.getContentPane().add(lblPassword);

            txtUsername = new JTextField();
            txtUsername.setBounds(67, 33, 160, 20);
            MainFormLogin.getContentPane().add(txtUsername);
            txtUsername.setColumns(10);

            txtPassword = new JPasswordField();
            txtPassword.setColumns(10);
            txtPassword.setBounds(67, 59, 160, 20);
            MainFormLogin.getContentPane().add(txtPassword);

            //TODO: Eliminare campi temponarei aggiunti per non inserire sempre user e password durante i test
            txtUsername.setText("admin");
            txtPassword.setText("admin");
            
            JButton btnLogin = new JButton("Login");
            btnLogin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    if(txtUsername.getText().length() < 2 && txtPassword.getText().length() < 2){
                        JOptionPane.showMessageDialog(MainFormLogin,
                                    "Devi inserire almeno 3 caratteri per username e password",
                                    "Errore di Login",
                                    JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        try {
                            UserItem userItem = new UserItem(txtUsername.getText(), txtPassword.getText(), false);
                            String commandStr = "Login";
                            BaseXMLCommand<String, String, UserItem> command = new BaseXMLCommand<>(commandStr, null, userItem);
                            String xmlCommand = Command.Encoder(command);
                            xmlCommand = xmlCommand;
                            _runCLient.sendQuery(xmlCommand);
                            
                            String responseStr = _runCLient.getResponse();
                            BaseXMLCommand<String, String, UserItem> commandResponse = Command.Decoder(responseStr);
                            if(commandResponse.getValue() != null){
                                OpenMainForm((UserItem)commandResponse.getValue(), _runCLient);
                            }
                            else {
                                JOptionPane.showMessageDialog(MainFormLogin,
                                            "Nome utente e password errati!",
                                            "Errore durante il Login",
                                            JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            btnLogin.setBounds(138, 90, 89, 23);
            MainFormLogin.getContentPane().add(btnLogin);
            
            JButton btnRegister = new JButton("Registrati!");
            btnRegister.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    Registration r = new Registration(_runCLient);
                }
            });
            btnRegister.setBounds(10, 90, 89, 23);
            MainFormLogin.getContentPane().add(btnRegister);
    }
    public void OpenMainForm(UserItem userItem, RunClient runCLient){
            MainForm mainForm = new MainForm(userItem, runCLient);
            MainFormLogin.setVisible(false);
            mainForm.MainForm.setVisible(true);

    }
}
