import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    Button [][] board = new Button[3][3];
    private boolean xturn = true;
    @Override
    public void start(Stage primaryStage) {
        // 1. Create UI 
        GridPane grid = new GridPane();

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setOnAction(e -> {
                    if (button.getText().isEmpty()){
                        if (xturn){
                            button.setText("X");
                        }
                        else {
                            button.setText("O");
                        }
                        xturn = !xturn;
                    }
                });
                grid.add(button, col, row);
                board[row][col] = button;
            }
        }

        if (board[0][0].getText().equals("X")  && board[0][1].getText().equals("X") && board[0][2].getText().equals("X")){
            System.out.println("Player one wins");
        }
        

        // 4. Create a scene with specific dimensions (Width, Height)
        Scene scene = new Scene(grid);

        // 5. Configure and display the main window (Stage)
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launches the JavaFX application lifecycle
        launch(args);
    }
}
