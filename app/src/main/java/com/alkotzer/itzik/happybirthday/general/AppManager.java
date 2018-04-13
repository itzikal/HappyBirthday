package com.alkotzer.itzik.happybirthday.general;

import android.content.Context;

import com.alkotzer.itzik.happybirthday.PersistenceManager;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class AppManager
{
    private static AppManager mInstance;
    private Context mContext;
    private PersistenceManager mPersistance;

    private AppManager(Context context)
    {
        mContext = context;
    }

    public static AppManager getInstance()
    {

        return mInstance;
    }

    public PersistenceManager getPreferance(){
        if(mPersistance == null)
        {
            mPersistance = new PersistenceManager(mContext);
        }
        return mPersistance;
    }

    static void init(final Context applicationContext)
    {
        if (mInstance == null)
        {
            mInstance = new AppManager(applicationContext);
        }
    }
}
