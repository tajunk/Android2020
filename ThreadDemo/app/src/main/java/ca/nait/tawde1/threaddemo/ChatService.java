package ca.nait.tawde1.threaddemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ChatService extends Service

{
    public static final String TAG = "ChatService";
    static final int DELAY = 10000;
    public static boolean bRun = false;
    public ChatThread theThread;

    public ChatService()
    {
        Log.d(TAG, "service constructed");
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        theThread = new ChatThread("ChatThreadOne");
        Log.d(TAG, "in onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        bRun = true;
        theThread.start();
        Log.d(TAG, "inOnStartCommand()");
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        bRun = false;
        theThread.interrupt();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
    private class ChatThread extends Thread
    {
        public ChatThread(String names)
        {
            super(names);
            Log.d(TAG, "thread instantiated");
        }

        @Override
        public void run()
        {
            while(bRun == true)
            {
                try
                {
                    Log.d(TAG, "thread executed one cycle");
                    Thread.sleep(DELAY);
                }
                catch(InterruptedException e)
                {
                    bRun = false;
                    Log.d(TAG, "interrupted exception occurred");
                }
            }
        }
    }




}
