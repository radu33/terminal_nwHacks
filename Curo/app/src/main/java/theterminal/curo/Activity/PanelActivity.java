package theterminal.curo.Activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import theterminal.curo.Fragment.Conversation;
import theterminal.curo.Fragment.MinionList;
import theterminal.curo.Fragment.TaskViewer;
import theterminal.curo.Model.Minion;
import theterminal.curo.Model.Task;
import theterminal.curo.R;

public class PanelActivity extends ActionBarActivity implements MinionList.Listener{

    private Minion mMinion;
    private LocationListener locationListener;
    private Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panel);

        mMinion = new Minion("Vaastav", 0, null, null);
        Firebase.setAndroidContext(this);
        firebase = new Firebase(getResources().getString(R.string.firebase_url));

        firebaseLocationUpdate();


        FragmentTransaction fragmentTransactionMinionList = getFragmentManager().beginTransaction();
        MinionList minionList = MinionList.getInstance();
        fragmentTransactionMinionList.add(R.id.panel_container_1, minionList);
        fragmentTransactionMinionList.commit();

        FragmentTransaction fragmentTransactionTaskViewer = getFragmentManager().beginTransaction();
        TaskViewer taskViewer = TaskViewer.getInstance(new Task("Unload Boxes",
                "unload all electronic material from boxes and places them on garage table",
                0, null, null));
        fragmentTransactionTaskViewer.add(R.id.panel_task_viewer, taskViewer);
        fragmentTransactionTaskViewer.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ListViewItemSelected(Minion minion) {
        FragmentTransaction fragmentTransactionConversation = getFragmentManager().beginTransaction();
        Conversation conversation = Conversation.getInstance(minion);
        fragmentTransactionConversation.replace(R.id.panel_container_1, conversation);
        fragmentTransactionConversation.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransactionConversation.addToBackStack(null);
        fragmentTransactionConversation.commit();
    }

    private void firebaseLocationUpdate(){
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(mMinion != null){
                    mMinion.setLat(location.getLatitude());
                    mMinion.setLong(location.getLongitude());

                    firebase.child("minions").child("Vaastav").child("lat").setValue(Double.toString(mMinion.getLat()), new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            if (firebaseError != null) {
                                Log.d("firebase", "Data could not be saved. " + firebaseError.getMessage());
                            } else {
                               Log.d("firebase", "Data saved successfully.");
                            }
                        }
                    });

                    firebase.child("minions").child("Vaastav").child("long").setValue(Double.toString(mMinion.getLong()), new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            if (firebaseError != null) {
                                Log.d("firebase", "Data could not be saved. " + firebaseError.getMessage());
                            } else {
                                Log.d("firebase", "Data saved successfully.");
                            }
                        }
                    });
                }
            }


            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

}

