package com.template.msgtemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<KeyMessage> keys;
    private Button send, save, cek;
    private CustomAdapter mAdapter;
    private String templateMsg;
    private TextView templateText, openEditTemplate, preview;
    private LinearLayout mainMsg, editTemplateMsg;
    private EditText newTemplateText, number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiation
        send = findViewById(R.id.send);
        templateText = findViewById(R.id.templateText);
        mainMsg = findViewById(R.id.mainMsg);
        editTemplateMsg = findViewById(R.id.editTemplateMsg);
        openEditTemplate = findViewById(R.id.openEditTemplate);
        newTemplateText = findViewById(R.id.newTemplateText);
        preview = findViewById(R.id.preview);
        save = findViewById(R.id.save);
        cek = findViewById(R.id.cek);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        number = findViewById(R.id.number);

        //onclick
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMsg(rebuildText());
            }
        });
        openEditTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTemplateText.setText(templateMsg);
                mainMsg.setVisibility(View.GONE);
                editTemplateMsg.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                templateMsg = newTemplateText.getText()+"";
                initKeys();
                Toast.makeText(view.getContext(), "Saved.", Toast.LENGTH_SHORT).show();
                mainMsg.setVisibility(View.VISIBLE);
                editTemplateMsg.setVisibility(View.GONE);
            }
        });
        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preview.setText(rebuildText());
            }
        });

        //Text data
        templateMsg = "Hallo [nama] dari kelas [kelas]";
        initKeys();
    }

    private void initKeys(){
        keys = new ArrayList<KeyMessage>();
        templateText.setText(templateMsg);
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(templateMsg);
        while (m.find()) {
            keys.add(new KeyMessage(m.group(1) + ""));
        }
        initRecycleView();
    }
    private void initRecycleView(){
        //Recycle view init
        mAdapter = new CustomAdapter(this, keys);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void sendMsg(String msg) {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String (number.getText()+""));
        smsIntent.putExtra("sms_body"  , msg);
        startActivity(smsIntent);
        Toast.makeText(getApplicationContext(), "Cek the Message!",
                Toast.LENGTH_SHORT).show();
    }
    private String rebuildText(){
        ArrayList<KeyMessage> data = mAdapter.getData();
        String finalMsg = templateMsg;
        for (int i = 0; i < data.size(); i++) {
            KeyMessage key = data.get(i);
            finalMsg = finalMsg.replace('['+key.getKey()+']',key.getData());
        }
        return finalMsg;
    }
}
