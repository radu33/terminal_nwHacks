package theterminal.curo.Activity;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import theterminal.curo.Fragment.MinionList;
import theterminal.curo.Fragment.TaskViewer;
import theterminal.curo.Model.Task;
import theterminal.curo.R;

public class PanelActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        FragmentTransaction fragmentTransactionMinionList = getFragmentManager().beginTransaction();
        MinionList minionList = MinionList.getInstance();
        fragmentTransactionMinionList.add(R.id.panel_minion_list, minionList);
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
}
