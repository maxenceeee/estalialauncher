package application.fr.maxence.estalia.launcher.pages.components;

import java.util.Calendar;
import java.util.Locale;

import javafx.scene.control.TextArea;

public class Console extends TextArea{

	
	public Console()  {
	}


	public void log(String text)
	{
			Calendar calendrier = Calendar.getInstance(new Locale(Locale.FRENCH.toString(), Locale.FRANCE.toString()));
			this.setText(this.getText() +"\n" + "[" + calendrier.getTime() + "]" + text);
			text = this.getText();
			return;
		
	}





}
