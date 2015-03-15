package theterminal.curo.Fragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

import theterminal.curo.Adapter.ConversationAdapter;
import theterminal.curo.Model.Message;
import theterminal.curo.R;
/**
 * Created by Radu on 2015-03-14.
 */
public class LoginFragment extends Fragment{

    /* Fields */

    //current instance of Inbox, null when not instantiated
    private static volatile LoginFragment instance;

    /**
     * Singleton pattern instantiation
     *
     * @return current instance of Inbox if instance is not null, else create new instance
     */
    public static LoginFragment getInstance(){

        if(instance == null)
            instance = new LoginFragment();

        return instance;
    }

    public LoginFragment() {
        // Required empty public constructor

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_page, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceStates){
        super.onActivityCreated(savedInstanceStates);

        //assign ListView
        //mListView = (ListView) getActivity().findViewById(R.id.conversation_listview);


        //instantiate adapter
        //ConversationAdapter adapter = new ConversationAdapter(getActivity(),mMessages);
        //mListView.setAdapter(adapter);

    }

}
