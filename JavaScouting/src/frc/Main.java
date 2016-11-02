package frc;

import javafx.stage.*;
import javafx.application.Application;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setupDB();
		loadInterface(primaryStage);
		runSystem();
	}
	
	void setupDB() {
		ScoutQuerier.startConnection();
		ScoutQuerier.queryDB("USE frcscout2016;");
	}
	
	void loadInterface(Stage primaryStage) {
		ScoutInterface.load(primaryStage);
	}
	
	void runSystem() {
		ScoutInterface.run();
	}
}
