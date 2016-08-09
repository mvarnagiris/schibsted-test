package com.schibsted.android.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.schibsted.android.chatbot.model.ChatMessage;

import java.util.ArrayList;

/**
 * Created by claudiopalumbo on 27/04/2016.
 */
public class ChatAdapter extends ArrayAdapter<ChatMessage> {
        // View lookup cache
        private static class ViewHolder {

        }

        public ChatAdapter(Context context, ArrayList<ChatMessage> messages) {
            super(context, R.layout.item_message, messages);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatMessage chatMessage = getItem(position);


            // Return the completed view to render on screen
            return convertView;
        }
}
