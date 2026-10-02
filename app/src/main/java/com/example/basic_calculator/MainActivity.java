/**
 * Programmers {
 *     Mario Aguilera Piceno - CWU ID: 49998581
 * }
 * About: Handles the main interaction logic of the application. Based on Professor Zhu's
 * video lectures.
 * Date: 09/30/2026
 * Modified: 10/2/2026
 */

package com.example.basic_calculator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.*;

//import android.content.Context; // Unused


/**
 * Main App Class: MainActivity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * UI Fields
     */
    TextView resultTV, solutionTV;

    /**
     * Default settings
     */
    private boolean firstInteraction = true;

    /**
     * App start.
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

        assignID(R.id.button_c);
        assignID(R.id.button_open_bracket);
        assignID(R.id.button_close_bracket);

        assignID(R.id.button_0);
        assignID(R.id.button_1);
        assignID(R.id.button_2);
        assignID(R.id.button_3);
        assignID(R.id.button_4);
        assignID(R.id.button_5);
        assignID(R.id.button_6);
        assignID(R.id.button_7);
        assignID(R.id.button_8);
        assignID(R.id.button_9);

        assignID(R.id.button_mul);
        assignID(R.id.button_plus);
        assignID(R.id.button_minus);
        assignID(R.id.button_divide);
        assignID(R.id.button_equals);

        assignID(R.id.button_ac);
        assignID(R.id.button_dot);
    }

    /**
     * Assigns button click listener to buttons.
     *
     * @param id The id of the button.
     */
    void assignID(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this::onClick);
    }

    /**
     * Handles button click logic
     *
     * @param view The button being clicked on.
     */
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();

        // Handles first interaction needs
        if (firstInteraction) {
            solutionTV.setText("");
            firstInteraction = false;
        }

        String dataToCalc = solutionTV.getText().toString();

        // Clears Buffer Fully
        if(buttonText.equals("AC")) {
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }

        // Calculates
        if(buttonText.equals("=")) {
            solutionTV.setText(resultTV.getText());
            return;
        }

        // Clears Buffer by 1
        if(buttonText.equals("C")) {
            if (dataToCalc.length() >= 2) {
                dataToCalc = dataToCalc.substring(0, dataToCalc.length() - 1);
            } else {
                solutionTV.setText("");
                resultTV.setText("0");
                return;
            }
        } else {
            if (dataToCalc.length() == 1 && dataToCalc.equals("0")) {
                dataToCalc = buttonText;
            } else {
                dataToCalc += buttonText;
            }
        }

        // Updates user input visuals
        solutionTV.setText(dataToCalc);

        // Outputs calculated results
        String finalResult = getResults(dataToCalc);
        if(!finalResult.equals("Err")) {
            resultTV.setText(finalResult);
        }
    }

    /**
     * Performs calculations on input data.
     *
     * @param data The data containing the calculation input.
     * @return Returns the final calculation as a String.
     */
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