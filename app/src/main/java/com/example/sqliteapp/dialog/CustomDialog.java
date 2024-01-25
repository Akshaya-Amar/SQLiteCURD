package com.example.sqliteapp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.sqliteapp.listener.CustomDialogListener;
import com.example.sqliteapp.databinding.CustomDialogLayoutBinding;

public class CustomDialog extends AppCompatDialogFragment {

    private CustomDialogListener listener;
    private final String operation;

    public CustomDialog(String operation) {
        this.operation = operation;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        CustomDialogLayoutBinding binding = CustomDialogLayoutBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());
        binding.customDialogId.setHint("Enter User ID to " + operation + " the record");

        builder.setTitle("Enter ID");
        builder.setPositiveButton("Proceed", (dialog, which) -> {
            int userId = Integer.parseInt(binding.customDialogId.getText().toString());
            listener.onPositiveButtonClick(userId);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

    public void setCustomDialogListener(CustomDialogListener listener) {
        this.listener = listener;
    }
}
