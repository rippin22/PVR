package com.in.ripp.pvr.Views;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.in.ripp.pvr.R;

import static android.content.ContentValues.TAG;

public class DialogUtil extends DialogFragment {

    public DialogUtil(){

    }

    public AlertDialog createDialogListOfItemsWithOk(final CharSequence[] options, Context context, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_header_title);
        builder.setItems(options, listener);
        return builder.create();
    }
}
