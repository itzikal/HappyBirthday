package com.alkotzer.itzik.happybirthday.mainActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.alkotzer.itzik.happybirthday.R;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class SelectImageSourceDialog
{
    public interface OnImageSourceSelected
    {
        void onGallerySelected();
        void onCameraSelected();

    }
    public void show(Activity context, final OnImageSourceSelected listener)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_choose_source, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Choose image from");
        alertDialog.setView(dialogView);
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();
        dialogView.findViewById(R.id.from_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                if(listener != null)
                {
                    listener.onCameraSelected();
                }

                alertDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.from_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v)
            {
                if(listener != null)
                {
                    listener.onGallerySelected();
                }
                alertDialog.dismiss();
            }
        });
    }
}
