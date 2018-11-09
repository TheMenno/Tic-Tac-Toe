package com.example.menno_000.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Game game;

    Button button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the game
        game = new Game();

        // Find the buttons
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the game
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // Restore the game
        Game savedGame = (Game) inState.getSerializable("game");
        game = savedGame;

        // Find the tilestates
        TileState b1 = savedGame.board[0][0];
        TileState b2 = savedGame.board[0][1];
        TileState b3 = savedGame.board[0][2];
        TileState b4 = savedGame.board[1][0];
        TileState b5 = savedGame.board[1][1];
        TileState b6 = savedGame.board[1][2];
        TileState b7 = savedGame.board[2][0];
        TileState b8 = savedGame.board[2][1];
        TileState b9 = savedGame.board[2][2];

        // Set the buttons to the saved values
        setText(b1, button1);
        setText(b2, button2);
        setText(b3, button3);
        setText(b4, button4);
        setText(b5, button5);
        setText(b6, button6);
        setText(b7, button7);
        setText(b8, button8);
        setText(b9, button9);
    }

    // Takes input and writes the right text in a tile
    public void setText(TileState tileState, Button button) {
        switch (tileState) {
            case BLANK:
                button.setText("");
                break;
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
        }
    }

    // Acts when a tile is clicked
    public void tileClicked(View view) {
        int iden = view.getId();
        int iD = iden % 10;

        int row;
        int column;

        // Find the clicked button
        Button button = findViewById(iden);

        // Retrieve the row and column
        switch (iD) {
            case 0:
                row = 0;
                column = 0;
                break;
            case 1:
                row = 0;
                column = 1;
                break;
            case 2:
                row = 0;
                column = 2;
                break;
            case 3:
                row = 1;
                column = 0;
                break;
            case 4:
                row = 1;
                column = 1;
                break;
            case 5:
                row = 1;
                column = 2;
                break;
            case 6:
                row = 2;
                column = 0;
                break;
            case 7:
                row = 2;
                column = 1;
                break;
            case 8:
                row = 2;
                column = 2;
                break;
            default:
                row = 0;
                column = 0;

        }

        // Either fill or don't fill the tile
        TileState state = game.choose(row, column);

        // Set the visualisation of the app
        switch(state) {
            case CROSS:
                // do something
                button.setText("X");
                break;
            case CIRCLE:
                // do something
                button.setText("O");
                break;
        }

        // Check if a player has won
        GameState gstate = game.won();

        // End the game if someone has won
        switch (gstate) {
            case PLAYER_ONE:
                Toast.makeText(this, "Player one won!",
                        Toast.LENGTH_SHORT).show();
                enableButtons(false);
                break;

            case PLAYER_TWO:
                Toast.makeText(this, "Player two won!",
                        Toast.LENGTH_SHORT).show();
                enableButtons(false);
                break;

            case DRAW:
                Toast.makeText(this, "It's a draw!",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // Turn the buttons (un)clickable
    public void enableButtons(Boolean bool) {
        button1.setEnabled(bool);
        button2.setEnabled(bool);
        button3.setEnabled(bool);
        button4.setEnabled(bool);
        button5.setEnabled(bool);
        button6.setEnabled(bool);
        button7.setEnabled(bool);
        button8.setEnabled(bool);
        button9.setEnabled(bool);
    }

    // Reset the game
    public void resetClicked(View view) {

        // Reset buttons
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");

        // Make the buttons clickable (again)
        enableButtons(true);

        // Reset the game
        game = new Game();

    }
}
