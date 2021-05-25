package com.ayonmotors.ayonmotors;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class Functions {

    AlertDialog dialog;
    public  void showDialogue(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflater.inflate(R.layout.progressbar,null,false);
        builder.setView(vi);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }
    public void dismissdialogue(){
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }


}
