package com.example.parcelz_driver.directory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcelz_driver.AcceptRequest;
import com.example.parcelz_driver.DB.DB_SQLITE;
import com.example.parcelz_driver.MainFrame;
import com.example.parcelz_driver.OTP;
import com.example.parcelz_driver.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class myadapter extends FirebaseRecyclerAdapter<Model, myadapter.NoteHolder> {
    private AdapterView.OnItemClickListener listener;
    DatabaseReference databaseReference;
String code;
    Context context;
    ArrayList<Model> list;
    DB_SQLITE db = new DB_SQLITE(context);

    ///methode pour l'insert et l'update de la place du page
    public void Work(String key) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Temp");
        temp ModelA = new temp(
                key
        );
        databaseReference.push().setValue(ModelA);
        Query query = databaseReference.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new

                                                     ValueEventListener() {
                                                         @Override
                                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                                             for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                                                 code = (childSnapshot.getKey());
                                                             }
                                                         }

                                                         @Override
                                                         public void onCancelled(DatabaseError databaseError) {
                                                             throw databaseError.toException();
                                                         }
                                                     });
        System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH success="+key);
        /*
        try {
            db.Delete("88");
            System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH ="+key);
            boolean result = (boolean) db.insertData(88, key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
    }

    public myadapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }
   /* public Model getItem(int position) {
        Model x =new Model();
        String m="";
        m=getSnapshots().getSnapshot(position).child("").getKey();
        x.setKey(m);
        return x;
    }*/

    @Override
    protected void onBindViewHolder(@NonNull NoteHolder holder, int position, @NonNull Model model) {
        holder.textViewTitle.setText(model.getTitle());
        holder.textViewDescription.setText(model.getWillaya() + ", " + model.getBaladia());
        holder.textViewPriority.setText(model.getType());

        String keyId = this.getRef(position).getKey();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("kkkkkkkkkkkkkkkkkkkk " + keyId);
                Toast.makeText(holder.itemView.getContext(), keyId, Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(v.getContext(), AcceptRequest.class);
                Work(keyId);
                v.getContext().startActivity(homeIntent);

            }
        });

    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,
                parent, false);
        return new NoteHolder(v);
    }


    class NoteHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPriority;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {

                    int pos = getAdapterPosition();

                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {

                        Model clickedDataItem = new Model();
                        //Toast.makeText(itemView.getContext(), "You clicked " + clickedDataItem.getTitle()
                        //          + " \nand this key: " + clickedDataItem.getKey(), Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }
    }
}
