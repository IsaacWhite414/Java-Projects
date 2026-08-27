import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class TicTacToe extends Application {
    Button [][] board = new Button[3][3];
    Random random = new Random();
    Label winner = new Label();
    boolean gameOver = false;
    private boolean xturn = true;
    @Override
    public void start(Stage primaryStage) {
        // Create UI 
        GridPane grid = new GridPane();

        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setOnAction(e -> {
                    if (button.getText().isEmpty() && !gameOver){
                       button.setText("X");
                       xturn = !xturn;

                       String w = checkWinner();
                       if (w == null){
                            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                            pause.setOnFinished(event -> {
                                aiMove();

                                String w2 = checkWinner();
                                if (w2 != null){
                                    System.out.println(w2 + "" + "Wins!");
                                    gameOver = true;
                                }
                            });
                            pause.play();
                        }
                        else{
                            winner.setText(w + "" + "Wins!");
                            gameOver = true;
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
        

        // Create a scene with specific dimensions (Width, Height)
        Scene scene = new Scene(grid);

        // Configure and display the main window (Stage)
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
    
    private void aiMove(){
        int moveR, moveC;
        do {
            moveR = random.nextInt(3);
            moveC = random.nextInt(3);
        } while (!board[moveR][moveC].getText().isEmpty());
        board[moveR][moveC].setText("O");
    }

}
