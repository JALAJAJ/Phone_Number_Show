package com.kingja.phonenumbershow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber();
    }

    private void phoneNumber() {
        EditText editText = (EditText) findViewById(R.id.et_phoneNumber);
        editText.addTextChangedListener(new PhoneNumberTextWatcher(editText));
    }
}
