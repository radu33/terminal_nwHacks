package theterminal.curo.Model;

import java.util.GregorianCalendar;

/**
 * Created by tesla on 14/03/15.
 */
public class Task {

    /* Fields */


    //task name
    private String mName;

    //description of task i.e nature of task, description of location, resources, etc...
    private String mDescrition;

    //completion status of task [0,2]
    // 0 -> not completed
    // 1 -> in process of completion
    // 2 -> completed
    private int mStatus;

    //start and end times in local timezone
    private GregorianCalendar mStartTime;
    private GregorianCalendar mEndTime;

    /* Constructors */

    /**
     * @param name name of task
     * @param desc  description
     * @param status completion status
     * @param startTime start time of task
     * @param endTime end time of task
     */
    public Task(String name, String desc, int status, GregorianCalendar startTime, GregorianCalendar endTime){
        mName = name;
        mDescrition = desc;
        mStatus = status;
        mStartTime = startTime;
        mEndTime = endTime;

    }

    public String getName() {
        return mName;
    }

    public String getDescrition() {
        return mDescrition;
    }

    public int getStatus() {
        return mStatus;
    }

    public GregorianCalendar getStartTime() {
        return mStartTime;
    }

    public GregorianCalendar getEndTime() {
        return mEndTime;
    }
}
