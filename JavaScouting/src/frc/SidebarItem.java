package frc;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SidebarItem extends StackPane {
	private int ID;
	private String[] txt;
	private Rectangle rect;
	
	public SidebarItem(double posX, double posY, String... t) {
		ID = ScoutInterface.sidebarItems.size();
		txt = t;
		rect = new Rectangle(55, 55);
		rect.setFill(Color.WHITE);
		this.getChildren().add(rect);
		for(String s : t) {
			Text text = new Text(s);
			if(!s.equals("X")) {
				text.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 48));
			}
			else {
				text.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, 56));
			}
			this.getChildren().add(text);
		}
		this.setOnMouseClicked(e -> {
			for(SidebarItem item : ScoutInterface.sidebarItems) {
				if(item.getID() != ID) {
					item.setFill(Color.WHITE);
				}
				else {
					item.setFill(Color.CHARTREUSE);
				}
			}
			e.consume();
			ScoutInterface.selected = ID;
		});
		this.setTranslateX(posX);
		this.setTranslateY(posY);
		this.setMaxHeight(75);
		this.setMaxWidth(75);
		ScoutInterface.sidebarItems.add(this);
	}
	
	public String[] getText() {
		return txt;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setFill(Color c) {
		rect.setFill(c);
	}
}
