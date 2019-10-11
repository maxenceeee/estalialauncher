package application.fr.maxence.enerlia.launcher.pages;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;

public class ConsolePage extends Page{

	
	
	private static Group group = new Group();
	private static Image background = new Image("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/background.png");
	
	//components
			private Button hideButton;
			private Button quitButton;
			private Button consoleButton;
			private Button homeButton;
			private Button newsButton;
			private Button settingButton;
			
			private TextArea console;
	
	public ConsolePage() {
		super("Console", group, background);
		
		quitButton = new Button();
		quitButton.setLayoutX(922);quitButton.setLayoutY(12);
		quitButton.setMinSize(38, 36);
		quitButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/quit.png"));
		quitButton.setCursor(Cursor.HAND);
		quitButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(quitButton);

		hideButton = new Button();
		hideButton.setLayoutX(874);hideButton.setLayoutY(13);
		hideButton.resize(38, 36);
		hideButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/hide.png"));
		hideButton.setCursor(Cursor.HAND);
		hideButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(hideButton);

		consoleButton = new Button();
		consoleButton.setLayoutX(390);consoleButton.setLayoutY(30);
		consoleButton.resize(153, 39);
		consoleButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/Console.png"));
		consoleButton.setCursor(Cursor.HAND);
		consoleButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(consoleButton);

		homeButton = new Button();
		homeButton.setLayoutX(27);homeButton.setLayoutY(30);
		homeButton.resize(153, 39);
		homeButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/Accueil.png"));
		homeButton.setCursor(Cursor.HAND);
		homeButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(homeButton);

		newsButton = new Button();
		newsButton.setLayoutX(205);newsButton.setLayoutY(30);
		newsButton.resize(156, 39);
		newsButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/Actualit%C3%A9s.png"));
		newsButton.setBackground(Background.EMPTY);
		newsButton.setCursor(Cursor.HAND);
		getPageGroup().getChildren().add(newsButton);

		settingButton = new Button();
		settingButton.setLayoutX(568);settingButton.setLayoutY(30);
		settingButton.resize(174, 41);
		settingButton.setGraphic(new ImageView("http://estalia.fr/app/webroot/customweb/Launcher/LauncherImg/Page4/settings.png"));
		settingButton.setCursor(Cursor.HAND);
		settingButton.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(settingButton);
		
		console = Main.getInstance().getConsole();
		console.setLayoutX(25);console.setLayoutY(146);
		console.setMinSize(923, 456);
		console.setDisable(false);
		console.setBorder(Border.EMPTY);
		console.setBackground(Background.EMPTY);
		getPageGroup().getChildren().add(console);
		
		//events
		
		quitButton.setOnAction((ActionEvent e) -> {
			Platform.exit();
			System.exit(0);
		});


		hideButton.setOnAction((ActionEvent e) -> {
			Main.getInstance().getStage().setIconified(true);
		});
		
		homeButton.setOnAction((ActionEvent e) -> {
			Main.getInstance().changePage(new HomePage());
			Main.getInstance().update(Main.getInstance().getStage());
		});

		
	}

}
