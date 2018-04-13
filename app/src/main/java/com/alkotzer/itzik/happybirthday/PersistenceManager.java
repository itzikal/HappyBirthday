package com.alkotzer.itzik.happybirthday;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class PersistenceManager
{
    private static final String NAME = "name_key";
    private final SharedPreferences mPreferences;

    public PersistenceManager(Context context)
    {
        this.mPreferences = context.getSharedPreferences("HappyBirthdayPref", Context.MODE_PRIVATE);
    }

    public void saveName(String name)
    {
        mPreferences.edit().putString(NAME, name).apply();
    }

    @Nullable
    public String getName()
    {
       return mPreferences.getString(NAME, null);
    }
}
