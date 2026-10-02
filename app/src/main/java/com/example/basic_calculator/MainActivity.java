/**
 * Programmers {
 *     Mario Aguilera Piceno - ID: 49998581
 * }
 * About: Handles the main interaction logic of the application.
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
    }

    /**
     * Assigns id to buttons
     *
     * @param btn
     * @param id
     */
    void assignID(MaterialButton btn, int id) {
        btn = findViewById(id);

    }

    /**
     * Handles button click logic
     *
     * @param v
     */
    public void onClick(View v) {

    }
}