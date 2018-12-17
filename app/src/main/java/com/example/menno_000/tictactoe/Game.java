package com.example.menno_000.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {

    // Setting up variables
    final private int BOARD_SIZE = 3;
    public TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    static public Boolean gameOver;

    // Initiate the game
    public Game() {

        // Fill the board
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        // Setting up variables
        playerOneTurn = true;
        gameOver = false;
        movesPlayed = 0;
    }

    // Acts when a tile is clicked and sets the right values
    public TileState choose(int row, int column) {

        if (board[row][column] == TileState.BLANK) {
            if (playerOneTurn == true) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
                movesPlayed += 1;
                return TileState.CROSS;
            }

            else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                movesPlayed += 1;
                return TileState.CIRCLE;
            }
        }

        // The clicked tile is invalid so nothing happens
        else {
            return TileState.INVALID;
        }
    }


    // Checks if a player has won the game after every move
    public GameState won() {

        // The game cannot end before move 5
        if (movesPlayed < 5) {
            gameOver = false;
            return GameState.IN_PROGRESS;
        }

        // Find the values of the board
        TileState t1 = board[0][0];
        TileState t2 = board[0][1];
        TileState t3 = board[0][2];
        TileState t4 = board[1][0];
        TileState t5 = board[1][1];
        TileState t6 = board[1][2];
        TileState t7 = board[2][0];
        TileState t8 = board[2][1];
        TileState t9 = board[2][2];

        // gameOver will be true if any of the cases below is true
        gameOver = true;

        // All possible win conditions, done by hand because a switch would call three blank spaces
        // in a row a win
        if (t1 == TileState.CROSS & t2 == TileState.CROSS & t3 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t1 == TileState.CIRCLE & t2 == TileState.CIRCLE & t3 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t4 == TileState.CROSS & t5 == TileState.CROSS & t6 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t4 == TileState.CIRCLE & t5 == TileState.CIRCLE & t6 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t7 == TileState.CROSS & t8 == TileState.CROSS & t9 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t7 == TileState.CIRCLE & t8 == TileState.CIRCLE & t9 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t1 == TileState.CROSS & t4 == TileState.CROSS & t7 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t1 == TileState.CIRCLE & t4 == TileState.CIRCLE & t7 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t2 == TileState.CROSS & t5 == TileState.CROSS & t8 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t2 == TileState.CIRCLE & t5 == TileState.CIRCLE & t8 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t3 == TileState.CROSS & t6 == TileState.CROSS & t9 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t3 == TileState.CIRCLE & t6 == TileState.CIRCLE & t9 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t1 == TileState.CROSS & t5 == TileState.CROSS & t9 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t1 == TileState.CIRCLE & t5 == TileState.CIRCLE & t9 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }
        else if (t3 == TileState.CROSS & t5 == TileState.CROSS & t7 == TileState.CROSS) {
            return GameState.PLAYER_ONE;
        }
        else if (t3 == TileState.CIRCLE & t5 == TileState.CIRCLE & t7 == TileState.CIRCLE) {
            return GameState.PLAYER_TWO;
        }

        // If the board is filled without a winner, it's a draw
        else if (movesPlayed == 9) {
            return GameState.DRAW;
        }

        // No win condition has been fulfilled, so the game is still going on
        gameOver = false;
        return GameState.IN_PROGRESS;
    }
}
