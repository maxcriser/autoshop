package com.example.maksim_zakharenka.autoshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.maksim_zakharenka.autoshop.callback.IConfirmation;

public final class DialogUtils {

    public static void showErrorDialog(final Context pContext, final String pTitle, final String pText) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(pContext);

        builder.setTitle(pTitle)
                .setMessage(pText)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static void showConfirmDialog(final Context pContext, final String pText, final IConfirmation pConfirmation) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(pContext);

        builder.setTitle("Подтверждение")
                .setMessage(pText)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(final DialogInterface dialog, final int id) {
                        if (pConfirmation != null) {
                            pConfirmation.onConfirm();
                        }

                        dialog.cancel();
                    }
                })
                .setNegativeButton("ОТМЕНИТЬ", new DialogInterface.OnClickListener() {

                    public void onClick(final DialogInterface dialog, final int id) {
                        if (pConfirmation != null) {
                            pConfirmation.onDismiss();
                        }

                        dialog.cancel();
                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }
}
