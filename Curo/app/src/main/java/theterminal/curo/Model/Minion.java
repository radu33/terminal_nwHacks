package theterminal.curo.Model;

import java.util.ArrayList;

/**
 * Created by tesla on 14/03/15.
 */
public class Minion {

    /* Fields */

    //in format first name, last name
    private String mName;

    //latitude and longitude of current location of minion
    private double mLat;
    private double mLong;

    //status of minion [0,2]
    // 0 -> on task
    // 1 -> moving towards task
    // 2 -> not on task
    private int mStatus;

    //current task
    private Task mCurrentTask;

    //secondary tasks
    private ArrayList<Task> mTasks;

    /* Constructor */

    /**
     * @param name first name and last name
     * @param status status in terms of current task
     * @param currentTask currently assigned task
     * @param secondaryTasks list of tasks to be completed
     */
    public Minion(String name, int status, Task currentTask, ArrayList<Task> secondaryTasks){
        mName = name;
        mCurrentTask = currentTask;
        mTasks = secondaryTasks;
    }

    /**
     * @param name first name and last name
     * @param latitude latitude of minion
     * @param longitude longitude of minion
     * @param status status in terms of current task
     * @param currentTask currently assigned task
     * @param secondaryTasks list of tasks to be completed
     */
    public Minion(String name, int status, double latitude, double longitude ,
                  Task currentTask, ArrayList<Task> secondaryTasks){
        mName = name;
        mLat = latitude;
        mLong = longitude;
        mCurrentTask = currentTask;
        mTasks = secondaryTasks;
    }


}
