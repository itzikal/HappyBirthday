package com.alkotzer.itzik.happybirthday;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class Birthday
{
    private int mYear;
    private int mMonth;
    private int mDayOfMonth;

    public Birthday(final int year, final int month, final int dayOfMonth)
    {
        mYear = year;
        mMonth = month;
        mDayOfMonth = dayOfMonth;
    }

    public int getYear()
    {
        return mYear;
    }

    public int getMonth()
    {
        return mMonth;
    }

    public int getDayOfMonth()
    {
        return mDayOfMonth;
    }

    public String asDate()
    {
        return mDayOfMonth+"/"+mMonth+"/"+mYear;
    }
}
