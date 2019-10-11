package application.fr.maxence.enerlia.launcher.pages;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.Main;
import application.fr.maxence.enerlia.launcher.Session;
import application.fr.maxence.enerlia.launcher.utils.notification.UpdateErrorNotification;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.BadServerVersionException;
import fr.theshark34.supdate.exception.ServerDisabledException;
import fr.theshark34.supdate.exception.ServerMissingSomethingException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HomePage extends Page{



	private static Group group = new Group();
	private static Image background = new Image("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/background.png");

	//components
	private Button hideButton;
	private Button quitButton;
	private Button consoleButton;
	private Button homeButton;
	private Button newsButton;
	private Button settingButton;
	private Button serverIcon;
	private Button playButton;
	private static Label pourcentageLabel = new Label("Clique sur jouer !");;
	private ComboBox<?> multiversion;

	public HomePage() {
		super("Home", group, background);


		quitButton = new Button();
		quitButton.setLayoutX(922);quitButton.setLayoutY(12);
		quitButton.setMinSize(38, 36);
		quitButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/quit.png"));
		quitButton.setCursor(Cursor.HAND);
		quitButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(quitButton);

		hideButton = new Button();
		hideButton.setLayoutX(874);hideButton.setLayoutY(13);
		hideButton.resize(38, 36);
		hideButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/hide.png"));
		hideButton.setCursor(Cursor.HAND);
		hideButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(hideButton);

		consoleButton = new Button();
		consoleButton.setLayoutX(390);consoleButton.setLayoutY(30);
		consoleButton.resize(153, 39);
		consoleButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/Console.png"));
		consoleButton.setCursor(Cursor.HAND);
		consoleButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(consoleButton);

		homeButton = new Button();
		homeButton.setLayoutX(27);homeButton.setLayoutY(30);
		homeButton.resize(153, 39);
		homeButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/Accueil.png"));
		homeButton.setCursor(Cursor.HAND);
		homeButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(homeButton);

		newsButton = new Button();
		newsButton.setLayoutX(205);newsButton.setLayoutY(30);
		newsButton.resize(156, 39);
		newsButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2//Actualit%C3%A9s.png"));
		newsButton.setBackground(Background.EMPTY);
		newsButton.setCursor(Cursor.HAND);
		getPageGroup().getChildren().add(newsButton);

		settingButton = new Button();
		settingButton.setLayoutX(568);settingButton.setLayoutY(30);
		settingButton.resize(174, 41);
		settingButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/settings.png"));
		settingButton.setCursor(Cursor.HAND);
		settingButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(settingButton);

		serverIcon = new Button();
		serverIcon.setLayoutX(111);serverIcon.setLayoutY(113);
		serverIcon.resize(318, 288);
		serverIcon.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/icon.png"));
		serverIcon.setBackground(Background.EMPTY);
		serverIcon.setCursor(Cursor.HAND);
		getPageGroup().getChildren().add(serverIcon);

		playButton = new Button();
		playButton.setLayoutX(697);playButton.setLayoutY(503);
		playButton.resize(195, 64);
		playButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page2/Jouer.png"));
		playButton.setCursor(Cursor.HAND);
		playButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(playButton);

		pourcentageLabel.setLayoutX(698);pourcentageLabel.setLayoutY(424);
		pourcentageLabel.resize(190, 74);
		pourcentageLabel.setFont(Font.font(30));
		pourcentageLabel.setAlignment(Pos.CENTER);
		pourcentageLabel.setTextFill(Color.WHITE);
		getPageGroup().getChildren().add(pourcentageLabel);

		

		
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "1.8.9",
			        "1.9.4",
			        "1.12.2"
			    );
		
		multiversion = new ComboBox<String>(options);
		multiversion.setLayoutX(573);multiversion.setLayoutY(521);
		multiversion.resize(112, 10);
		getPageGroup().getChildren().add(multiversion);
		
		
		
		//events

		quitButton.setOnAction((ActionEvent e) -> {
			Platform.exit();
			System.exit(0);
		});


		hideButton.setOnAction((ActionEvent e) -> {
			Main.getInstance().getStage().setIconified(true);
		});

		consoleButton.setOnAction((ActionEvent e) -> {

			Main.getInstance().changePage(new ConsolePage());
			Main.getInstance().update(Main.getInstance().getStage());
		});

		serverIcon.setOnAction((ActionEvent e) -> {
			try {
				Desktop.getDesktop().browse(new URI("http://estalia.fr/"));
			} catch (IOException | URISyntaxException e1) {
				e1.printStackTrace();
			};
		});

		playButton.setOnAction((ActionEvent e) -> {

			playButton.setDisable(true);
			try {
				Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {

						try {
							Main.getInstance().update(Session.getVersion());

							Main.getInstance().launch();
						} catch (BadServerResponseException | ServerDisabledException | BadServerVersionException
								| ServerMissingSomethingException | IOException | LaunchException | InterruptedException e) {
							e.printStackTrace();
						}

							
						
					}
				});
				thread.start();
			} catch (Exception e1) {
				UpdateErrorNotification notif = new UpdateErrorNotification();
				notif.show();
				playButton.setDisable(false);
			}
		});
		
		multiversion.setOnAction((ActionEvent e) -> {
			@SuppressWarnings("rawtypes")
			ComboBox box = (ComboBox) e.getSource();
			Session.setVersion((String) box.getSelectionModel().getSelectedItem());
			System.out.println((String) box.getSelectionModel().getSelectedItem());
		});


	}

	public static Label getPourcentageLabel() {
		return pourcentageLabel;
	}



}
