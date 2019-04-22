package com.comrench.fastmathsforkids;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameActivity extends AppCompatActivity {

    final int NUM_OF_DIGITS = 7;

    TextView numText1, numText2, numText3, numText4, numText5, numText6, numText7, timeLeftId, rightId, wrongId, scoreId;

    int[] numList; // = new int[7];

    int finalResult;

    int numOfRightAns = 0, numOfWrongAns = 0, score = 0;

    Button resButton1, resButton2, resButton3, resButton4;
    List<Button> listOfButtons = new ArrayList<>();
    int res1, res2, res3, res4;

    Set<Integer> resultSet = new HashSet<>();

    CountDownTimer cTimer = null;

    boolean timeLeft = true, countScore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        numText1 = findViewById(R.id.num1);
        numText2 = findViewById(R.id.num2);
        numText3 = findViewById(R.id.num3);
        numText4 = findViewById(R.id.num4);
        numText5 = findViewById(R.id.num5);
        numText6 = findViewById(R.id.num6);
        numText7 = findViewById(R.id.num7);
        timeLeftId = findViewById(R.id.timeLeftId);
        rightId = findViewById(R.id.rightId);
        wrongId = findViewById(R.id.wrongId);
        scoreId = findViewById(R.id.scoreId);

        resButton1 = findViewById(R.id.resBtn1);
        resButton2 = findViewById(R.id.resBtn2);
        resButton3 = findViewById(R.id.resBtn3);
        resButton4 = findViewById(R.id.resBtn4);

        listOfButtons.add(resButton1);
        listOfButtons.add(resButton2);
        listOfButtons.add(resButton3);
        listOfButtons.add(resButton4);

        resButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult(resButton1, Integer.parseInt(((Button)v).getText().toString()));
            }
        });
        resButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult(resButton2, Integer.parseInt(((Button)v).getText().toString()));
            }
        });
        resButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult(resButton3, Integer.parseInt(((Button)v).getText().toString()));
            }
        });
        resButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult(resButton4, Integer.parseInt(((Button)v).getText().toString()));
            }
        });

        resetNumbers();
        startTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private void checkResult(Button resButton, int resultClicked) {
//        System.out.println("MeriApp: finalResult: "+finalResult);
        if(timeLeft) {
            if (resultClicked == finalResult) {
                if(countScore) {
                    numOfRightAns++;
                    rightId.setText("Right: " + numOfRightAns);
                }
                Toast.makeText(this, "Right Answer: " + finalResult, Toast.LENGTH_SHORT).show();
                resetNumbers();
                countScore = true;
            } else {
                if(countScore) {
                    numOfWrongAns++;
                    wrongId.setText("Wrong: " + numOfWrongAns);
                    countScore = false;
                }
                Toast.makeText(this, "Wrong Answer: " + finalResult, Toast.LENGTH_SHORT).show();
            }
            score = numOfRightAns * 10 - numOfWrongAns * 5;
            scoreId.setText("Score: " + score);
        }
    }

    private int findSumOfDigits(int randNum){
        int iterator = 0;
        int sum = 0;
        int tempNum = randNum;
        numList = new int[7];
        do{
            numList[iterator] = tempNum%10;
            sum += numList[iterator++];
            tempNum /= 10;
        }while(tempNum>0);
        return sum;
    }

    private void resetNumbers() {
        if(timeLeft) {
            int sumOfDigits = 0;
            resultSet.clear();
            do {
                int randNum = (int) (Math.random() * 10000000);
                sumOfDigits = findSumOfDigits(randNum);
                resultSet.add(sumOfDigits);
            } while (resultSet.size() < 4);

            finalResult = sumOfDigits;

            int i = 0;
            for (int result : resultSet) {
                listOfButtons.get(i++).setText("" + result);
            }

            numText1.setText("" + numList[0]);
            numText2.setText("" + numList[1]);
            numText3.setText("" + numList[2]);
            numText4.setText("" + numList[3]);
            numText5.setText("" + numList[4]);
            numText6.setText("" + numList[5]);
            numText7.setText("" + numList[6]);
        }
    }

    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftId.setText("Time left: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                timeLeftId.setText("FINISH!!");
                timeLeft = false;
            }
        };
        cTimer.start();
    }

    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
}
