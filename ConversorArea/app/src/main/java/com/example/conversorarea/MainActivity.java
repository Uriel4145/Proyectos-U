package com.example.conversorarea;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText inputValue;
    private Spinner inputUnit;
    private Spinner outputUnit;
    private TextView resultTextView;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        inputUnit = findViewById(R.id.inputUnit);
        outputUnit = findViewById(R.id.outputUnit);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> inputAdapter = ArrayAdapter.createFromResource(this, R.array.input_units, android.R.layout.simple_spinner_item);
        inputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnit.setAdapter(inputAdapter);
        inputUnit.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> outputAdapter = ArrayAdapter.createFromResource(this, R.array.output_units, android.R.layout.simple_spinner_item);
        outputAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        outputUnit.setAdapter(outputAdapter);
        outputUnit.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString();
                if (!input.isEmpty()) {
                    double area = Double.parseDouble(input);
                    convertArea(area);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Do nothing, as we are handling the conversion in the button click listener
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing, as we are handling the conversion in the button click listener
    }

    private void convertArea(double area) {
        String inputUnitText = inputUnit.getSelectedItem().toString();
        String outputUnitText = outputUnit.getSelectedItem().toString();

        double inputValueInMeters = convertToMeters(inputUnitText, area);
        double outputValue = convertFromMeters(outputUnitText, inputValueInMeters);

        resultTextView.setText(String.format("%.2f %s² = %.2f %s²", area, inputUnitText, outputValue, outputUnitText));
    }

    private double convertToMeters(String unit, double area) {
        switch (unit) {
            case "V²":
                return area * 0.092903;
            case "M²":
                return area;
            case "FT²":
                return area * 0.092903 * 0.092903;
            case "MZ":
                return area * 1000;
            case "Acre":
                return area * 4046.86;
            case "HA":
                return area * 10000;
            default:
                return 0;
        }
    }

    private double convertFromMeters(String unit, double area) {
        switch (unit) {
            case "V²":
                return area / 0.092903;
            case "FT²":
                return area / (0.092903 * 0.092903);
            case "MZ":
                return area / 1000;
            case "Acre":
                return area / 4046.86;
            case "HA":
                return area / 10000;
            default:
                return 0;
        }
    }

    @Override
    public void onClick(View v) {
        //...
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //...
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //...
    }
}