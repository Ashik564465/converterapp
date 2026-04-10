package com.example.unitconverter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner spinner;
    private TextView resultText;

    private final String[] conversions = {
            "Kilometer to Meter",
            "Celsius to Fahrenheit",
            "Kilogram to Gram"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        spinner = findViewById(R.id.spinnerConversion);
        Button convertBtn = findViewById(R.id.btnConvert);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                conversions
        );

        spinner.setAdapter(adapter);

        convertBtn.setOnClickListener(v -> {
            String input = inputValue.getText().toString();

            if (input.isEmpty()) {
                resultText.setText(R.string.msg_enter_value);
                return;
            }

            try {
                double value = Double.parseDouble(input);
                String selected = spinner.getSelectedItem().toString();

                double result;
                String resultStr;

                switch (selected) {
                    case "Kilometer to Meter":
                        result = value * 1000;
                        resultStr = String.format(Locale.getDefault(), "%.2f %s", result, getString(R.string.unit_meters));
                        break;

                    case "Celsius to Fahrenheit":
                        result = (value * 9 / 5) + 32;
                        resultStr = String.format(Locale.getDefault(), "%.2f %s", result, getString(R.string.unit_fahrenheit));
                        break;

                    case "Kilogram to Gram":
                        result = value * 1000;
                        resultStr = String.format(Locale.getDefault(), "%.2f %s", result, getString(R.string.unit_grams));
                        break;

                    default:
                        resultStr = getString(R.string.msg_invalid_selection);
                }
                resultText.setText(resultStr);
            } catch (NumberFormatException e) {
                resultText.setText(R.string.msg_enter_value);
            }
        });
    }
}