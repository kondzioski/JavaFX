package application;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class ButtonController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonSend;

    @FXML
    private MenuBar Menu;

    @FXML
    private TextArea Receiver;

    @FXML
    private TextArea Sender;
    
    @FXML
    private ImageView Colorize;
    
    private Scene scene;

    public void initialize() {
        assert ButtonSend != null : "fx:id=\"ButtonSend\" was not injected: check your FXML file 'UserInterface.fxml'.";
        assert Menu != null : "fx:id=\"Menu\" was not injected: check your FXML file 'UserInterface.fxml'.";
        assert Receiver != null : "fx:id=\"Receiver\" was not injected: check your FXML file 'UserInterface.fxml'.";
        assert Sender != null : "fx:id=\"Sender\" was not injected: check your FXML file 'UserInterface.fxml'.";
        assert Colorize != null: "fx:id=\"Colorize\" was not injected: check your FXML file 'UserInterface.fxml'.";

    }
    
    @FXML
    void buttonClicked(){
    	Receiver.setText(Sender.getText());
    	System.out.println(Sender.getText());
    }
    
    @FXML
    void menuFileClose(){
    	Platform.exit();
    }
    
    @FXML
    void menuEditDelete(){
    	Receiver.setText(null);
    	Sender.setText(null);
    }
    
    @FXML
    void ColorizedPressed(){
    	System.out.println("dupa");
    }
    
    @FXML
    void ColorizedMouseOver(){
    	Colorize.setEffect(null);
    }
    
    @FXML
    void ColorizedMouseGone() throws InterruptedException{
    	 Thread.sleep(150);
    	 MotionBlur motionBlur = new MotionBlur();
    	 motionBlur.setRadius(15);
    	 motionBlur.setAngle(60);

    	Colorize.setEffect(motionBlur);
    }
    
    @FXML
    void showHelpWindow() throws IOException {
    	
    	Stage helpWindowStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("HelpWindow.fxml"));
    	scene = new Scene(root);
		helpWindowStage.setScene(scene);
		helpWindowStage.show();

		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Dupa");
			}
		});

    }
    
    @FXML
    void sendMail() throws AddressException, MessagingException{
    	
    	Properties props = (Properties)System.getProperties().clone();
    	props.put("mail.smtps.auth", "true");
        // set as properties as needed
        Session session = Session.getInstance(props, null);
        
        String host = "smtp.gmail.com";
        String username = "kalisciak";
        String password = "m4x8ijz123ab";
        // ...
        Transport t = session.getTransport("smtps");
        
        Message msg = new MimeMessage(session);
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("kalisciak@gmail.com"));
        msg.setSubject("Testing Subject");
        msg.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");
        
        try {
			t.connect(host, username, password);
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {}

}