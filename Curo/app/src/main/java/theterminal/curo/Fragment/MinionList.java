package theterminal.curo.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import theterminal.curo.Model.Minion;
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

    //TODO: Add listener for listview


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

        //assign ListView
        mListView = (ListView) getActivity().findViewById(R.id.fragment_minion_list_listview);

        //instantiate adapter


    }


}
