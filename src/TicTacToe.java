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
    Label winner = new Label();
    boolean gameOver = false;
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
                    if (button.getText().isEmpty() && !gameOver){
                       button.setText(xturn ? "X" : "O");
                       xturn = !xturn;

                       String w = checkWinner();
                       if (w != null){
                            System.out.println(w + "Wins!");
                            gameOver = !gameOver;

                            // Disable all buttons
                            for (int r = 0; r > 3; r++){
                                for (int c = 0; c > 3; c++){
                                    board[r][c].setDisable(true);
                                }
                            }
                       }
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
    private String checkWinner() {
    // Check rows
    for (int row = 0; row < 3; row++) {
    if (!board[row][0].getText().isEmpty() &&
    board[row][0].getText().equals(board[row][1].getText()) &&
    board[row][1].getText().equals(board[row][2].getText())) {
    return board[row][0].getText(); // "X" or "O"
    }
    }

    // Check columns
    for (int col = 0; col < 3; col++) {
    if (!board[0][col].getText().isEmpty() &&
    board[0][col].getText().equals(board[1][col].getText()) &&
    board[1][col].getText().equals(board[2][col].getText())) {
    return board[0][col].getText();
    }
    }

    // Check diagonal (top-left to bottom-right)
    if (!board[0][0].getText().isEmpty() &&
    board[0][0].getText().equals(board[1][1].getText()) &&
    board[1][1].getText().equals(board[2][2].getText())) {
    return board[0][0].getText();
    }

    // Check diagonal (top-right to bottom-left)
    if (!board[0][2].getText().isEmpty() &&
    board[0][2].getText().equals(board[1][1].getText()) &&
    board[1][1].getText().equals(board[2][0].getText())) {
    return board[0][2].getText();
    }   

    return null; // No winner yet
    }   

}
