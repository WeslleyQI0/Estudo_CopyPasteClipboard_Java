package com.qi0.weslley.estudo_copypasteclipboard_java;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtText;
    private Button btCopy;
    private Button btPaste;
    private TextView tvTextCopied;

    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        btCopy = findViewById(R.id.btCopy);
        btPaste = findViewById(R.id.btPaste);
        tvTextCopied = findViewById(R.id.tvTxtCopied);

        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        if (!clipboardManager.hasPrimaryClip()){
            btPaste.setEnabled(false);
        }

        btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edtText.getText().toString();

                if (!text.equals("")){
                    ClipData clipData = ClipData.newPlainText("text", text);
                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                    btPaste.setEnabled(true);
                    btPaste.setVisibility(View.VISIBLE);
                }
            }
        });

        btPaste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = clipboardManager.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);
                
                tvTextCopied.setText(item.getText().toString());
                Toast.makeText(MainActivity.this, "Pasted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
