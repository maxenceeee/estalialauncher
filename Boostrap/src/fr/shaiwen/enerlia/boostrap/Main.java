package fr.shaiwen.enerlia.boostrap;
	

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import fr.theshark34.openlauncherlib.bootstrap.Bootstrap;
import fr.theshark34.openlauncherlib.bootstrap.LauncherClasspath;
import fr.theshark34.openlauncherlib.bootstrap.LauncherInfos;
import fr.theshark34.openlauncherlib.util.ErrorUtil;
import fr.theshark34.openlauncherlib.util.GameDir;
import fr.theshark34.openlauncherlib.util.SplashScreen;
import fr.theshark34.supdate.BarAPI;
import fr.theshark34.supdate.SUpdate;
import fr.theshark34.supdate.exception.BadServerResponseException;
import fr.theshark34.supdate.exception.BadServerVersionException;
import fr.theshark34.supdate.exception.ServerDisabledException;
import fr.theshark34.supdate.exception.ServerMissingSomethingException;
import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.colored.SColoredBar;


public class Main  {
	
	private static SplashScreen splashScreen;
	private static SColoredBar bar;
	private static Thread barThread;
	
	
	private static final LauncherInfos INFOS = new LauncherInfos("Estalia", "application.Main");
	private static final File DIR = GameDir.createGameDir("Estalia");
	private static final LauncherClasspath CLASSPATH = new LauncherClasspath(new File(DIR, "launcher/launcher.jar"),new File(DIR,"launcher/libs/"));
	
	private static ErrorUtil error = new ErrorUtil(new File(DIR,"crashes/"));
	
	public static void main(String[] args) throws MalformedURLException {
		Swinger.setResourcePath("/fr/shaiwen/enerlia/boostrap/ressources");
		displaySplash();
		try {
			doUpdate();
		} catch (BadServerResponseException | ServerDisabledException | BadServerVersionException
				| ServerMissingSomethingException | IOException e) {
			barThread.interrupt();
			error.catchError(e, "Mise à Jour impossible !");
		}
		try {
			launchLauncher();
		} catch (IOException e) {
			error.catchError(e, "Lancement impossible");
		}
	}
	
	public static void displaySplash() throws MalformedURLException
	{
		
		splashScreen = new SplashScreen("Estalia Launcher | Boostrap", Swinger.getResource("bootstrap.png"));
		bar = new SColoredBar(Swinger.getTransparentWhite(100),Swinger.getTransparentWhite(175));
		bar.setBounds(0, 605, 975, 20);
		splashScreen.add(bar);
		
		splashScreen.setVisible(true);
	}
	
	public static void doUpdate() throws BadServerResponseException, ServerDisabledException, BadServerVersionException, ServerMissingSomethingException, IOException
	{
		SUpdate su = new SUpdate("http://estalia.fr/app/webroot/customweb/Launcher/SUpdate/bootsrap/",new File(DIR,"launcher/"));
		barThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!barThread.isInterrupted()) {
					bar.setValue((int) BarAPI.getNumberOfTotalDownloadedBytes() / 1000);
					bar.setValue((int) BarAPI.getNumberOfTotalBytesToDownload() / 1000);
				}
				
			}
		});
		barThread.start();
		su.start();
		barThread.interrupt();
		
	}
	
	public static void launchLauncher() throws IOException
	{
		Bootstrap bootstrap = new Bootstrap(CLASSPATH, INFOS);
		Process p = bootstrap.launch();
		
		splashScreen.setVisible(false);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
		}
		
		System.exit(0);
	}
}
