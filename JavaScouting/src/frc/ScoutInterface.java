package frc;

import javafx.stage.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScoutInterface {
	private static Stage stage;
	private static StackPane layout;
	private static Scene scene;
	public static int selected = -1;
	public static ArrayList<SidebarItem> sidebarItems = new ArrayList<SidebarItem>(0);
	
	/**
	 * Loads the front-end for the interface.
	 * Mainly displays the sidebar and field image.
	 */
	public static void load(Stage primaryStage) {
		stage = primaryStage;
		
		// Load field image in center
		Image field = new Image(Config.getFieldURL());
		ImageView fieldView = new ImageView(field);
		fieldView.setX(400);
		fieldView.setY(200);
		fieldView.setPreserveRatio(true);
		fieldView.setOnMouseClicked(e -> {
			double x = e.getSceneX();
			double y = e.getSceneY();
			if(selected >= 0) {
				String[] text = sidebarItems.get(selected).getText();
				StackPane stackPane = new StackPane();
				stackPane.setMaxSize(55, 55);
				stackPane.setTranslateX(x - (layout.getWidth() / 2));
				stackPane.setTranslateY(y - (layout.getHeight() / 2));
				for(String s : text) {
					Text t = new Text(s);
					if(!s.equals("X")) {
						t.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 48));
					}
					else {
						t.setFont(Font.font("Arial", FontWeight.EXTRA_LIGHT, 56));
					}
					stackPane.getChildren().add(t);
				}
				layout.getChildren().add(stackPane);
			}
			e.consume();
		});
		
		// Load sidebar
		String[][] sidebarConfig = Config.getSidebarConfig();
		for(int i = 0; i < sidebarConfig.length; i++) {
			int x = (int) (-25 * Math.cos(i * Math.PI) + 575); // Swaps 550 and 600 every iteration
			int y = 50 * (i / 2) - 250; // Adds 50 every other iteration
			new SidebarItem(x, y, sidebarConfig[i]);
		}
		
		layout = new StackPane(fieldView);
		layout.getChildren().addAll(sidebarItems);
		scene = new Scene(layout, 1400, 700); // Should be close to max resolution anyway
		scene.setFill(Color.WHITE);
		stage.setTitle("FRC Scouting System");
		stage.setScene(scene);
		stage.setMaximized(true); // Will make sure of max resolution
		stage.setOnCloseRequest(e -> {
			ScoutQuerier.closeConnection();
		});
		stage.show();
		

		Button button = new Button("End Match");
		button.setTranslateY(stage.getHeight() / 2 - 100);
		button.setOnAction(e -> {
			int hg = 0, lg = 0, hgm = 0, lgm = 0, sc = 0, ch = 0;
			for(Node n : layout.getChildren()) {
				if(n.getClass() == StackPane.class) {
					ArrayList<Text> t = new ArrayList<Text>(); 
					for(int i = 0 ; i < ((StackPane)n).getChildren().size(); i++) {
						Node m = ((StackPane)n).getChildren().get(i);
						if(m.getClass() == Text.class) {
							t.add((Text)m);
						}
					}
					if(t.get(0).getText().equals("H")) {
						if(t.size() > 1 && t.get(1).getText().equals("X")) {
							hgm++;
						}
						else {
							hg++;
						}
					}
					else if(t.get(0).getText().equals("L")) {
						if(t.size() > 1 && t.get(1).getText().equals("X")) {
							lgm++;
						}
						else {
							lg++;
						}
					}
					else if(t.get(0).getText().equals("\u0394")) {
						sc++;
					}
					else if(t.get(0).getText().equals("\u2207")) {
						ch++;
					}
				}
			}
			int cs = 5 * hg + 2 * lg + 15 * sc + 5 * ch;
			ScoutQuerier.insertDB(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
					0, 0, 0, 0, 0, 0, 0, "NA", 0, 0, 0, 0, 0, 0, 0, hg, hgm, 0, lg, lgm, 0, 0, 0, 
					0, sc, 0, ch, 0, 0, 0, 0, 0, cs);
			e.consume();
		});
		layout.getChildren().add(button);
		layout.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
	}
	
	public static void run() {
		// TODO: actually put something here that does something useful
		System.out.println("WARNING: ScoutInterface.run() not yet implemented.");
	}
}
