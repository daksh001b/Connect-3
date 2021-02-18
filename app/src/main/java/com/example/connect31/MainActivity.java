package com.example.connect31;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 1 ;

    int[] currentState = {0 ,0 ,0 , 0, 0, 0, 0, 0, 0} ;

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameOn = true;

    public void tokenAppear(View view){

        // red = 1 ; yellow = 2 ; empty = 0

        ImageView counter = (ImageView) view;

        currentState[Integer.parseInt(counter.getTag().toString())] = activePlayer;

        if(gameOn && counter.getDrawable() == null) {

        counter.setTranslationY(-1500);

        if (activePlayer == 1) {

            counter.setImageResource(R.drawable.red);
            activePlayer = 2;

        } else {

            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }
        counter.animate().translationYBy(1500).setDuration(300);

        String winner = null;

        for (int[] winningPosition : winningPositions) {

            if (currentState[winningPosition[0]]==currentState[winningPosition[1]] && currentState[winningPosition[1]]==currentState[winningPosition[2]] && currentState[winningPosition[0]] != 0) {

                if (activePlayer == 1) {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }

                TextView textview = (TextView) findViewById(R.id.textView);
                Button playAgain = (Button) findViewById(R.id.playAgain);

                textview.setText(winner + " has won!");
                textview.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                gameOn = false;
            }
        }

    }
    }

    public void playAgain(View view) {

        TextView textview = (TextView) findViewById(R.id.textView);
            Button playAgain = (Button) findViewById(R.id.playAgain);

            textview.setVisibility(View.INVISIBLE);
            playAgain.setVisibility(View.INVISIBLE);

            for(int i : currentState){
                i=0;
            }

            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

            for(int i = 0 ; i <gridLayout.getChildCount() ; i++){
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }

        gameOn = true;
        activePlayer = 1 ;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
