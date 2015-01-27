package edu.washington.chau93.lifecounter;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private final String TAG = "MainActivity";
    private final int PLAYER_COUNT = 4;
    private int[] playersHealth;
    private int[][] playersIDs = {
            {R.id.p1hp, R.id.p1plus1, R.id.p1plus5, R.id.p1minus1, R.id.p1minus5},
            {R.id.p2hp, R.id.p2plus1, R.id.p2plus5, R.id.p2minus1, R.id.p2minus5},
            {R.id.p3hp, R.id.p3plus1, R.id.p3plus5, R.id.p3minus1, R.id.p3minus5},
            {R.id.p4hp, R.id.p4plus1, R.id.p4plus5, R.id.p4minus1, R.id.p4minus5}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            playersHealth = savedInstanceState.getIntArray("playersHP");
            for(int i = 0; i < playersHealth.length; i++){
                if(playersHealth[i] <= 0){
                    disablePlayerBtn(i);
                }
            }
            Log.i(TAG, "Save State playersHp: " + playersHealth.toString());
        } else {
            Log.i(TAG, "No save state, creating new playersHp");
            playersHealth = new int[PLAYER_COUNT];
            for(int i = 0; i < PLAYER_COUNT; i++){
                playersHealth[i] = 20;
            }
        }



        for(int i = 0; i < playersIDs.length; i++){
            final TextView healthView = (TextView) findViewById(playersIDs[i][0]);
            healthView.setText(playersHealth[i] + "");
            Button btnPlus1 = (Button) findViewById(playersIDs[i][1]);
            Button btnPlus5 = (Button) findViewById(playersIDs[i][2]);
            Button btnMinus1 = (Button) findViewById(playersIDs[i][3]);
            Button btnMinus5 = (Button) findViewById(playersIDs[i][4]);
            setPlusMinusListeners(i, healthView, btnPlus1, btnMinus1, btnPlus5, btnMinus5);
        }

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
        if (playersHealth[player] > 0){
            playersHealth[player] = playersHealth[player] + value;
            playerText.setText("" + playersHealth[player]);
            if(playersHealth[player] <= 0){
                Log.d(TAG, "Player " + player + " is dead.");
                disablePlayerBtn(player);
                Context context = getApplicationContext();
                Toast.makeText(
                        context,
                        "Player " + player + " LOSES!",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    private void disablePlayerBtn(int player) {
        int[] ids = playersIDs[player];;
        for(int i = 1; i < ids.length; i++){
            ((Button) findViewById(ids[i])).setEnabled(false);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntArray("playersHP", playersHealth);

        super.onSaveInstanceState(outState);
    }
}
