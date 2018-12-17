package com.example.menno_000.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Initialising variables
    Game game;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9;
    TextView wintext;
    int row,column;


    // Setting up basic functionality
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the game
        game = new Game();

        // Setting variables to buttons in the application
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // Find the TextView for the win message
        wintext = findViewById(R.id.wintext);
    }


    // Overriding app settings so progress will be saved between swapping views
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Store the game
        outState.putSerializable("game", game);

        // Check if the game has ended
        if (Game.gameOver) {
            outState.putBoolean("enable", false);
        } else {
            outState.putBoolean("enable", true);
        }
    }

    // Overriding app settings so progress will be saved between swapping views
    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // Retrieve the game
        game = (Game) inState.getSerializable("game");

        // Retrieve the tilestates
        TileState b1 = game.board[0][0];
        TileState b2 = game.board[0][1];
        TileState b3 = game.board[0][2];
        TileState b4 = game.board[1][0];
        TileState b5 = game.board[1][1];
        TileState b6 = game.board[1][2];
        TileState b7 = game.board[2][0];
        TileState b8 = game.board[2][1];
        TileState b9 = game.board[2][2];

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

        // (Re)Set the buttons
        Boolean enable = inState.getBoolean("enable");
        enableButtons(enable);
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

        // Initialising variables
        Button button = (Button) view;

        // Retrieve the row and column of the chosen tile and fill the tile if needed
        getRowColumn(button);
        TileState state = game.choose(row, column);
        setButtontext(state, button);

        // Check if a player has won and end the game if someone did
        GameState gstate = game.won();
        gameFinished(gstate);
    }


    // Fill text in a button if the TileState is a certain value
    public void setButtontext(TileState state, Button button) {
        switch(state) {
            case INVALID:
                break;
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
        }
    }


    // Sets row and column to the variables of the given button
    public void getRowColumn(Button button) {
        switch (button.getId()) {
            case R.id.button1:
                row = 0;
                column = 0;
                break;
            case R.id.button2:
                row = 0;
                column = 1;
                break;
            case R.id.button3:
                row = 0;
                column = 2;
                break;
            case R.id.button4:
                row = 1;
                column = 0;
                break;
            case R.id.button5:
                row = 1;
                column = 1;
                break;
            case R.id.button6:
                row = 1;
                column = 2;
                break;
            case R.id.button7:
                row = 2;
                column = 0;
                break;
            case R.id.button8:
                row = 2;
                column = 1;
                break;
            case R.id.button9:
                row = 2;
                column = 2;
                break;
            default:
                row = 0;
                column = 0;

                // Error message
                wintext.setText(getString(R.string.error));
                break;
        }
    }


    // Ends the game according to the gamestate and lets the player(s) know
    public void gameFinished(GameState gstate) {
        switch (gstate) {
            case IN_PROGRESS:
                break;

            case PLAYER_ONE:
                wintext.setText(getString(R.string.onewin));
                enableButtons(false);
                break;

            case PLAYER_TWO:
                wintext.setText(getString(R.string.twowin));
                enableButtons(false);
                break;

            case DRAW:
                wintext.setText(getString(R.string.draw));
                enableButtons(false);
                break;
        }

    }

    // Turn the buttons (un)clickable based on the input
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

    // Reset the game when reset is clicked
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

        // Reset win message
        wintext.setText("");

        // Make the buttons clickable (again)
        enableButtons(true);

        // Reset the game
        game = new Game();

    }
}
