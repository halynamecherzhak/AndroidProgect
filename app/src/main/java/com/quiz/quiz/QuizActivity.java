package com.quiz.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Button btnYes;
    Button btnNo;
    TextView questionText;
    private boolean answer;
    private String question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        questionText = (TextView) findViewById(R.id.question);
        StartProgressBar();
        SetQuestion();
    }

    private void SetQuestion() {
        Random random = new Random();

        int firstNumber = random.nextInt(10);
        int secondNumber = random.nextInt(10);
        String[] action = new String[]{"*", "+", "-"};
        int result;
        int actionIndex = 1 + random.nextInt(3);

        switch (actionIndex) {
            case 1:
                result = firstNumber * secondNumber;
                question = "" + firstNumber + "*"  + secondNumber + "=" + RandomAnswer(result);
                break;
            case 2:
                result = firstNumber + secondNumber;
                question = "" + firstNumber + "+"  + secondNumber + "=" + RandomAnswer(result);
                break;
            case 3:
                result = firstNumber - secondNumber;
                question = "" + firstNumber + "-"  + secondNumber + "=" + RandomAnswer(result);
                break;
            default:
                break;
        }
        questionText.setText(question);
    }

    private int RandomAnswer(int rightAnswer)
    {
        Random random = new Random();
        int randomResult = random.nextInt(2);

        if (randomResult == 0)
        {
            answer = true;
            return rightAnswer;
        }
        else
        {
            answer = false;
            return random.nextInt(10);
        }
    }

    private void StartProgressBar()
    {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i <= 100;) {
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i=progressBar.getProgress()+1;
                    progressBar.setProgress(i);
                }
            }
        };
        thread.start();
    }

    public void btnYesClick(View view)
    {
        if (answer)
        {
            int progresBarValue=progressBar.getProgress();
            {
                if (progresBarValue<60)
                    progressBar.setProgress(0);
                else
                    progressBar.setProgress(progresBarValue-60);
            }
        }
        else
        {
            int progresBarValue=progressBar.getProgress();
            {
                if (progresBarValue<60)
                    progressBar.setProgress(0);
                else
                    progressBar.setProgress(progresBarValue-20);
            }
        }
        SetQuestion();

    }
    public void btnNoClick(View view)
    {
        if (!answer)
        {
            int progresBarValue=progressBar.getProgress();
            {
                if (progresBarValue<60)
                    progressBar.setProgress(0);
                else
                    progressBar.setProgress(progresBarValue-60);
            }
        }
        else
        {
            int progresBarValue=progressBar.getProgress();
            {
                if (progresBarValue<60)
                    progressBar.setProgress(0);
                else
                    progressBar.setProgress(progresBarValue-20);
            }
        }
        SetQuestion();
    }
}
