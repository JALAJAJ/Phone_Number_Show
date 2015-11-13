/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kingja.phonenumbershow;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Administrator on 2015-11-13.
 */
public class PhoneNumberTextWatcher implements TextWatcher {
    private EditText editText;
    private boolean mDelte;

    public PhoneNumberTextWatcher(EditText editText) {
        this.editText = editText;
        PhoneNumberTextWatcher.this.editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                /**
                 * 加外层判断可以屏蔽调用两次
                 */
                if (event.getAction()==KeyEvent.ACTION_DOWN){
                    if (keyCode==KeyEvent.KEYCODE_DEL){
                        mDelte = true;
                    }
                }

                return false;
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {



    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if (mDelte){
            mDelte=false;
            return;
        }
        String inputNumber = s.toString().replace("-", "");
        String a = "";
        String b = "";
        String c = "";
        if (inputNumber.length()>=3){
            a = inputNumber.substring(0, 3);
        }else if (inputNumber.length() <3) {
            a = inputNumber.substring(0, inputNumber.length());
        }
        if (inputNumber.length() >= 7) {
            b = inputNumber.substring(3, 7);
            c = inputNumber.substring(7, inputNumber.length());
        }else if(inputNumber.length() >3 && inputNumber.length() <7){
            b = inputNumber.substring(3, inputNumber.length());
        }
        StringBuilder sb = new StringBuilder();
        if (a.length() > 0) {
            sb.append(a);
            if (a.length() == 3) {
                sb.append("-");
            }
        }
        if (b.length() > 0) {
            sb.append(b);
            if (b.length() == 4) {
                sb.append("-");
            }
        }
        if (c.length() > 0) {
            sb.append(c);
        }
        editText.removeTextChangedListener(this);
        editText.setText(sb.toString());
        editText.setSelection(editText.getText().toString().length());
        editText.addTextChangedListener(this);
    }
}
