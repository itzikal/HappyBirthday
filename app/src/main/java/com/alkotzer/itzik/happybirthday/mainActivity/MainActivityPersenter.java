package com.alkotzer.itzik.happybirthday.mainActivity;

import com.alkotzer.itzik.happybirthday.Birthday;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public class MainActivityPersenter implements MainActivityContract.MainActivityPresenter
{
    private static final String LOG_TAG = MainActivityPersenter.class.getSimpleName();

    private MainActivityContract.MainActivityView mView;
    private Birthday mBirthday;
    private String mName;

    public MainActivityPersenter(final MainActivityContract.MainActivityView view)
    {
        mView = view;
        mBirthday = new Birthday(1980,12, 31);
        mName = "itzik";
    }

    @Override
    public void onViewCreated()
    {
        setViewBirthday();
        setViewName();
    }

    @Override
    public void onBirthdaySelected(final int year, final int month, final int dayOfMonth)
    {
        mBirthday = new Birthday(year,month, dayOfMonth);
    }

    @Override
    public void updateName(final String name)
    {
        mName = name;
    }

    private void setViewName()
    {
        mView.setName(mName);
    }

    private void setViewBirthday()
    {
        mView.setBirthday(mBirthday.asDate());
    }



}
