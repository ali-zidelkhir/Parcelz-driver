package com.example.parcelz_driver;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.parcelz_driver.DB.DB_SQLITE;
import com.example.parcelz_driver.directory.MyRequest_Model;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AcceptRequest extends AppCompatActivity {
    String Key = "";
    private GoogleMap map;
    private CameraPosition cameraPosition;
    Geocoder geocoder;
    Switch switchFrangible;
    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;
    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;
    StepView stepView;
    double LATT, LONGG, LATTDEST, LONGGDEST;
    RadioButton PriceBase, TimeBase, radioDelivery, radioPickup;
    TextView days, pricing;

    String KEY = "";
    TextView TitleET, DimensionET, PickupAddressET, DestinationAddressET, FrangibleET, DeliveryModeET,
            CashOnET, durationET, distanceET, costET;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    DB_SQLITE db = new DB_SQLITE(this);
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_request);

        TitleET = findViewById(R.id.Title);
        DimensionET = findViewById(R.id.Dimension);
        PickupAddressET = findViewById(R.id.PickupAddress);
        DestinationAddressET = findViewById(R.id.DestinationAddress);
        FrangibleET = findViewById(R.id.Frangible);
        DeliveryModeET = findViewById(R.id.DeliveryMode);
        CashOnET = findViewById(R.id.CashOn);
        durationET = findViewById(R.id.duration);
        distanceET = findViewById(R.id.distance);
        costET = findViewById(R.id.cost);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Temp");
        Query query = databaseReference.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Key = getCode((Map<String, Object>) dataSnapshot.getValue());
                //Key = String.valueOf(dataSnapshot.child("key").getValue());
                System.out.println("le key inside is : " + Key.toString());

                try {
                    Thread.sleep(500);
                    System.out.println("))))))))))))))))))))))))  " + Key);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Send_Details_A");

                    reference.orderByKey().equalTo(Key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                String title = datas.child("title").getValue(String.class);
                                String willaya = datas.child("willaya").getValue(String.class);
                                System.out.println(")))))))))))))))))))))))) :: " + title);
                                System.out.println(")))))))))))))))))))))))) :: " + willaya);

                                ////////
                                String Title = datas.child("title").getValue(String.class);
                                String H = datas.child("h").getValue(String.class);
                                String W = datas.child("w").getValue(String.class);
                                String L = datas.child("l").getValue(String.class);
                                String pickupAddress = datas.child("current_willaya").getValue().toString() + ", "
                                        + datas.child("current_baladia").getValue().toString();
                                String Destination = datas.child("willaya").getValue(String.class)
                                        + ", " + datas.child("baladia").getValue(String.class);
                                String Frangible = datas.child("frangible").getValue(String.class);
                                String DeliverMode = datas.child("delivery_mode").getValue(String.class);
                                String CashOn = datas.child("cash_on").getValue(String.class);
                                String days = datas.child("duration").getValue().toString();
                                double distance = (double) datas.child("distance").getValue();
                                double price = (double) datas.child("price").getValue();
                                System.out.println(")))))))))))))))))))))))) :: " + H);
                                System.out.println(")))))))))))))))))))))))) :: " + W);
                                System.out.println(")))))))))))))))))))))))) :: " + L);
                                System.out.println(")))))))))))))))))))))))) :: " + Destination);
                                System.out.println(")))))))))))))))))))))))) :: " + Frangible);
                                //// here i will fill my text fields
                                TitleET.setText(Title.toString());
                                DimensionET.setText(H + "cm x " + W + "cm x " + L + "cm x ");
                                PickupAddressET.setText(pickupAddress);
                                DestinationAddressET.setText(Destination.toString());
                                FrangibleET.setText(Frangible.toString());
                                DeliveryModeET.setText(DeliverMode.toString());
                                CashOnET.setText(CashOn.toString());
                                durationET.setText(days);
                                distanceET.setText(String.format("%.2f", distance));
                                costET.setText(String.format("%.2f", price));


                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


    }


    private String getCode(Map<String, Object> users) {

        ArrayList<String> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get("key"));
        }
        Key = phoneNumbers.get(0).toString();
        System.out.println("key inside " + phoneNumbers.toString());
        return Key;
    }

    private void getWillaya(Map<String, Object> users) {

        ArrayList<String> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get("baladia"));
        }

        System.out.println("baladia " + phoneNumbers.toString());
    }

    String MyCode = "";

    public void Next(View view) {

        //create Model Data
        MyRequest_Model ModelA = new MyRequest_Model(
                "", ""
        );

        databaseReference.push().setValue(ModelA);
        Query query = databaseReference.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            MyCode = childSnapshot.getKey();

                            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                            rootRef.child("MyRequest")
                                    .child(MyCode)
                                    .child("driver_id")
                                    .setValue(currentFirebaseUser.getUid() );
                            rootRef.child("MyRequest")
                                    .child(MyCode)
                                    .child("request_id")
                                    .setValue(Key);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        throw databaseError.toException();
                    }
                });

    }
}