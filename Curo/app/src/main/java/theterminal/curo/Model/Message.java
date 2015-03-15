package theterminal.curo.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Raza on 2015-03-14.
 */
public class Message {
    private String mMessageBody;
    private Calendar mSendDate;
    private Boolean mIsAlert;
    private String mReceiver;
    private String mSender;

    public String getReceiver() {
        return mReceiver;
    }

    public void setReceiver(String mReceiver) {
        this.mReceiver = mReceiver;
    }


    public String getSender() {
        return mSender;
    }

    public void setSender(String mSender) {
        this.mSender = mSender;
    }

    public Boolean getIsAlert() {
        return mIsAlert;
    }

    public void setIsAlert(Boolean mIsAlert) {
        this.mIsAlert = mIsAlert;
    }

    public String getMessageBody() {
        return mMessageBody;
    }

    public void setMessageBody(String mMessageBody) {
        this.mMessageBody = mMessageBody;
    }


    public Calendar getSendDate() {
        return mSendDate;
    }

    public void setSendDate(Calendar mSendDate) {
        this.mSendDate = mSendDate;
    }




    public Message()
    {
        mMessageBody= "Mock message body";
        mSendDate = GregorianCalendar.getInstance(TimeZone.getDefault());
        mReceiver = "Vaastav Anand";
        mSender = "Radu Nesiu";
        mIsAlert = false;
    }

    public Message(String body,Calendar date,String receiver,String sender,Boolean isAlert)
    {
        mMessageBody= body;
        mSendDate = date;
        mReceiver = receiver;
        mSender = sender;
        mIsAlert = isAlert;
    }

    public Message(String input,String username,String receiver)
    {
        mMessageBody= input;
        mSendDate = GregorianCalendar.getInstance(TimeZone.getDefault());
        mReceiver = receiver;
        mSender = username;
        mIsAlert = false;
    }











}
