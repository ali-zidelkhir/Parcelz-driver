package com.example.parcelz_driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.parcelz_driver.DB.DB_SQLITE;
import com.example.parcelz_driver.Models.DetailsFrameA;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shuhart.stepview.StepView;

import org.jetbrains.annotations.NotNull;

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
        DimensionET = findViewById(R.id.Title);
        PickupAddressET = findViewById(R.id.Title);
        DestinationAddressET = findViewById(R.id.Title);
        FrangibleET = findViewById(R.id.Title);
        DeliveryModeET = findViewById(R.id.Title);
        CashOnET = findViewById(R.id.Title);
        durationET = findViewById(R.id.Title);
        distanceET = findViewById(R.id.Title);
        costET = findViewById(R.id.Title);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Temp");
        Query query = databaseReference.orderByKey().limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Key = getCode((Map<String, Object>) dataSnapshot.getValue());
                //Key = String.valueOf(dataSnapshot.child("key").getValue());
                System.out.println("le key inside is : " + Key.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
        try {
            Thread.sleep(1000);
            databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Send_Details_A").child(Key);
            Query querye = databaseReference2.orderByKey().limitToLast(1);
            querye.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot1) {
                    System.out.println("le key inside is : 2222 " + Key.toString());
                       /* try {
                            Object Title=dataSnapshot.child("title").getValue(String.class);
                            Object H=dataSnapshot.child("h").getValue(String.class) ;
                            Object W=dataSnapshot.child("w").getValue(String.class) ;
                            Object L=dataSnapshot.child("l").getValue(String.class) ;
                            //String pickupAddress=dataSnapshot.child("willaya").getValue().toString();
                            Object Destination=dataSnapshot.child("willaya").getValue(String.class)
                                    +", "+dataSnapshot.child("baladia").getValue(String.class) ;
                            Object Frangible=dataSnapshot.child("frangible").getValue(String.class) ;
                            Object DeliverMode=dataSnapshot.child("delivery_mode").getValue(String.class) ;
                            Object CashOn=dataSnapshot.child("cash_on").getValue(String.class) ;
                            //// here i will fill my text fields
                            TitleET.setText(Title.toString());
                            DimensionET.setText(H.toString()+"cm x "+W.toString()+"cm x "+L.toString()+"cm x ");
                            //PickupAddressET .setText(Title);
                            DestinationAddressET.setText(Destination.toString());
                            FrangibleET .setText(Frangible.toString());
                            DeliveryModeET .setText(DeliverMode.toString());
                            CashOnET .setText(CashOn.toString());
                            //durationET .setText(Title);
                            //distanceET .setText(Title);
                            //costET.setText(Title);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }*/
                    try {
                        //DetailsFrameA user = dataSnapshot1.getValue(DetailsFrameA.class);
                        //String Title = getData((Map<String, Object>) dataSnapshot1.getValue(),"title");
                        String Title=dataSnapshot1.child("willaya").getValue( ).toString();
                        //String Title = user.getTitle();
                        System.out.println("Titlelellee:"+Title);
                            /*String H = dataSnapshot.child("h").getValue(String.class);
                            String W = dataSnapshot.child("w").getValue(String.class);
                            String L = dataSnapshot.child("l").getValue(String.class);
                            //String pickupAddress=dataSnapshot.child("willaya").getValue().toString();
                            String Destination = dataSnapshot.child("willaya").getValue(String.class)
                                    + ", " + dataSnapshot.child("baladia").getValue(String.class);
                            String Frangible = dataSnapshot.child("frangible").getValue(String.class);
                            String DeliverMode = dataSnapshot.child("delivery_mode").getValue(String.class);
                            String CashOn = dataSnapshot.child("cash_on").getValue(String.class);*/
                        //// here i will fill my text fields
                        //TitleET.setText(Title.toString());
                            /*DimensionET.setText(H.toString() + "cm x " + W.toString() + "cm x " + L.toString() + "cm x ");
                            //PickupAddressET .setText(Title);
                            DestinationAddressET.setText(Destination.toString());
                            FrangibleET.setText(Frangible.toString());
                            DeliveryModeET.setText(DeliverMode.toString());
                            CashOnET.setText(CashOn.toString());*/
                        //durationET .setText(Title);
                        //distanceET .setText(Title);
                        //costET.setText(Title);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    throw databaseError.toException();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String getData(Map<String, Object> users, String Field) {

        ArrayList<String> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()) {

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
            phoneNumbers.add((String) singleUser.get(Field));
        }
        // Key = phoneNumbers.get(0).toString();
        System.out.println(Field + " key inside " + phoneNumbers.toString());
        return phoneNumbers.get(0).toString();
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
}