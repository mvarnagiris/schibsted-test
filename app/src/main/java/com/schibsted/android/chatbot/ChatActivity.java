package com.schibsted.android.chatbot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.schibsted.android.chatbot.model.ChatMessage;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private static ArrayAdapter mAdapter;
    private static final String jsonUrl = "https://s3-eu-west-1.amazonaws.com/rocket-interview/chat.json";

    // Construct the data source
    private static ArrayList<ChatMessage> arrayOfMessages = new ArrayList<ChatMessage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Set the title on screen
        setTitle("Chat");

        // Create the adapter to convert the array to views
        mAdapter = new ChatAdapter(this, arrayOfMessages);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lvMessages);
        if (listView != null) {
            listView.setAdapter(mAdapter);
        }
    }
}
