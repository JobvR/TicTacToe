package com.example.jvanruiten.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class TicTacToeActivity extends AppCompatActivity implements View.OnClickListener {

    int roundCounter = 0;
    char symbol;
    int[][] board = new int[3][3];
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button_reset;
    String result;

    public char playerTurn(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            roundCounter++;
            if (roundCounter == 1 || roundCounter == 3 || roundCounter == 5 || roundCounter == 7 || roundCounter == 9) {
                return symbol = 'X';
            } else {
                return symbol = 'O';
            }
        } else {
            return text.charAt(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button_reset = findViewById(R.id.button_reset);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    private void resetBoard() {
        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button6.setText("");
        button5.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        roundCounter = 0;
        board = new int[3][3];
    }

    @Override
    public void onClick(View view) {
        ((Button) view).setText(String.valueOf(playerTurn(((Button) view).getText())));
        fillBoard((Button) view);
        checkForWinner();
        showResetDialog();
    }

    private void showResetDialog() {
        if (checkForWin()) {
            new AlertDialog.Builder(TicTacToeActivity.this)
                    .setTitle(String.valueOf(checkForWinner()))
                    .setMessage("Do you want to start a new game?")
                    .setCancelable(false)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            resetBoard();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TicTacToeActivity.this.finish();
                        }
                    }).show();
        } else if (!checkForWin() && roundCounter == 9) {
            new AlertDialog.Builder(TicTacToeActivity.this)
                    .setTitle(String.valueOf(checkForWinner()))
                    .setMessage("Do you want new game")
                    .setCancelable(false)
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            resetBoard();
                        }
                    })
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            TicTacToeActivity.this.finish();
                        }
                    }).show();
        }
    }

    private void fillBoard(Button button) {
        switch (button.getId()) {
            case R.id.button1:
                board[0][0] = button.getText().toString().charAt(0);
                break;
            case R.id.button2:
                board[0][1] = button.getText().toString().charAt(0);
                break;
            case R.id.button3:
                board[0][2] = button.getText().charAt(0);
                break;
            case R.id.button4:
                board[1][0] = button.getText().toString().charAt(0);
                break;
            case R.id.button5:
                board[1][1] = button.getText().toString().charAt(0);
                break;
            case R.id.button6:
                board[1][2] = button.getText().toString().charAt(0);
                break;
            case R.id.button7:
                board[2][0] = button.getText().toString().charAt(0);
                break;
            case R.id.button8:
                board[2][1] = button.getText().toString().charAt(0);
                break;
            case R.id.button9:
                board[2][2] = button.getText().toString().charAt(0);
                break;

        }
    }

    private boolean checkForWin() {
        if (roundCounter < 10) {
            for (int i = 0; i < 3; i++) {

                //checks horizontal
                int row = i;
                if (board[row][0] == board[row][1]
                        && board[row][1] == board[row][2]) {
                    if (board[row][0] > 0) {
                        return true;
                    }
                }
                //checks vertical
                else if (board[0][row] == board[1][row]
                        && board[1][row] == board[2][row]) {
                    if (board[0][row] != 0) {
                        return true;
                    }
                }
            }
            if (board[0][0] == board[1][1]
                    && board[1][1] == board[2][2]) {
                if (board[0][0] != 0) {
                    return true;
                }
            } else if (board[0][2] == board[1][1]
                    && board[1][1] == board[2][0]) {
                if (board[0][2] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    void checkRow() {
        int[] field = new int[9];
        for (int i = 0; i < field.length; i += 3) {
            if (field[i] == field[i + 1] && field[i] == field[i + 2]) {

            }
        }
    }

    void checkColumn() {
        int[] field = new int[9];
        for (int i = 0; i < 3; i ++) {
            if (field[i] == field[i + 3] && field[i] == field[i + 6]) {

            }
        }
    }

    private String checkForWinner() {
        if (checkForWin()) {
            if (roundCounter == 5 || roundCounter == 7 || roundCounter == 9) {
                result = "Player 1 wins!";
            } else if (roundCounter == 6 || roundCounter == 8) {
                result = "Player 2 wins!";
            }
        } else if (!checkForWin()) {
            if (roundCounter == 9) {
                result = "Draw!";
            }
        }
        return result;
    }
}
