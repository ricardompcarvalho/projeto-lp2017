package ismt.application.main;

import ismt.application.scene.*;
import static javafx.geometry.HPos.RIGHT;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application implements GameInterface{

	final int APP_WIDTH = 333;
	final int APP_HEIGHT = 600;
	final int BUTTON_SIZE = 100;
	final int GAP_SIZE = 10;
	Scene sceneLogin, sceneMain, scenePlay, 
		  sceneViewStats, sceneViewPlayers, 
		  sceneViewCards, sceneViewRules, sceneViewShop; 

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("LP Clash Royale");

		// Build individual screens
		buildLoginScene(primaryStage);
		buildMainScene(primaryStage);
		scenePlay = new PlayScene().buildPlayScene(primaryStage, sceneMain);
		sceneViewStats = new StatsScene().buildStatsScene(primaryStage, sceneMain);
		sceneViewCards = new CardsScene().buildCardsScene(primaryStage, sceneMain);
		sceneViewPlayers = new PlayersScene().buildPlayersScene(primaryStage, sceneMain);
		sceneViewShop = new SceneShop().buildShopScene(primaryStage, sceneMain);
		sceneViewRules = new RulesScene().buildRulesScene(primaryStage, sceneMain);
		
		// Set initial scene for login
		primaryStage.setScene(sceneLogin);
		
		// Show application!
		primaryStage.show();
	}

	public void buildLoginScene(Stage primaryStage) {
		// Scene login
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(GAP_SIZE);
		grid.setVgap(GAP_SIZE);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Login page");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 2);

		Button buttonLogin = new Button("Sign in");
		HBox hbBtn = new HBox(BUTTON_SIZE);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(buttonLogin);
		grid.add(hbBtn, 1, 4);
		
		Button buttonExit = new Button("Quit");
		HBox hbBtnBottom = new HBox(BUTTON_SIZE);
		hbBtnBottom.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnBottom.getChildren().add(buttonExit);
		grid.add(hbBtnBottom, 2, 35);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 0, 6);
		GridPane.setColumnSpan(actiontarget, 2);
		GridPane.setHalignment(actiontarget, RIGHT);
		actiontarget.setId("actiontarget");

		sceneLogin = new Scene(grid, APP_WIDTH, APP_HEIGHT);
		sceneLogin.getStylesheets().add("resource/login.css");

		// Set button login action
		buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// if(Utils.validateUser(userTextField.getText(), pwBox.getText())){ // FIXME
				if(true){
					actiontarget.setText("Correct!");
					primaryStage.setScene(sceneMain);
				}
				else{
					actiontarget.setFill(Color.FIREBRICK);
					actiontarget.setText("Wrong credentials: " + userName.getText() + "|" + pw.getText());
				}
			}
		});
		
		// Set exit button action
		buttonExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) { 
				primaryStage.close();
			}
		});
	
	}

	public void buildMainScene(Stage primaryStage) {
		// Scene main menu
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(GAP_SIZE);

		Text scenetitle2 = new Text("Main menu");
		scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(scenetitle2, 0, 0, 2, 1);

		Button buttonPlay = new Button("Play!");
		Button buttonStats = new Button("View stats");
		Button buttonPlayers = new Button("View players");
		Button buttonCards = new Button("View cards");
		Button buttonShop = new Button("View shop");
		Button buttonRules = new Button("View rules");
		Button buttonLogout = new Button("Logout");
		buttonPlay.setMaxWidth(BUTTON_SIZE);
		buttonCards.setMaxWidth(BUTTON_SIZE);
		buttonStats.setMaxWidth(BUTTON_SIZE);
		buttonPlayers.setMaxWidth(BUTTON_SIZE);
		buttonShop.setMaxWidth(BUTTON_SIZE);
		buttonRules.setMaxWidth(BUTTON_SIZE);
		buttonLogout.setMaxWidth(BUTTON_SIZE);

		VBox vbBtn = new VBox(BUTTON_SIZE);
		vbBtn.setAlignment(Pos.BASELINE_CENTER);
		vbBtn.setSpacing(GAP_SIZE);
		vbBtn.setPadding(new Insets(0, 20, 10, 20)); 
		vbBtn.getChildren().addAll(buttonPlay, buttonStats, buttonPlayers, buttonCards, buttonShop, buttonRules, buttonLogout);
		grid2.add(vbBtn, 1, 4);

		sceneMain = new Scene(grid2, APP_WIDTH, APP_HEIGHT);
		sceneMain.getStylesheets().add("resource/style.css");

		// Set buttons action
		EventHandler<ActionEvent> buttonClickhandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (e.getSource() == buttonLogout)
					primaryStage.setScene(sceneLogin);
				else if (e.getSource() == buttonPlay)
					primaryStage.setScene(scenePlay);
				else if (e.getSource() == buttonStats)
					primaryStage.setScene(sceneViewStats);
				else if (e.getSource() == buttonPlayers)
					primaryStage.setScene(sceneViewPlayers);
				else if (e.getSource() == buttonCards)
					primaryStage.setScene(sceneViewCards);
				else if (e.getSource() == buttonShop)
					primaryStage.setScene(sceneViewShop);
				else if (e.getSource() == buttonRules)
					primaryStage.setScene(sceneViewRules);
				else
					primaryStage.setScene(sceneMain);
			}
		};

		// Associate event handler to buttons
		for(Object obj : vbBtn.getChildren().toArray())
		{
			if (obj.getClass().getTypeName() == "javafx.scene.control.Button")
				((Button)obj).setOnAction(buttonClickhandler);
		}
	}

	@Override
	public ArrayList<Card> drawRandomCards(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game createGame(ArrayList<Player> players) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String simulateGameResult(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startGame(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endGame(Game game, String result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Card> shopRandomCards() {
		// TODO Auto-generated method stub
		return null;
	}
}
