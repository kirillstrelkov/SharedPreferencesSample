package com.example.kirill.sharedpreferencessample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int currentRate;
    private int highestRate;
    private TextView txtHighestRate;
    private TextView txtCurrentRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        currentRate = preferences.getInt("currentRate", 0);
        highestRate = preferences.getInt("highestRate", 0);

        txtCurrentRate = (TextView) findViewById(R.id.txtCurrentRate);
        txtHighestRate = (TextView) findViewById(R.id.txtHighestRate);

        updateAndSaveRates();
    }

    private void updateAndSaveRates() {
        txtCurrentRate.setText("Current rate: " + currentRate);
        txtHighestRate.setText("Highest rate: " + highestRate);
        saveRates();
    }

    private void saveRates() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("currentRate", currentRate);
        editor.putInt("highestRate", highestRate);
        editor.commit();
    }

    public void addRate(View view) {
        currentRate++;
        if (currentRate > highestRate) {
            highestRate++;
        }

        updateAndSaveRates();
    }

    public void clearCurrentRate(View view) {
        currentRate = 0;
        updateAndSaveRates();
    }

    public void clearAllRates(View view) {
        currentRate = 0;
        highestRate = 0;
        updateAndSaveRates();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveRates();
    }
}
