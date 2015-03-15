package theterminal.curo.Fragment;


import android.net.LinkAddress;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Map;

import theterminal.curo.Adapter.MinionAdapter;
import theterminal.curo.Model.Minion;
import theterminal.curo.Model.Task;
import theterminal.curo.R;

/**
 * A simple {@link Fragment} subclass.
 * Uses Singleton Pattern
 */
public class MinionList extends Fragment {

    /* Fields */

    //current instance of MinionList, null when not instantiated
    private static volatile MinionList instance;

    //List of Minions to Display
    private ArrayList<Minion> mMinions;

    //ListView where minions are displayed
    private ListView mListView;

    Listener mListener;


    /* Fragment States */

    /**
     * Singleton pattern instantiation
     *
     * @return current instance of MinionList if instance is not null, else create new instance
     */
    public static MinionList getInstance(){

        if(instance == null)
            instance = new MinionList();

        return instance;
    }


    public MinionList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_minion_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceStates){
        super.onActivityCreated(savedInstanceStates);

        mListener = (Listener) getActivity();

        mMinions = new ArrayList<Minion>();

        getMinions();

        //MOCK VALUES
/*        Task t1 = new Task("unload boxes", "ipsum", 0, null, null);
        Task t2 = new Task("welcome customers", "dolor", 0, null, null);
        mMinions.add(new Minion("Vaastav", 0, t1, null));
        mMinions.add(new Minion("Raunak", 1, t2, null));
        mMinions.add(new Minion("Vaastav", 0, t1, null));
        mMinions.add(new Minion("Raunak", 2, t2, null));
        mMinions.add(new Minion("Vaastav", 0, t1, null));
        mMinions.add(new Minion("Raunak", 2, t2, null));
        mMinions.add(new Minion("Vaastav", 0, t1, null));
        mMinions.add(new Minion("Raunak", 1, t2, null));
        mMinions.add(new Minion("Vaastav", 0, t1, null));
        mMinions.add(new Minion("Raunak", 2, t2, null));
        mMinions.add(new Minion("Vaastav", 0, t1, null));
        mMinions.add(new Minion("Raunak", 1, t2, null));*/


        //assign ListView
        mListView = (ListView) getActivity().findViewById(R.id.fragment_minion_list_listview);

        //handles clicks to listview

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.ListViewItemSelected(mMinions.get(position));
            }
        });

        //instantiate adapter
        MinionAdapter adapter = new MinionAdapter(getActivity(), mMinions);

        //set adapter
        mListView.setAdapter(adapter);

    }

    private void getMinions(){
        Firebase firebase = new Firebase(getActivity().getResources().getString(R.string.firebase_url));
        Firebase minions = firebase.child("minions");

        minions.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> newMinion = (Map<String, String>) dataSnapshot.getValue();


                Task t = new Task("Unload Boxes", "perform this random task",  0, null, null);
                mMinions.add(new Minion( newMinion.get("name"), 0, null, null));

                MinionAdapter adapter = new MinionAdapter(getActivity(), mMinions);
                mListView.setAdapter(adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    /* Nested Class */
    public interface Listener{
        public void ListViewItemSelected(Minion minion);
    }


}
