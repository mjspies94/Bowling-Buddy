package com.example.mario.bowlingbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class BowlingGame extends AppCompatActivity {

    //score for rolls, frames and the total
   // DatabaseHandler db = new DatabaseHandler(this);
   // UserPrefs prefs;

    //score for rolls, frames and the total score ---------
    private int rollOne; // roll one score
    private int rollTwo; // roll two score
    private int rollThree; // third roll on the 10th Frame
    private int frameScore; // framescore - might not need it
    private int totalScore; // total score
    private int rollNumber; // the roll number
    private int frameNumber; // keeps track of which frame is currently in play

    private boolean existingGame;
    private String gameName;

    private ArrayList<Frame> frames = new ArrayList<Frame>(); // Array list of the frame objects
    private ArrayList<Button> downButtons; // Array List of buttons that were knocked down on the FIRST roll
    private ArrayList<Button> allButtons; // Array List of all the buttons
    private ArrayList<Button> pinsDownInFrame; //Array list to keep track of pins knocked down in each frame
    private ArrayList<TextView> rollOneFrames; // Array list to keep track of 1st rolls on frames
    private ArrayList<TextView> rollTwoFrames;  // Array list to keep track of 2nd rolls on frames
    private ArrayList<TextView> tscores; // Array list of scores to appear under each frame

    private Button strikeButton;
    private Button frame1Button;
    private Button frame2Button;

    private TextView roll1One,roll2One,roll1Two,roll2Two,roll1Three,roll2Three,roll1Four,roll2Four,roll1Five,roll2Five,roll1Six,roll2Six,roll1Seven,roll2Seven,roll1Eight,
    roll2Eight,roll1Nine, roll2Nine,roll1Ten,roll2Ten;

    private TextView roll3Ten;

    private TextView tscore1,tscore2,tscore3,tscore4,tscore5,tscore6,tscore7,tscore8,tscore9,tscore10; // total score THUS far after each frame

    private Frame frameOne, frameTwo,frameThree,frameFour,frameFive,frameSix,frameSeven,frameEight,frameNine,frameTen; // All the frames


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowling_game);
        Button backButton = (Button) findViewById(R.id.detailsButton);
        backButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BowlingGame.this, Home.class);
                startActivity(intent);
            }
        });
        Bundle extras = getIntent().getExtras();
        //checks if the user is loading data from a previous game already played, or starting a new game.
        if(extras != null){
            existingGame = extras.getBoolean("existingGame");
            gameName = extras.getString("gameName");

        }

        frameNumber = 0; // first frame in arraylist
        rollNumber = 1; // first roll

        //add all frames to array list
        rollOneFrames = new ArrayList<TextView>(); //Array of all the roll 1's for each frame
        rollTwoFrames = new ArrayList<TextView>(); //Array of all the roll 2's for each frame
        tscores = new ArrayList<TextView>(); // Array of all the total scores to appear at bottom of frames
        allButtons = new ArrayList<Button>(); //   Array of all buttons in frame
        downButtons = new ArrayList<Button>(); // Array of buttons knocked down in array
        pinsDownInFrame = new ArrayList<Button>();

        //initialize all total scores for each frame
        tscore1 = (TextView) findViewById(R.id.tscore1);tscore2 = (TextView) findViewById(R.id.tscore2); tscore3 = (TextView) findViewById(R.id.tscore3);tscore4 = (TextView) findViewById(R.id.tscore4);
        tscore5 = (TextView) findViewById(R.id.tscore5);tscore6 = (TextView) findViewById(R.id.tscore6);tscore7 = (TextView) findViewById(R.id.tscore7);tscore8 = (TextView) findViewById(R.id.tscore8);
        tscore9 = (TextView) findViewById(R.id.tscore9);tscore10 = (TextView) findViewById(R.id.tscore10);

        //add total scores to arraylist
        tscores.add(tscore1);tscores.add(tscore2);tscores.add(tscore3);tscores.add(tscore4);tscores.add(tscore5);tscores.add(tscore6);tscores.add(tscore7);tscores.add(tscore8);
        tscores.add(tscore9);tscores.add(tscore10);



        ///frameText = (TextView) findViewById(R.id.frameText);
        //rollOneText = (TextView) findViewById(R.id.roll1Text);
        //rollTwoText = (TextView) findViewById(R.id.rollTwoText);

        // add roll 1's and roll 2's textview to an array list
        roll1One = (TextView) findViewById(R.id.roll1One);roll2One = (TextView) findViewById(R.id.roll2One);roll1Two = (TextView) findViewById(R.id.roll1Two);
        roll2Two = (TextView) findViewById(R.id.roll2Two);roll1Three = (TextView) findViewById(R.id.roll1Three);roll2Three = (TextView) findViewById(R.id.roll2Three);
        roll1Four = (TextView) findViewById(R.id.roll1Four);roll2Four = (TextView) findViewById(R.id.roll2Four);roll1Five = (TextView) findViewById(R.id.roll1Five) ;
        roll2Five = (TextView) findViewById(R.id.roll2Five);roll1Six = (TextView) findViewById(R.id.roll1Six);roll2Six = (TextView) findViewById(R.id.roll2Six);
        roll1Seven = (TextView) findViewById(R.id.roll1Seven);roll2Seven = (TextView) findViewById(R.id.roll2Seven);roll1Eight = (TextView) findViewById(R.id.roll1Eight);
        roll2Eight = (TextView) findViewById(R.id.roll2Eight);roll1Nine = (TextView) findViewById(R.id.roll1Nine);roll2Nine = (TextView) findViewById(R.id.roll2Nine);
        roll1Ten = (TextView) findViewById(R.id.roll1Ten);roll2Ten = (TextView) findViewById(R.id.roll2Ten);

        roll3Ten = (TextView) findViewById(R.id.roll3Ten);

        rollOneFrames.add(roll1One);rollTwoFrames.add(roll2One);rollOneFrames.add(roll1Two);rollTwoFrames.add(roll2Two);rollOneFrames.add(roll1Three);
        rollTwoFrames.add(roll2Three);rollOneFrames.add(roll1Four);rollTwoFrames.add(roll2Four);rollOneFrames.add(roll1Five);rollTwoFrames.add(roll2Five);
        rollOneFrames.add(roll1Six);rollTwoFrames.add(roll2Six);rollOneFrames.add(roll1Seven);rollTwoFrames.add(roll2Seven);rollOneFrames.add(roll1Eight);rollTwoFrames.add(roll2Eight);
        rollOneFrames.add(roll1Nine);rollTwoFrames.add(roll2Nine);rollOneFrames.add(roll1Ten);rollTwoFrames.add(roll2Ten);

        //totalScoreText = (TextView) findViewById(R.id.totalScoreText);
        strikeButton = (Button) findViewById(R.id.strikeButton);

        // Add all the buttons to the "allButtons" array
        Button one = (Button) findViewById(R.id.pinOne); allButtons.add(one); Button two = (Button) findViewById(R.id.pinTwo); allButtons.add(two);
        Button three = (Button) findViewById(R.id.pinThree); allButtons.add(three); Button four = (Button) findViewById(R.id.pinFour); allButtons.add(four);
        Button five = (Button) findViewById(R.id.pinFive); allButtons.add(five); Button six = (Button) findViewById(R.id.pinSix); allButtons.add(six);
        Button seven = (Button) findViewById(R.id.pinSeven); allButtons.add(seven); Button eight = (Button) findViewById(R.id.pinEight); allButtons.add(eight);
        Button nine = (Button) findViewById(R.id.pinNine); allButtons.add(nine); Button ten = (Button) findViewById(R.id.pinTen); allButtons.add(ten);

        one.setContentDescription("up");two.setContentDescription("up");three.setContentDescription("up");four.setContentDescription("up");five.setContentDescription("up");
        six.setContentDescription("up");seven.setContentDescription("up");eight.setContentDescription("up");nine.setContentDescription("up");ten.setContentDescription("up");

        rollOne = 0;
        rollTwo = 0;
        rollThree = 0;

        // if loading data from a previous game,
        if(existingGame){
            frameOne = new Frame();
            frameTwo = new Frame(frameOne);
            frameThree = new Frame(frameTwo);
            frameFour = new Frame(frameThree);
            frameFive = new Frame(frameFour);
            frameSix = new Frame(frameFive);
            frameSeven = new Frame(frameSix);
            frameEight = new Frame(frameSeven);
            frameNine = new Frame(frameEight);
            frameTen = new Frame(frameNine);
            loadGame();

        }
        else {
            //initialize each frame
            frameOne = new Frame();
            frameTwo = new Frame(frameOne);
            frameThree = new Frame(frameTwo);
            frameFour = new Frame(frameThree);
            frameFive = new Frame(frameFour);
            frameSix = new Frame(frameFive);
            frameSeven = new Frame(frameSix);
            frameEight = new Frame(frameSeven);
            frameNine = new Frame(frameEight);
            frameTen = new Frame(frameNine);
        }
        frames.add(frameOne);frames.add(frameTwo);frames.add(frameThree);frames.add(frameFour);frames.add(frameFive);frames.add(frameSix);frames.add(frameSeven);frames.add(frameEight);
        frames.add(frameNine);frames.add(frameTen);
        frameTen.setTenthFrame();

        //loads data from the previous game
        if(existingGame){
            loadGame();
        }

    }

    // HANDLES WHEN PIN IS CLICKED
    public void pin_Clicked(View v) {
        Button pin = (Button) v;


        if (pin.getContentDescription() == "up") { // check if content description equals up
            pin.setBackgroundResource(R.drawable.dot); // set background resource to dot
            pin.setContentDescription("down"); //set content description to down

            if (rollNumber == 1) { // if roll equals 1
                rollOne++; // add one to roll one score
                if (rollOne == 10) {
                    frames.get(frameNumber).setStrikeRolled();
                    rollOneFrames.get(frameNumber).setText("X");
                    Strike();

                } else {
                    rollOneFrames.get(frameNumber).setText(Integer.toString(rollOne)); //sets the text to roll one score
                    frames.get(frameNumber).setRollOne(rollOne);
                    rollTwo = 0;
                    downButtons.add(pin);
                }

            }
            else if (rollNumber == 2) {
                rollTwo++;
                if (rollTwo == (10 - rollOne)) {
                    Spare();
                } else {
                    rollTwo++;
                    rollTwoFrames.get(frameNumber).setText(Integer.toString(rollTwo));
                    frames.get(frameNumber).setRollTwo(rollTwo);
                }
            }


        }

        else // else if the pin was already knocked down

        {
            pin.setBackgroundResource(R.drawable.ball); // background is changed back to white circle
            pin.setContentDescription("up"); // content description is set to up

            if (rollNumber == 1)
            {
                rollOne--; // decreases score by one
                rollOneFrames.get(frameNumber).setText(Integer.toString(rollOne)); // sets the text to roll 1 score
                frames.get(frameNumber).setRollOne(rollOne); // resets the roll one score for the specific frame
                for (int x = 0; x < downButtons.size(); x++) { // removes that pin from the down button array list
                    if (downButtons.get(x) == pin) {
                        downButtons.remove(x);
                    }
                }
            }
            else if (rollNumber == 2) {
                rollTwoFrames.get(frameNumber).setText(Integer.toString(rollTwo));
                frames.get(frameNumber).setRollTwo(rollTwo);
                frameScore = rollOne + rollTwo;

            }

        }
    }


    public void strikeButton_clicked(View v) {

        if(frameNumber != 9) {
            // If Strike
            if (rollNumber == 1) {
                rollOne = 10;
                frameScore = 10;
                rollOneFrames.get(frameNumber).setText("");
                rollTwoFrames.get(frameNumber).setText("X");
                Strike();
            }

            //If Spare
            else if (rollNumber == 2) {
                rollTwo = 10 - rollOne;
                frameScore = 10;
                rollTwoFrames.get(frameNumber).setText("/");
                Spare();
            }
        }
        else {
            if(rollNumber ==1)
            {
                rollOne = 10;
                frameTen.setRollOne(10);
                roll1Ten.setText("X");
                rollNumber++;

            }
            else if(rollNumber == 2){
                rollTwo = 10;
                frameTen.setRollTwo(10);
                roll2Ten.setText("X");
                rollNumber++;

            }
            else if(rollNumber == 3){
                roll3Ten.setText("X");
                rollThree =10;
                frameTen.setRollThree(10);
                finishGame();
            }
        }



    }

    // Handles what happens on strike
    public void Strike() {

        frames.get(frameNumber).setRollOne(10);
        frames.get(frameNumber).setRollTwo(0);
        frames.get(frameNumber).setStrikeRolled();
        for (int x = 0; x < allButtons.size(); x++) {

            allButtons.get(x).setBackgroundResource(R.drawable.dot);
            allButtons.get(x).setContentDescription("down");

        }
        nextFrame();
    }

    // Handles what happens on spare
    public void Spare() {

        frames.get(frameNumber).setRollOne(rollOne);
        frames.get(frameNumber).setRollTwo(rollTwo);
        frames.get(frameNumber).setSparedRolled();

        for (int x = 0; x < allButtons.size(); x++) {

            if (allButtons.get(x).getContentDescription() == "up") {
                allButtons.get(x).setBackgroundResource(R.drawable.dot);
                allButtons.get(x).setContentDescription("down");
            }
        }
        nextFrame();


    }

    public void nextButton_clicked(View v) {

        rollNumber++; //Roll Number increases, starts at 1

        if (rollNumber == 2) {
            if(frameNumber!=9) {
                strikeButton.setText("SPARE"); // Strike button is now presented as Spare button
            }
            for (int x = 0; x < downButtons.size(); x++) {
                downButtons.get(x).setClickable(false);
            }
            rollOneFrames.get(frameNumber).setText(Integer.toString(rollOne));


        }
        else if (rollNumber == 3) {
            frames.get(frameNumber).setRollOne(rollOne);
            frames.get(frameNumber).setRollTwo(rollTwo);
            frames.get(frameNumber).setDownButtons(downButtons);
            downButtons.clear();
            rollTwoFrames.get(frameNumber).setText(Integer.toString(rollTwo));
            nextFrame();
        }
        else if(rollNumber == 4){
            roll3Ten.setText(Integer.toString(rollThree));
        }

    }



    public void nextFrame() {
        totalScore = 0;
        frameScore = rollOne + rollTwo;
        frames.get(frameNumber).setFrameScore(frameScore);

        for(int x = 0; x<=frameNumber; x++) {

            totalScore += frames.get(x).getFrameScore();
            tscores.get(x).setText(Integer.toString(totalScore));


        }

        strikeButton.setText("STRIKE");

        if(frameNumber != 9) {
            frameNumber++;
            rollNumber = 1;
            rollOne = 0;
            rollTwo = 0;
            frameScore = 0;
        }
        for (int x = 0; x < allButtons.size(); x++) {
            if (allButtons.get(x).getContentDescription() == "down") {
                allButtons.get(x).setBackgroundResource(R.drawable.ball);
                allButtons.get(x).setContentDescription("up");
            }
            if (allButtons.get(x).isClickable() == false) {
                allButtons.get(x).setClickable(true);
            }
        }
    }

    //retrives data from database if loading a game
    public void loadGame()
    {
        totalScore = 0;
        //frames = (ArrayList<Frame>) db.getFrames(gameName).clone();
        for(int x = 0; x<=9; x++) {
            totalScore += frames.get(x).getFrameScore();
            rollOne = frames.get(x).getRollOne();
            rollTwo = frames.get(x).getRollTwo();
            if(rollOne == 10){
                rollTwoFrames.get(x).setText("X");
            }
            else if(rollOne + rollTwo == 10) {
                rollTwoFrames.get(x).setText("/");
            }
            else {
                rollOneFrames.get(x).setText(Integer.toString(rollOne));
                rollTwoFrames.get(x).setText(Integer.toString(rollTwo));
            }
            tscores.get(x).setText(Integer.toString(totalScore));
        }
    }

    public void finishGame()
    {
        totalScore = 0;
        frameScore = rollOne + rollTwo + rollThree;
        frameTen.setFrameScore(frameScore);
        for(int x = 0; x<=frameNumber; x++) {
            frames.get(x).setGameName(gameName);
            totalScore += frames.get(x).getFrameScore();
           // db.addFrame(frames.get(x));
            tscores.get(x).setText(Integer.toString(totalScore));
        }


    }

}
