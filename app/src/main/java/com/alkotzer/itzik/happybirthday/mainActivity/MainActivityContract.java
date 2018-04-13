package com.alkotzer.itzik.happybirthday.mainActivity;

import com.alkotzer.itzik.happybirthday.Birthday;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public interface MainActivityContract
{
    interface MainActivityPresenter
    {

        void onViewStopped();

        void onViewCreated();

        void onBirthdaySelected(int year, int month, int dayOfMonth);

        void updateName(String name);

        void onImageClicked();

        Birthday getLastBirthday();
    }

    interface MainActivityView
    {

        void setBirthday(String birthday);

        void setName(String name);

        void setShowBirthdayButtonEnabled(boolean isEnabled);

        void showSelectImageSourceDialog();
    }
}
