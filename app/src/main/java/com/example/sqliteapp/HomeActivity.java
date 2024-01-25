package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqliteapp.adapter.UserDataAdapter;
import com.example.sqliteapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityHomeBinding binding;
    private UserDBHelper dbHelper;
    private UserDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new UserDBHelper(this);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new UserDataAdapter(this, dbHelper.getAllRecords());
        recyclerView.setAdapter(adapter);

        binding.add.setOnClickListener(this);
        binding.update.setOnClickListener(this);
        binding.delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.add) {

            boolean isUserAdded = addUser();

            if (isUserAdded) {
                adapter.updateDisplayList(dbHelper.getAllRecords());
                Toast.makeText(this, "New User Added", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.update) {

            int rowsAffected = updateUser();

            if (rowsAffected != 0) {
                adapter.updateDisplayList(dbHelper.getAllRecords());
                Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.delete) {

            boolean isUserDeleted = deleteUser();

            if (isUserDeleted) {
                adapter.updateDisplayList(dbHelper.getAllRecords());
                Toast.makeText(this, "User Deleted ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean addUser() {

        String name = binding.nameEditText.getText().toString().trim();
        int age = Integer.parseInt(binding.ageEditText.getText().toString());
        long number = Long.parseLong(binding.numberEditText.getText().toString());

        return dbHelper.addUserRecord(name, age, number) != -1;
    }

    private int updateUser() {

        int userId = Integer.parseInt(binding.idEditText.getText().toString());
        String name = binding.nameEditText.getText().toString().trim();
        int age = Integer.parseInt(binding.ageEditText.getText().toString());
        long number = Long.parseLong(binding.numberEditText.getText().toString());

        return dbHelper.updateUserRecord(userId, name, age, number);
    }

    private boolean deleteUser() {
        int userId = Integer.parseInt(binding.idEditText.getText().toString());
        return dbHelper.deleteUserRecord(userId) != 0;
    }

}