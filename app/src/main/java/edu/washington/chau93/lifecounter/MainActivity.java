package edu.washington.chau93.lifecounter;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private final String TAG = "MainActivity";
    private boolean gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameOver = false;

        // Set up player 1
        final TextView p1HealthView = (TextView) findViewById(R.id.p1hp);
        Button p1BtnPlus1 = (Button) findViewById(R.id.p1plus1);
        Button p1BtnMinus1 = (Button) findViewById(R.id.p1minus1);
        Button p1BtnPlus5 = (Button) findViewById(R.id.p1plus5);
        Button p1BtnMinus5 = (Button) findViewById(R.id.p1minus5);
        setPlusMinusListeners(1, p1HealthView, p1BtnPlus1, p1BtnMinus1, p1BtnPlus5, p1BtnMinus5);

        // Set up player 2
        final TextView p2HealthView = (TextView) findViewById(R.id.p2hp);
        Button p2BtnPlus1 = (Button) findViewById(R.id.p2plus1);
        Button p2BtnMinus1 = (Button) findViewById(R.id.p2minus1);
        Button p2BtnPlus5 = (Button) findViewById(R.id.p2plus5);
        Button p2BtnMinus5 = (Button) findViewById(R.id.p2minus5);
        setPlusMinusListeners(2, p2HealthView, p2BtnPlus1, p2BtnMinus1, p2BtnPlus5, p2BtnMinus5);

        // Set up player 3
        final TextView p3HealthView = (TextView) findViewById(R.id.p3hp);
        Button p3BtnPlus1 = (Button) findViewById(R.id.p3plus1);
        Button p3BtnMinus1 = (Button) findViewById(R.id.p3minus1);
        Button p3BtnPlus5 = (Button) findViewById(R.id.p3plus5);
        Button p3BtnMinus5 = (Button) findViewById(R.id.p3minus5);
        setPlusMinusListeners(3, p3HealthView, p3BtnPlus1, p3BtnMinus1, p3BtnPlus5, p3BtnMinus5);

        // Set up player 4
        final TextView p4HealthView = (TextView) findViewById(R.id.p4hp);
        Button p4BtnPlus1 = (Button) findViewById(R.id.p4plus1);
        Button p4BtnMinus1 = (Button) findViewById(R.id.p4minus1);
        Button p4BtnPlus5 = (Button) findViewById(R.id.p4plus5);
        Button p4BtnMinus5 = (Button) findViewById(R.id.p4minus5);
        setPlusMinusListeners(4, p4HealthView, p4BtnPlus1, p4BtnMinus1, p4BtnPlus5, p4BtnMinus5);



    }

    private void setPlusMinusListeners(final int player, final TextView playerView,
                                       Button plus1, Button minus1, Button plus5, Button minus5){
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyHealth(player, playerView, 1);
            }
        });
        plus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyHealth(player, playerView, 5);
            }
        });
        minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyHealth(player, playerView, -1);
            }
        });
        minus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyHealth(player, playerView, -5);
            }
        });

    }

    private void modifyHealth(int player, TextView playerText, int value){
        Log.d(TAG, "Player " + player + ": " + value + " button clicked.");
        if(gameOver){
            Log.d(TAG, "Button is pushed but game is over.");
        } else {
            int currHealth = Integer.parseInt(playerText.getText().toString());
            if (currHealth > 0){
                int newHealth = currHealth + value;
                playerText.setText("" + newHealth);
                if(newHealth <= 0){
                    Log.d(TAG, "Player " + player + " is dead.");
                    TextView loser = (TextView) findViewById(R.id.loser);
                    loser.setText("Player " + player + " LOSES!");
                    gameOver = true;
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
