package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
        TextView tvInputWindow = findViewById(R.id.tvInputWindow);
        TextView tvOutputWindow = findViewById(R.id.tvOutputWindow);

        TextView tvAC = findViewById(R.id.tvAC);
        TextView tvClear = findViewById(R.id.tvClear);
        TextView tvMod = findViewById(R.id.tvMod);
        TextView tvDiv = findViewById(R.id.tvDiv);

        TextView tv7 = findViewById(R.id.tv7);
        TextView tv8 = findViewById(R.id.tv8);
        TextView tv9 = findViewById(R.id.tv9);
        TextView tvMul = findViewById(R.id.tvMul);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        TextView tv6 = findViewById(R.id.tv6);
        TextView tvSub = findViewById(R.id.tvSub);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tvAdd = findViewById(R.id.tvAdd);
        TextView tv00 = findViewById(R.id.tv00);
        TextView tv0 = findViewById(R.id.tv0);
        TextView tvEqual = findViewById(R.id.tvEqual);


        tv0.setOnClickListener(v -> {
            tvInputWindow.append("0");

        });

        tv00.setOnClickListener(v -> {
            tvInputWindow.append("00");
        });

        tv1.setOnClickListener(v -> {
            tvInputWindow.append("1");
        });

        tv2.setOnClickListener(v -> {
            tvInputWindow.append("2");
        });

        tv3.setOnClickListener(v -> {
            tvInputWindow.append("3");
        });

        tv4.setOnClickListener(v -> {
            tvInputWindow.append("4");
        });

        tv5.setOnClickListener(v -> {
            tvInputWindow.append("5");
        });

        tv6.setOnClickListener(v -> {
            tvInputWindow.append("6");
        });

        tv7.setOnClickListener(v -> {
            tvInputWindow.append("7");
        });

        tv8.setOnClickListener(v -> {
            tvInputWindow.append("8");
        });

        tv9.setOnClickListener(v -> {
            tvInputWindow.append("9");
        });

        tvAdd.setOnClickListener(v -> {
            tvInputWindow.append(getString(R.string.add));
        });

        tvSub.setOnClickListener(v -> {
            tvInputWindow.append(getString(R.string.sub));
        });

        tvMul.setOnClickListener(v -> {
            tvInputWindow.append(getString(R.string.mul));
        });

        tvDiv.setOnClickListener(v -> {
            tvInputWindow.append(getString(R.string.div));
        });

        tvMod.setOnClickListener(v -> {
            tvInputWindow.append(getString(R.string.mod));
        });

        tvClear.setOnClickListener(v -> {
            String input = tvInputWindow.getText().toString();
            if (!input.isEmpty()) {
                tvInputWindow.setText(input.substring(0, input.length() - 1));
            }
        });

        tvAC.setOnClickListener(v -> {
            tvInputWindow.setText("");
            tvOutputWindow.setText("");
        });

        tvEqual.setOnClickListener(v -> {
            String expression = tvInputWindow.getText().toString();
            String input = expression.replace(getString(R.string.add), " + ")
                    .replace(getString(R.string.sub), " - ")
                    .replace(getString(R.string.mul), " * ")
                    .replace(getString(R.string.div), " / ")
                    .replace("%", " % ");
            if (!input.isEmpty()) {
                try {
                    String result = evaluateExpression(input);
                    tvOutputWindow.setText(result);
                } catch (Exception e) {
                    tvOutputWindow.setText(R.string.error);
                }
            }
        });


    }

    private String evaluateExpression(String input) {
        String[] tokens = input.split(" ");

        String result = tokens[0];
        for (int i = 1; i < tokens.length; i += 2) {

            String operator = tokens[i];

            String operand = tokens[i + 1];
            switch (operator) {

                case "+":
                    result = String.valueOf(Integer.parseInt(result) + Integer.parseInt(operand));
                    break;
                case "-":
                    result = String.valueOf(Integer.parseInt(result) - Integer.parseInt(operand));
                    break;
                case "*":
                    result = String.valueOf(Integer.parseInt(result) * Integer.parseInt(operand));
                    break;
                case "/":
                    result = String.valueOf(Double.parseDouble(result) / Double.parseDouble(operand));
                    break;
                case "%":
                    result = String.valueOf((Double.parseDouble(result) / 100) * Double.parseDouble(operand));
                    break;
            }
        }
        return result;
    }
}