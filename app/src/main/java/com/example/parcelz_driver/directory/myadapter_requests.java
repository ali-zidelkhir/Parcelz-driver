package com.example.parcelz_driver.directory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcelz_driver.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.annotations.NotNull;

public class myadapter_requests extends FirebaseRecyclerAdapter<MyRequest_Model, myadapter_requests.NoteHolder> {
    private AdapterView.OnItemClickListener listener;
    DatabaseReference databaseReference;
    String code;
    Context context;

    public myadapter_requests(@NonNull @NotNull FirebaseRecyclerOptions<MyRequest_Model> options) {
        super(options);
    }
    ///methode pour l'insert et l'update de la place du page


    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull MyRequest_Model model) {
        holder.textViewTitle.setText(model.getRequest_id());
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item_request,
                parent, false);
        return new NoteHolder(v);
    }


    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title_request);


        }
    }
}
