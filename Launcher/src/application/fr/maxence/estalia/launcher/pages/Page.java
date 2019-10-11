package application.fr.maxence.enerlia.launcher.pages;

import application.Main;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Page {
	
	private String name;
	private Group pageGroup;
	private Image background;
	private GraphicsContext gc;
	
	public GraphicsContext getGc() {
		return gc;
	}

	public String getName() {
		return name;
	}

	public Group getPageGroup() {
		return pageGroup;
	}

	public Image getBackground() {
		return background;
	}

	public Page(String name, Group pageGroup, Image background) {
		super();
		this.name = name;
		this.pageGroup = pageGroup;
		this.background = background;
	}
	
	
	public void show()
	{
		gc = Main.getInstance().getCanvas().getGraphicsContext2D();
		gc.drawImage(getBackground(), 0, 0);
		Main.getInstance().setActivePage(this);
	}
	
	
	
	
}
