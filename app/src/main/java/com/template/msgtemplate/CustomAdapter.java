package com.template.msgtemplate;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    LayoutInflater mInflater;
    ArrayList<KeyMessage> keyMessages;

    public CustomAdapter(Context context,
                         ArrayList<KeyMessage> name) {
        setHasStableIds(true);
        this.mInflater = LayoutInflater.from(context);
        this.keyMessages = name;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView namaItemView;
        EditText dataNameView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            namaItemView = itemView.findViewById(R.id.keyName);
            dataNameView = itemView.findViewById(R.id.dataName);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(
                R.layout.rowview, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final KeyMessage current = keyMessages.get(position);

        holder.namaItemView.setText(current.getKey());
        holder.dataNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                current.setData(editable.toString());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return keyMessages.size();
    }

    public ArrayList<KeyMessage> getData(){
        return keyMessages;
    }
}
