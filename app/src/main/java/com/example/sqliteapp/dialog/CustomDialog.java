package com.example.sqliteapp.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.sqliteapp.databinding.CustomDialogLayoutBinding;

public class CustomDialog extends AppCompatDialogFragment {

    private CustomDialogListener listener;
    private final String operationType;

    public CustomDialog(String operationType) {
        this.operationType = operationType;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        CustomDialogLayoutBinding binding = CustomDialogLayoutBinding.inflate(getLayoutInflater());
        builder.setView(binding.getRoot());
        builder.setTitle("Enter ID");
        binding.customDialogId.setHint("Enter User ID to " + operationType + " the record");

        builder.setPositiveButton("Proceed", (dialog, which) -> {
            int userId = Integer.parseInt(binding.customDialogId.getText().toString());
            listener.onPositiveButtonClick(userId);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

    // used inner interface to make it clear that this listener is specific to the functionality of the custom dialog
    public interface CustomDialogListener {
        void onPositiveButtonClick(int userId);
    }

    public void setCustomDialogListener(CustomDialogListener listener) {
        this.listener = listener;
    }
}
