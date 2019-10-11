package application.fr.maxence.enerlia.launcher.pages;



import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.Main;
import application.fr.maxence.enerlia.launcher.Session;
import application.fr.maxence.enerlia.launcher.utils.notification.AuthenticateExceptionNotification;
import fr.northenflo.auth.exception.AccountSuspendException;
import fr.northenflo.auth.exception.DataEmptyException;
import fr.northenflo.auth.exception.DataWrongException;
import fr.northenflo.auth.exception.RequireGAuthException;
import fr.northenflo.auth.exception.ServerNotFoundException;
import fr.northenflo.auth.mineweb.AuthMineweb;
import fr.northenflo.auth.mineweb.utils.Get;
import fr.northenflo.auth.mineweb.utils.TypeConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

public class LoginPage extends Page{

	

	private static Group group = new Group();
	private static Image background = new Image("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page1/background.png");
	//component
	private TextField pseudoField;
	private PasswordField passwordField;
	private Button connectionButton;
	private Button quitButton;
	private Button hideButton;
	private Button serverButton;
	
	private boolean mouseClicked;
	
	
	public LoginPage() {
		
		super("Login", group, background);

		group.setOnMousePressed((MouseEvent e) -> {
			mouseClicked = true;
		});
		
		group.setOnMouseReleased((MouseEvent e) -> {
			mouseClicked = false;
		});
		
		group.setOnMouseMoved((MouseEvent e) -> {
			if(!mouseClicked) return;
			Main.getInstance().getStage().setX(e.getX());
			Main.getInstance().getStage().setY(e.getY());
		});
		
		
		pseudoField = new TextField("Pseudo");
		pseudoField.setLayoutX(560); pseudoField.setLayoutY(188);
		pseudoField.setMinWidth(325);
		pseudoField.setBackground(Background.EMPTY);
		pseudoField.setFont(Font.font(20));
		pseudoField.setStyle("-fx-text-inner-color: white;");
		getPageGroup().getChildren().add(pseudoField);
		
		passwordField = new PasswordField();
		passwordField.setLayoutX(560); passwordField.setLayoutY(370);
		passwordField.setMinWidth(325);
		passwordField.setBackground(Background.EMPTY);
		passwordField.setFont(Font.font(20));
		passwordField.setStyle("-fx-text-inner-color: white;");
		getPageGroup().getChildren().add(passwordField);
		
		connectionButton = new Button();
		connectionButton.setLayoutX(566);connectionButton.setLayoutY(495);
		connectionButton.setMinWidth(331);connectionButton.setMinHeight(61);
		connectionButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page1/Connection.png"));
		connectionButton.setCursor(Cursor.HAND);
		connectionButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(connectionButton);
		
		quitButton = new Button();
		quitButton.setLayoutX(922);quitButton.setLayoutY(12);
		quitButton.setCursor(Cursor.HAND);
		quitButton.setMinSize(38, 36);
		quitButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page1/quit.png"));
		quitButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(quitButton);
		
		hideButton = new Button();
		hideButton.setLayoutX(874);hideButton.setLayoutY(13);
		hideButton.setCursor(Cursor.HAND);
		hideButton.resize(38, 36);
		hideButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page1/hide.png"));
		hideButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(hideButton);

		
		serverButton = new Button();
		serverButton.setLayoutX(106);serverButton.setLayoutY(168);
		serverButton.setCursor(Cursor.HAND);
		serverButton.resize(318, 288);
		serverButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page1/icon.png"));
		serverButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(serverButton);
		
		//Event
		connectionButton.setOnAction((ActionEvent e) ->{
			AuthMineweb.setTypeConnection(TypeConnection.launcher);
			AuthMineweb.setUrlRoot("http://estalia.fr/");
			AuthMineweb.setUsername(pseudoField.getText());
			AuthMineweb.setPassword(passwordField.getText());
			try {
				AuthMineweb.start();
			} catch (AccountSuspendException | DataWrongException | DataEmptyException | IOException
					| ServerNotFoundException | RequireGAuthException e1) {
				AuthenticateExceptionNotification error = new AuthenticateExceptionNotification();
				error.show();
            }
				
			
			if(AuthMineweb.isConnected())
			{
						Session.setPseudo(Get.getSession.getUsername());
						Main.getInstance().changePage(new HomePage());
						Main.getInstance().update(Main.getInstance().getStage());
			}
		
		});
		
		quitButton.setOnAction((ActionEvent e) -> {
			Platform.exit();
			System.exit(0);
		});
		
		hideButton.setOnAction((ActionEvent e) -> {
			Main.getInstance().getStage().setIconified(true);
		});
		
		serverButton.setOnAction((ActionEvent e) -> {
			try {
                Desktop.getDesktop().browse(new URI("http://estalia.fr/"));
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
		});
		
		serverButton.setOnMouseMoved((MouseEvent e) -> {
				serverButton.setTranslateX((serverButton.getLayoutX() - e.getX()) / 4);
				serverButton.setTranslateY((serverButton.getLayoutY() - e.getY()) / 4);
			
		});
		
	}


	
}
