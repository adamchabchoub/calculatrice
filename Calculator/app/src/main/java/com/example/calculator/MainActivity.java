package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText resultat;
    private String operateur = "";
    private String calcul = "";
    private double operand1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultat = findViewById(R.id.resultat);
    }

    public void onNumero(View view) {
        Button button = (Button) view;
        calcul += button.getText().toString();
        resultat.append(button.getText().toString());
    }

    public void onOperateur(View view) {
        Button button = (Button) view;
        String nouveau = button.getText().toString();

        if (!calcul.isEmpty() && operateur.isEmpty()) {
            operand1 = Double.parseDouble(calcul);
            operateur = nouveau;
            resultat.append(operateur);
            calcul = "";
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }

    public void onReinitialiser(View view) {
        calcul = "";
        operateur = "";
        operand1 = 0;
        resultat.setText("");

    }
    public void onVirgule(View view) {
        if (!calcul.contains(".")) {
            calcul += ".";
            resultat.append(".");
        }
    }

    public void onResultat(View view) {
        if (!calcul.isEmpty() && !operateur.isEmpty()) {
            double operand2 = Double.parseDouble(calcul);
            double result = 0;
            switch (operateur) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }
            if (result % 1 == 0) {
                resultat.setText(String.valueOf((int) result));
            } else {
                resultat.setText(String.valueOf(result));
            }
            calcul = String.valueOf(result);
            operand1 = result;
            operateur = "";
        }
    }


}
