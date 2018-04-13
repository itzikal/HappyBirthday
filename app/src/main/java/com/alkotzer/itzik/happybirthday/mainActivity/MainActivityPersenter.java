package com.alkotzer.itzik.happybirthday.mainActivity;

import android.text.TextUtils;

import com.alkotzer.itzik.happybirthday.Birthday;
import com.alkotzer.itzik.happybirthday.PersistenceManager;
import com.alkotzer.itzik.happybirthday.general.AppManager;

import java.util.Calendar;

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

        PersistenceManager preferance = AppManager.getInstance().getPreferance();
        mBirthday = preferance.getBirthday();
        mName = preferance.getName();
    }

    @Override
    public void onViewStopped()
    {
        PersistenceManager preferance = AppManager.getInstance().getPreferance();
        preferance.saveBirthday(mBirthday);
        preferance.saveName(mName);
    }

    @Override
    public void onViewCreated()
    {
        setViewBirthday();
        setViewName();
        setShowBirthdayScreenButtonState();
    }

    @Override
    public Birthday getLastBirthday()
    {
        if(mBirthday == null)
        {
            Calendar instance = Calendar.getInstance();
            return new Birthday(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH),instance.get(Calendar.DAY_OF_MONTH));
        }
        return mBirthday;
    }

    @Override
    public void onShowBirthdayScreenClicked()
    {
        mView.navigateToBirthdayScreen();
    }

    private void setShowBirthdayScreenButtonState()
    {
        mView.setShowBirthdayButtonEnabled(!TextUtils.isEmpty(mName) && mBirthday != null);
    }

    @Override
    public void onBirthdaySelected(final int year, final int month, final int dayOfMonth)
    {
        mBirthday = new Birthday(year,month, dayOfMonth);
        setViewBirthday();
        setShowBirthdayScreenButtonState();
    }

    @Override
    public void updateName(final String name)
    {
        mName = name;
        setShowBirthdayScreenButtonState();
    }

    @Override
    public void onImageClicked()
    {
        mView.showSelectImageSourceDialog();
    }

    private void setViewName()
    {
        mView.setName(mName);
    }

    private void setViewBirthday()
    {
        String date = mBirthday == null ? "" : mBirthday.asDate();
        mView.setBirthday(date);
    }


}
