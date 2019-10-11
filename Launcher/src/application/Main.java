package application;


import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


import application.fr.maxence.enerlia.launcher.Session;
import application.fr.maxence.enerlia.launcher.pages.HomePage;
import application.fr.maxence.enerlia.launcher.pages.LoginPage;
import application.fr.maxence.enerlia.launcher.pages.Page;
import application.fr.maxence.enerlia.launcher.pages.components.Console;
import application.fr.maxence.enerlia.launcher.utils.notification.UpdateStartNotification;
import fr.northenflo.auth.mineweb.mc.MinewebGameType;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import fr.theshark34.openlauncherlib.minecraft.GameInfos;
import fr.theshark34.openlauncherlib.minecraft.GameVersion;
import fr.theshark34.openlauncherlib.minecraft.MinecraftLauncher;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.BadServerVersionException;
import fr.theshark34.supdate.exception.ServerDisabledException;
import fr.theshark34.supdate.exception.ServerMissingSomethingException;
import fr.theshark34.swinger.Swinger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

	private Canvas canvas;
	private static Main instance;
	private Page activePage;
	private Group root;
	private Stage stage;
	private  File gameDir;
	private Console console;
	private SUpdate update = null;


	public Console getConsole() {
		return console;
	}

	public Group getRoot() {
		return root;
	}

	@Override
	public void start(Stage primaryStage) {
		try {

			console = new Console();

			console.log("Démarrage du programme");

			console.log("Insialisation des instances...");
			instance = this;

			canvas  = new Canvas(975,625);
			root = new Group();
			Scene scene = new Scene(root);

			console.log("Insialisation des instances terminé");


			root.getChildren().add(canvas);

			console.log("Ajout du canvas");



			console.log("Changement de la scène de la fenêtre...");

			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);

			console.log("Changement de la scène de la fenêtre terminé");

			console.log("Affichage de la page...");

			primaryStage.show();

			console.log("Affichege de la page terminés");




			primaryStage.setResizable(false);
			console.log("Fenêtre non redimensionnable : OK");

			LoginPage page = new LoginPage();



			changePage(page);
			update(primaryStage);

			primaryStage.getIcons().add(new Image("http://estalia.fr/app/webroot/supdate/supdate2/images/icon.png"));


			




		} catch(Exception e) {
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public static Main getInstance()
	{
		return instance;

	}

	public Page getActivePage() {
		return activePage;
	}

	public void setActivePage(Page activePage) {
		this.activePage = activePage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Canvas getCanvas()
	{
		return canvas;
	}

	public void update(Stage primaryStage)
	{
		primaryStage.setTitle("Estalia Launcher | By Maxence#7544 | Page : " + this.activePage.getName());
		stage = primaryStage;
	}
	public void changePage(Page page)
	{
		console.log("Changement de page...");

		console.log("Clear des composants...");

		root.getChildren().clear();
		console.log("Clear des composants terminées");

		console.log("Changement du canvas...");
		canvas = new Canvas(975,625);
		console.log("Changement du canvas terminés");

		console.log("Ajout du nouveaux Canvas...");

		root.getChildren().add(canvas);
		console.log("Ajout du nouveaux Canvas terminée !");

		console.log("Ajout de la page...");
		getRoot().getChildren().add(page.getPageGroup());
		console.log("Ajout de la page terminée !");

		console.log("Affichage de la page...");
		page.show();
		console.log("Affichage de la page terminée !");

		console.log("Changement de page terminée");

	}

	public void update(String version) throws BadServerResponseException, ServerDisabledException, BadServerVersionException, ServerMissingSomethingException, IOException 
	{
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				console.log("Début de l'update du jeu");
				console.log("Création de la notification...");

			}
		});
		UpdateStartNotification startNotification = new UpdateStartNotification();

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				console.log("Création de la notification terminées");

				console.log("Affichage de la notification...");

			}
		});
		startNotification.show();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {


				console.log("Affichage de la notification terminées");
			}
		});
		String os = System.getProperty("os.name").toLowerCase();

		if(version.equalsIgnoreCase("1.8.9")) { 

			if (os.contains("win"))
				gameDir = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\.Estalia\\1.8.9");
			else if (os.contains("mac"))
				gameDir = new File(System.getProperty("user.home") + "/Library/Application Support/Estalia/1.8.9");
			else
				gameDir = new File(System.getProperty("user.home") + "/.Estalia/1.8.9");
			update = new SUpdate("http://estalia.fr/app/webroot/customweb/Launcher/SUpdate/1.8.9", gameDir);		
			} else if(version.equalsIgnoreCase("1.9.4")) {
				if (os.contains("win"))
					gameDir = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\.Estalia\\1.9.4");
				else if (os.contains("mac"))
					gameDir = new File(System.getProperty("user.home") + "/Library/Application Support/Estalia/1.9.4");
				else
					gameDir = new File(System.getProperty("user.home") + "/.Estalia/1.9.4");
				update = new SUpdate("http://estalia.fr/app/webroot/customweb/Launcher/SUpdate/1.8.9", gameDir);	
			}


		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				console.log("Création du Thread pour le label de pourcentage !");

			}
		});
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {e.printStackTrace();}
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								HomePage.getPourcentageLabel().setText(Swinger.percentage((int) BarAPI.getNumberOfTotalDownloadedBytes() / 1000,(int) BarAPI.getNumberOfTotalBytesToDownload() / 1000) + "%");

							}
						});

					}
				}, 0, 10000L);

			}

		});

		t.start();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				console.log("Lancement du Thread pour le label de pourcentage");
				console.log("Téléchargement des fichiers");

			}
		});
		update.start();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				console.log("Téléchargement des fichier terminés");


			}
		});
		t.interrupt();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				console.log("interruption du Thread pour le label de pourcentage !");
				HomePage.getPourcentageLabel().setText("Lancement du jeu !");

			}
		});


	}

	public void launch() throws LaunchException, InterruptedException
	{
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				console.log("Lancement du jeu...");

			}
		});
		GameVersion version = new GameVersion(Session.getVersion(), MinewebGameType.V1_8_HIGHER);
		GameInfos infos = new GameInfos("Estalia", version, null);
		GameFolder folder = new GameFolder(Session.getVersion() + "/assets", Session.getVersion() + "/libs", Session.getVersion() + "/natives", Session.getVersion() + "/minecraft.jar");
		

		ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(infos, folder, new AuthInfos("null", "null", "null"));
		ExternalLauncher launcher = new ExternalLauncher(profile);

		Process p = launcher.launch();
		Thread.sleep(5000);

		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				getStage().close();

			}
		});

		p.waitFor();
		System.exit(0);
	}



}







