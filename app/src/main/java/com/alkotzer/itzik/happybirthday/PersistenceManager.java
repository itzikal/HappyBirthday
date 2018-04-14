package com.alkotzer.itzik.happybirthday;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import static com.alkotzer.itzik.happybirthday.AppConsts.BIRTHDAY_DAY;
import static com.alkotzer.itzik.happybirthday.AppConsts.BIRTHDAY_MONTH;
import static com.alkotzer.itzik.happybirthday.AppConsts.BIRTHDAY_YEAR;
import static com.alkotzer.itzik.happybirthday.AppConsts.NAME;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class PersistenceManager
{


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

    public void saveBirthday(Birthday birthday)
    {
        if(birthday == null)
        {
            mPreferences.edit()
                    .remove(BIRTHDAY_YEAR)
                    .remove(BIRTHDAY_MONTH)
                    .remove(BIRTHDAY_DAY)
                    .apply();
            return;
        }
        mPreferences.edit()
                .putInt(BIRTHDAY_YEAR, birthday.getYear())
                .putInt(BIRTHDAY_MONTH, birthday.getMonth())
                .putInt(BIRTHDAY_DAY, birthday.getDayOfMonth())
                .apply();
    }

    @Nullable
    public Birthday getBirthday()
    {
        int year  = mPreferences.getInt(BIRTHDAY_YEAR, -1);
        int month  = mPreferences.getInt(BIRTHDAY_MONTH, -1);
        int day  = mPreferences.getInt(BIRTHDAY_DAY, -1);

        if(year == -1 || month == -1 || day == -1)
        {
            return null;
        }

        return new Birthday(year, month, day);
    }
}
