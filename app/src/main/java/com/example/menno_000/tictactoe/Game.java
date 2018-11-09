package com.example.menno_000.tictactoe;

import android.content.Context;
import android.service.quicksettings.Tile;
import android.widget.Toast;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    public TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
        movesPlayed = 0;
    }

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

        else {
            return TileState.INVALID;
        }
    }


    public GameState won() {
        TileState t1 = board[0][0];
        TileState t2 = board[0][1];
        TileState t3 = board[0][2];
        TileState t4 = board[1][0];
        TileState t5 = board[1][1];
        TileState t6 = board[1][2];
        TileState t7 = board[2][0];
        TileState t8 = board[2][1];
        TileState t9 = board[2][2];

        if (movesPlayed == 9) {
            return GameState.DRAW;
        }

        else if (t1 == TileState.CROSS & t2 == TileState.CROSS & t3 == TileState.CROSS) {
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
        //
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
        //
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

        return GameState.IN_PROGRESS;
    }
}
