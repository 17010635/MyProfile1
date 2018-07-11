package sg.edu.rp.c346.myprofile1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etGPA;
    TextView tvName, tvGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        tvName = findViewById(R.id.textViewName);
        tvGPA = findViewById(R.id.textViewGPA);
        rgGender = findViewById(R.id.radioGroupGender);
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        float floatGPA = Float.parseFloat(etGPA.getText().toString());
        int intGenderID = rgGender.getCheckedRadioButtonId();
        // Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        // Step 1d: Add the key-value pair
        prefEdit.putString("name",strName);
        prefEdit.putFloat("gpa", floatGPA);
        prefEdit.putInt("gender", intGenderID);
        // Step 1e: Call ocmmit() method to save the changes into the SharedPreferences
        prefEdit.commit();
        //
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String name = prefs.getString("name", "John");
        float gpa = prefs.getFloat("gpa", 0);
        int genderID = prefs.getInt("gender", R.id.radioButtonGenderMale);
        // Step 2c: Update the UI element with the value
        etName.setText(name);
        etGPA.setText(gpa+"");
        rgGender.check(genderID);
    }
}