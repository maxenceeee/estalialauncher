package application.fr.maxence.enerlia.launcher;

public class Session {

	private static String version = "1.8.9";
	private static String pseudo;

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		Session.version = version;
	}

	public static String getPseudo() {
		return pseudo;
	}

	public static void setPseudo(String pseudo) {
		Session.pseudo = pseudo;
	}

}
