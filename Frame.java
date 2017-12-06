package com.example.mario.bowlingbuddy;

import android.widget.Button;

import java.util.ArrayList;

/**
 * Author: Mario Spies
 * Date:11/30/2016
 * Description: This class handles each "Frame" object during the game. Each Game consists of 10 different frames, In which each game then stores those 10 frames an retrieves them from a database if the user wants to view a game in the past.
 */
public class Frame {

    //Instance variables
    private String gameName;
    private int id;
    private int rollOne;
    private int rollTwo;
    private int rollThree;
    private int frameScore;
    private boolean isTenthFrame;
    private boolean strikeRolled;
    private boolean spareRolled;
    private ArrayList<Button> downButtons;
    private Frame prevFrame;


    //Constructor for first frame
    public Frame() {
        id = 1;
        this.rollOne = 0;
        this.rollTwo = 0;
        downButtons = new ArrayList<Button>();
        gameName = "test";
    }

    //Constructor for frames 2-10. Each frame has a previous frame reference to handle strikes and spares.
    public Frame(Frame frame) {

        id=1;
        this.prevFrame = frame;
        this.rollOne = 0;
        this.rollTwo = 0;
        downButtons = new ArrayList<Button>();
        gameName="test";
    }

    public Frame(int rollOne, int rollTwo, int rollThree, int frameScore){
        this.rollOne = rollOne;
        this.rollTwo = rollTwo;
        this.rollThree = rollThree;
        this.frameScore = frameScore;
        if(rollThree!= 0){
            setTenthFrame();
        }

    }

    // Setters for rolls 1 & 2
    public void setRollOne(int score) {
        this.rollOne = score;
    }

    public void setRollTwo(int score) {

        this.rollTwo = score;

    }

    // If the frame is the tenth frame this method is called
    public void setTenthFrame() {
        isTenthFrame = true;
    }

    // Sets roll 3 for the tenth frame
    public void setRollThree(int score) {
        this.rollThree = score;

    }

    public void setGameName(String gameName){
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
    public int getRollOne(){
        return rollOne;
    }

    public int getRollTwo(){
        return rollTwo;
    }

    public int getRollThree(){
        return rollThree;
    }
    // If Strike is rolled
    public void setStrikeRolled() {

        strikeRolled = true;
    }

    public boolean isStrikeRolled() {

        return strikeRolled;
    }

    // If Spare is rolled
    public void setSparedRolled() {

        spareRolled = true;
    }

    public boolean isSpareRolled() {

        return spareRolled;
    }
    public int getId(){
        return id;
    }

    // This method sets the frame score for the specific frame, but also updates previous frame scores
    // if the previous frame rolled a strike/spare
    public void setFrameScore(int score) {

        if (prevFrame != null) { // check if the previous frame is not null
            if (prevFrame.isStrikeRolled()) { // if the previous frame rolled a strike
                if (isStrikeRolled()) { // if this frame rolled a strike
                    prevFrame.frameScore += rollOne; // roll 1 (which is 10) is added to the previous frames frame score
                    if (prevFrame.prevFrame != null) { // if the previous frame of the previous frame is not null
                        if (prevFrame.prevFrame.isStrikeRolled()) { // if that frame rolled a strike
                            prevFrame.prevFrame.frameScore += rollOne; // add roll 1 to that frames frame score
                        }
                    }
                } else { // else if a strike was not rolled on this frame
                    if (isTenthFrame) {  // if this is the tenth frame
                        if (prevFrame.prevFrame.isStrikeRolled()) {
                            prevFrame.prevFrame.frameScore += rollOne; // if prev of prev frame rolled a strike it adds the first roll to it
                        }
                        // add the first and second roll to the previous frame
                        prevFrame.frameScore += rollOne;
                        prevFrame.frameScore += rollTwo;
                    } else {
                        prevFrame.frameScore += score;
                    }
                }
            } else if (prevFrame.isSpareRolled()) {
                prevFrame.frameScore += rollOne; //if previous frame rolled a spare, add the first roll
            }
        }
        this.frameScore = score;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public void setFinalFrameScore(int score){

    }
    public void setDownButtons(ArrayList buttons) {
        for (int x = 0; x > downButtons.size(); x++) {
            Button tempButton = (Button) buttons.get(x);
            downButtons.add((Button) tempButton);
        }
    }


}

