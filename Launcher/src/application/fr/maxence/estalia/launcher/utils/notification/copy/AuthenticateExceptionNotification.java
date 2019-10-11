package application.fr.maxence.enerlia.launcher.utils.notification.copy;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

public class AuthenticateExceptionNotification implements Notification{

	@Override
	public void show() {
		//Obtain only one instance of the SystemTray object
				SystemTray tray = SystemTray.getSystemTray();
				//If the icon is a file
				Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
				//Alternative (if the icon is on the classpath):
				//Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
				TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
				//Let the system resize the image if needed
				trayIcon.setImageAutoSize(true);
				//Set tooltip text for the tray icon
				trayIcon.setToolTip("System tray icon demo");
				try {
					tray.add(trayIcon);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				trayIcon.displayMessage("Estalia Launcher", "Erreur d'athentification", MessageType.ERROR);
			}
		
	}


