package com.alkotzer.itzik.happybirthday.mainActivity;

/**
 * Created by itzikalkotzer on 13/04/2018.
 */

public interface MainActivityContract
{
    interface MainActivityPresenter
    {

        void onViewCreated();

        void onBirthdaySelected(int year, int month, int dayOfMonth);

        void updateName(String name);
    }

    interface MainActivityView
    {

        void setBirthday(String birthday);

        void setName(String name);
    }
}
