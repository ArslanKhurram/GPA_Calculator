package com.example.gpa_calculator;

/*
    Author: Arslan Khurram
    Date: 09/17/19
    File: MainActivity.java
 */
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText[] grade = new EditText[5]; //initialize array to link 5 Edit Text views
    TextView tvGPA;
    Button btnCalcGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grade[0] = findViewById(R.id.et_Grade1); //link edit text views to arrays
        grade[1] = findViewById(R.id.et_Grade2);
        grade[2] = findViewById(R.id.et_Grade3);
        grade[3] = findViewById(R.id.et_Grade4);
        grade[4] = findViewById(R.id.et_Grade5);
        tvGPA = findViewById(R.id.finalGPA);
        btnCalcGPA = findViewById(R.id.btn_computeGPA);

    }
    /*
        Function: emptyFieldValidation(View view)
        Purpose: Test for empty views and change to red if found
     */
    public boolean emptyFieldValidation(View view) {
        Boolean val = true;
        for (int i = 0; i < 5; i++) { //loop through all edit text views
            if (grade[i].getText().toString().isEmpty()) { //set view to red if empty
                grade[i].setBackgroundColor(Color.rgb(255, 99, 71));
                val = false;
            } else {
                grade[i].setBackgroundColor(Color.TRANSPARENT);
                val = true;
            }
        }
        return val;
    }
    /*
        Function: resetLayout(View view)
        Purpose: Reset to default layout when user presses edit text view after computing GPA
     */
    public void resetLayout(View view){
        if (!btnCalcGPA.isClickable()) { //test if "Compute GPA" button was pressed
            for (int i = 0; i < 5; i++) {
                grade[i].setBackgroundColor(Color.TRANSPARENT);
            }
            getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            btnCalcGPA.setClickable(true);
            btnCalcGPA.setTextColor(Color.BLACK);
            btnCalcGPA.setBackgroundResource(android.R.drawable.btn_default);
        }
    }
    /*
        Function: computeGPA(View view)
        Purpose: Display GPA, Change background color
     */
    public void computeGPA(View view) {
        DecimalFormat df = new DecimalFormat(".00"); //used to round 2 decimal places
        double sumGPA = 0, gpa;

        if (!emptyFieldValidation(view)) { //Call function, if return is false snackbar message will notify user of ERROR
            Snackbar.make(view, "Empty Fields Detected", Snackbar.LENGTH_LONG).
                    setAction("OK", v ->
                            Toast.makeText(MainActivity.this, "Please fill all RED fields", Toast.LENGTH_LONG).show()).show();
        } else {
            for (int i = 0; i < 5; i++) //calculate sum of grades
                sumGPA += Double.parseDouble(grade[i].getText().toString());
            gpa = sumGPA / 5; //calculate final GPA

            //Change background of app to 'RED', 'YELLOW', 'GREEN' depending on what range GPA falls into
            if (gpa < 60)
                getWindow().getDecorView().setBackgroundColor(Color.rgb(255,99,71));
            else if(gpa>=61 && gpa<=79)
                getWindow().getDecorView().setBackgroundColor(Color.rgb(255,255,153));
            else if(gpa>=80)
                getWindow().getDecorView().setBackgroundColor(Color.rgb(124,252,0));
            btnCalcGPA.setBackgroundColor(Color.TRANSPARENT); btnCalcGPA.setTextColor(Color.TRANSPARENT); btnCalcGPA.setClickable(false); //disable and clear button
            tvGPA.setText(df.format(gpa)); //display GPA
        }

    }
}
