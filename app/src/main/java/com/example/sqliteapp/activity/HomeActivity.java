package com.example.sqliteapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteapp.R;
import com.example.sqliteapp.adapter.UserDataAdapter;
import com.example.sqliteapp.databinding.ActivityHomeBinding;
import com.example.sqliteapp.dialog.CustomDialog;
import com.example.sqliteapp.model.UserDBHelper;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private UserDBHelper dbHelper;
    private UserDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        dbHelper = new UserDBHelper(this);
        adapter = new UserDataAdapter(this, dbHelper.getAllRecords());
        recyclerView.setAdapter(adapter);

        registerViews();
    }

    private void registerViews() {
        binding.add.setOnClickListener(clickListener);
        binding.update.setOnClickListener(clickListener);
        binding.delete.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = view -> {

        int id = view.getId();

        if (id == R.id.add) {
            addUser();
        } else if (id == R.id.update) {
            updateUser();
        } else if (id == R.id.delete) {
            deleteUser();
        }
    };

    private void addUser() {

        String name = binding.nameEditText.getText().toString().trim();
        int age = Integer.parseInt(binding.ageEditText.getText().toString());
        long number = Long.parseLong(binding.numberEditText.getText().toString());

        long rowId = dbHelper.addUserRecord(name, age, number);
        if (rowId != -1) {
            adapter.updateDisplayList(dbHelper.getAllRecords());
            Toast.makeText(this, "New User Added " + rowId, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUser() {

        CustomDialog dialog = createCustomDialog("update");

        dialog.setCustomDialogListener(userId -> {

            String name = binding.nameEditText.getText().toString().trim();
            int age = Integer.parseInt(binding.ageEditText.getText().toString());
            long number = Long.parseLong(binding.numberEditText.getText().toString());

            int rowsAffected = dbHelper.updateUserRecord(userId, name, age, number);
            if (rowsAffected != 0) {
                adapter.updateDisplayList(dbHelper.getAllRecords());
                Toast.makeText(HomeActivity.this, "User Updated", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show(getSupportFragmentManager(), "custom dialog");
    }

    private void deleteUser() {

        CustomDialog dialog = createCustomDialog("delete");

        dialog.setCustomDialogListener(userId -> {

            int result = dbHelper.deleteUserRecord(userId);
            if (result != 0) {
                adapter.updateDisplayList(dbHelper.getAllRecords());
                Toast.makeText(HomeActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show(getSupportFragmentManager(), "custom dialog");
    }

    private CustomDialog createCustomDialog(String operationType) {
        CustomDialog dialog = new CustomDialog(operationType);
        dialog.setCancelable(false);
        return dialog;
    }
}