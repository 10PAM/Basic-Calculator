/**
 * Programmers {
 *     Mario Aguilera Piceno - ID: 49998581
 * }
 * About: Handles the main interaction logic of the application.
 * Date: 09/30/2026
 * Modified: 10/2/2026
 */

package com.example.basic_calculator;
//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.faendir.rhino_android.*;
import org.mozilla.javascript.*;


/**
 * Main App Class: MainActivity
 */
public class MainActivity extends AppCompatActivity {

    TextView resultTV, solutionTV;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonMul, buttonPlus, buttonSub, buttonDivide, buttonEquals;
    MaterialButton buttonAC, buttonDot;

    /**
     * Program start..
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        resultTV = findViewById(R.id.result_tv);
        solutionTV = findViewById(R.id.solution_tv);

        assignID(buttonC, R.id.button_c);
        assignID(buttonBrackOpen, R.id.button_open_bracket);
        assignID(buttonBrackClose, R.id.button_close_bracket);

        assignID(button0, R.id.button_0);
        assignID(button1, R.id.button_1);
        assignID(button2, R.id.button_2);
        assignID(button3, R.id.button_3);
        assignID(button4, R.id.button_4);
        assignID(button5, R.id.button_5);
        assignID(button6, R.id.button_6);
        assignID(button7, R.id.button_7);
        assignID(button8, R.id.button_8);
        assignID(button9, R.id.button_9);

        assignID(buttonMul, R.id.button_c);
        assignID(buttonPlus, R.id.button_plus);
        assignID(buttonSub, R.id.button_minus);
        assignID(buttonDivide, R.id.button_divide);
        assignID(buttonEquals, R.id.button_equals);

        assignID(buttonAC, R.id.button_ac);
        assignID(buttonDot, R.id.button_dot);
    }

    /**
     * Assigns id to buttons
     *
     * @param btn
     * @param id
     */
    void assignID(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this::onClick);
    }

    /**
     * Handles button click logic
     *
     * @param view
     */
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalc = solutionTV.getText().toString();

        if(buttonText.equals("AC")) {
            solutionTV.setText("");
            solutionTV.setText("0");
            return;
        }

        if(buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }

        if(buttonText.equals("C")) {
            dataToCalc = dataToCalc.substring(0, dataToCalc.length() - 1);
        } else {
            dataToCalc = dataToCalc + buttonText;
        }

        solutionTV.setText(dataToCalc);

        String finalResult = getResults(dataToCalc);
        if(!finalResult.equals("Err")) {
            resultTV.setText(finalResult);
        }
    }

    String getResults(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            return context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

        } catch (Exception e) {
            return "Err";
        }
    }
}