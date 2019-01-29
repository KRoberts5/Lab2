package com.example.jsu.lab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.*;

public class MainActivity extends AppCompatActivity {

    public enum Weapon {

        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        private String message;

        private Weapon(String msg) { message = msg; }

        @Override
        public String toString() { return message; }

    };

    private static final int ROCK = 0;
    private static final int PAPER = 1;
    private static final int SCISSORS = 2;

    private String outcomeString;

    private Weapon playerWeapon;
    private int playerWins;

    private Weapon compWeapon;
    private int compWins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        playerWins = 0;
        compWins = 0;

        outcomeString = "";
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

    public void playerChoseRock(View v){
        playerWeapon = Weapon.ROCK;
        computerChoose();
        compareChoices();
    }
    public void playerChosePaper(View v){
        playerWeapon = Weapon.PAPER;
        computerChoose();
        compareChoices();
    }
    public void playerChoseScissors(View v){
        playerWeapon = Weapon.SCISSORS;
        computerChoose();
        compareChoices();
    }

    private void computerChoose(){
        int choice = (int)(Math.random()*3);

        switch(choice){
            case ROCK:
                compWeapon = Weapon.ROCK;
                break;
            case PAPER:
                compWeapon = Weapon.PAPER;
                break;
            default:
                compWeapon = Weapon.SCISSORS;
                break;
        }
    }
    private void compareChoices(){

        if(playerWeapon == Weapon.ROCK){
            if(compWeapon == Weapon.ROCK){
                outcomeString = "It Was A Draw!";
            }
            else if(compWeapon == Weapon.PAPER){
                ++compWins;
                outcomeString = "Computer Wins... Paper Smothers Rock.";
            }
            else{
                ++playerWins;
                outcomeString = "Player Wins... Rock Blunts Scissors.";
            }
        }
        else if(playerWeapon == Weapon.PAPER){
            if(compWeapon == Weapon.ROCK){
                ++playerWins;
                outcomeString = "Player Wins... Paper Smothers Rock.";
            }
            else if(compWeapon == Weapon.PAPER){
                outcomeString = "It Was A Draw!";
            }
            else{
                ++compWins;
                outcomeString = "Computer Wins... Scissors Cuts Paper.";
            }
        }
        else{
            if(compWeapon == Weapon.ROCK){
                ++compWins;
                outcomeString = "Computer Wins... Rock Blunts Scissors.";
            }
            else if(compWeapon == Weapon.PAPER){
                ++playerWins;
                outcomeString = "Player Wins... Scissors Cuts Paper.";
            }
            else{
                outcomeString = "It Was A Draw!";
            }
        }

        updateOutput();
    }

    private void updateOutput(){
        TextView scoreOutput = (TextView)findViewById(R.id.ScoreOutput);
        String scoreString = "Player: " + playerWins + ", Computer: " + compWins;
        scoreOutput.setText(scoreString);

        TextView playerWeaponOutput = (TextView)findViewById(R.id.PlayerWeaponOutput);
        String playerWeaponString = "Player's Weapon: " + playerWeapon.toString();
        playerWeaponOutput.setText(playerWeaponString);

        TextView compWeaponOutput = (TextView)findViewById(R.id.ComputerWeaponOutput);
        String compWeaponString = "Computer's Weapon: " + compWeapon.toString();
        compWeaponOutput.setText(compWeaponString);

        TextView outcomeOutput = (TextView)findViewById(R.id.OutcomeOutput);
        outcomeOutput.setText(outcomeString);

    }
}
