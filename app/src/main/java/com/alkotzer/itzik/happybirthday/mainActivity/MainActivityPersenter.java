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

    public MainActivityPersenter(final MainActivityContract.MainActivityView view)
    {
        mView = view;
        mBirthday = new Birthday(1980,12, 31);
    }

    @Override
    public void onViewCreated()
    {
        setViewBirthday();
    }

    @Override
    public void onBirthdaySelected(final int year, final int month, final int dayOfMonth)
    {
        mBirthday = new Birthday(year,month, dayOfMonth);
    }

    private void setViewBirthday()
    {
        mView.setBirthday(mBirthday.asDate());
    }

}
