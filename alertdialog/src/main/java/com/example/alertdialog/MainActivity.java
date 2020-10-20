package com.example.alertdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.viewButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_android_black_24dp)
                .setTitle(R.string.dialogTitle)
                .setMessage(R.string.dialogMessage)
                .setPositiveButton(R.string.dialogPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.dialogNegative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "CANCELED", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        dialog.show();
    }
}
