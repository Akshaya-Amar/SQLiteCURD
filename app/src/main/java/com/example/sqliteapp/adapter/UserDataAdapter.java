package com.example.sqliteapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteapp.model.UserContract;
import com.example.sqliteapp.databinding.UserItemsBinding;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.ViewHolder> {

    private final Context context;
    private Cursor cursor;

    public UserDataAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemsBinding binding = UserItemsBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (cursor.moveToPosition(position)) {

            int idColumnIndex = cursor.getColumnIndex(UserContract.UserEntry._ID);
            holder.binding.idTextView.setText(String.valueOf(cursor.getInt(idColumnIndex)));

            int nameColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME);
            holder.binding.nameTextView.setText(cursor.getString(nameColumnIndex));

            int ageColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_AGE);
            holder.binding.ageTextView.setText(String.valueOf(cursor.getInt(ageColumnIndex)));

            int numberColumnIndex = cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NUMBER);
            holder.binding.numberTextView.setText(String.valueOf(cursor.getLong(numberColumnIndex)));
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void updateDisplayList(Cursor newCursor) {
        updateCursor(newCursor);
        notifyDataSetChanged();
    }

    private void updateCursor(Cursor newCursor) {

        if (cursor != null) {
            cursor.close();
        }

        this.cursor = newCursor;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final UserItemsBinding binding;

        public ViewHolder(@NonNull UserItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
