package theterminal.curo.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.GregorianCalendar;

import theterminal.curo.Model.Task;
import theterminal.curo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaskViewer extends Fragment {

    /* Fields */

    private static volatile TaskViewer instance;
    private Task mCurrentTask;

    //UI Elements
    private TextView name;
    private TextView desc;
    private TextView completionStatus;
    private TextView time;

    /* Fragment States */

    public static TaskViewer getInstance(){

        if(instance == null)
            instance = new TaskViewer();

        return instance;
    }

    public TaskViewer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_viewer, container, false);
    }

    public void setCurrentTask(Task currentTask) {
        mCurrentTask = currentTask;
        setupUI();
        displayTask();
    }


    public void setupUI(){

        if(name == null || desc == null || completionStatus == null || time == null) {

            name = (TextView) getActivity().findViewById(R.id.task_viewer_task_name);
            desc = (TextView) getActivity().findViewById(R.id.task_viewer_task_desc);
            completionStatus = (TextView) getActivity().findViewById(R.id.task_viewer_completion_status);
            time = (TextView) getActivity().findViewById(R.id.task_viewer_time);

        }

    }

    public void displayTask(){

        //name description
        name.setText("Task Name: " + mCurrentTask.getName());
        desc.setText("Description: " + mCurrentTask.getDescrition());

        //completion status
        String completionText = getCompletionText(mCurrentTask.getStatus());
        completionStatus.setText("Completion Status: " + completionText);

        //time
        if (mCurrentTask.getStartTime() != null) {
            int startDay = mCurrentTask.getStartTime().get(GregorianCalendar.DAY_OF_MONTH);
            int startHours = mCurrentTask.getStartTime().get(GregorianCalendar.HOUR_OF_DAY);
            int startMin = mCurrentTask.getStartTime().get(GregorianCalendar.MINUTE);
            String startTime = Integer.toString(startDay) + " " +
                    Integer.toString(startHours) + ":" + Integer.toString(startMin);

            time.setText("Start Time: " + startTime);
        }


        if(mCurrentTask.getEndTime() != null) {
            int endDay = mCurrentTask.getEndTime().get(GregorianCalendar.DAY_OF_MONTH);
            int endHours = mCurrentTask.getEndTime().get(GregorianCalendar.HOUR_OF_DAY);
            int endMin = mCurrentTask.getEndTime().get(GregorianCalendar.MINUTE);
            String endTime = Integer.toString(endDay) + " " +
                    Integer.toString(endHours) + ":" + Integer.toString(endMin);

            time.setText(time.getText() + "\n" +
                    "End Time" + endTime);
        }


    }

    public String getCompletionText(int status){
        String completionText = "";
        switch (status){
            case 0:
                completionText = "not completed";
                break;
            case 1:
                completionText = "in process";
                break;
            case 2:
                completionText = "completed";
                break;
        }

        return completionText;
    }
}
