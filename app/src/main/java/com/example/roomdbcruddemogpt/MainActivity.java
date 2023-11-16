package com.example.roomdbcruddemogpt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);
        Button addButton = findViewById(R.id.buttonAdd);
        Button updateButton = findViewById(R.id.buttonUpdate);
        Button deleteButton = findViewById(R.id.buttonDelete);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        userAdapter = new UserAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                int age = Integer.parseInt(editTextAge.getText().toString().trim());

                User newUser = new User(name, age);
                userViewModel.insert(newUser);

                // Clear the input fields after adding a new user
                editTextName.setText("");
                editTextAge.setText("");
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement update logic using userViewModel.update()
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement delete logic using userViewModel.delete()
            }
        });

        // Observe the LiveData from the ViewModel to update the UI when the data changes
        userViewModel.getAllUsers().observe(this, new androidx.lifecycle.Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Update the RecyclerView adapter with the new data
                userAdapter.setUsers(users);
            }
        });
    }
}
