package com.alkotzer.itzik.happybirthday.mainActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.alkotzer.itzik.happybirthday.BaseActivity;
import com.alkotzer.itzik.happybirthday.Birthday;
import com.alkotzer.itzik.happybirthday.R;
import com.alkotzer.itzik.happybirthday.birthdayActivity.BirthdayActivity_;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityView, DatePickerDialog.OnDateSetListener
{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.user_name)
    EditText mName;

    @ViewById(R.id.user_birthday)
    EditText mBirthday;

    @ViewById(R.id.show_birthday_screen)
    Button mShowBirthdayScreen;

    @ViewById(R.id.user_image)
    ImageView mImage;


    MainActivityContract.MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mPresenter = new MainActivityPersenter(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mPresenter.onViewStopped();
    }

    @AfterViews
    void initViews()
    {
        mPresenter.onViewCreated();
        loadUserImageInTo(mImage);
    }

    @Override
    public void showSelectImageSourceDialog()
    {
        super.showImageSourceDialog();
    }

    @Override
    protected void setUserImage(final Bitmap photo)
    {
        mImage.setImageBitmap(photo);
    }

    @Override
    public void setBirthday(final String birthday)
    {
        mBirthday.setText(birthday);
    }

    @Override
    public void setName(final String name)
    {
        mName.setText(name);
    }

    @Override
    public void setShowBirthdayButtonEnabled(final boolean isEnabled)
    {
        mShowBirthdayScreen.setEnabled(isEnabled);
    }

    @Click(R.id.user_birthday)
    void onBirthdayClicked()
    {
        Birthday birthday = mPresenter.getLastBirthday();
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, this, birthday.getYear(), birthday.getMonth(), birthday.getDayOfMonth());
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(final DatePicker view, final int year, final int month, final int dayOfMonth)
    {
        mPresenter.onBirthdaySelected(year, month, dayOfMonth);
    }

    @AfterTextChange(R.id.user_name)
    void onNameChange(Editable text)
    {
        mPresenter.updateName(text.toString());
    }

    @Click(R.id.user_image)
    void onImageClicked()
    {
       mPresenter.onImageClicked();
    }

    @Click(R.id.show_birthday_screen)
    void onButtonClicked()
    {
        mPresenter.onShowBirthdayScreenClicked();
    }

    @Override
    public void navigateToBirthdayScreen()
    {
        startActivity(new Intent(MainActivity.this, BirthdayActivity_.class));
    }


}
