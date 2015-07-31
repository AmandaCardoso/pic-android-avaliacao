package com.example.administrador.projeto1.util;

import android.content.Context;
import android.widget.EditText;

import com.example.administrador.projeto1.R;

/**
 * Created by Administrador on 22/07/2015.
 */
public final class FormHelper {
    private FormHelper(){
        super();
    }
    public static boolean requiredValidate(Context context,EditText... editTexts) {
        boolean valid = true;
        for (EditText editText : editTexts) {
            String value = editText.getText() == null ? null : editText.getText().toString();
            if (value == null || value.trim().isEmpty()) {
                String errorMessage = context.getString(R.string.required);
                editText.setError(errorMessage);
                valid = false;
            }
        }
        return valid;
    }

}
