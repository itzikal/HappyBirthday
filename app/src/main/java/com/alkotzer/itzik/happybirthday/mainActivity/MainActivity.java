package com.alkotzer.itzik.happybirthday.mainActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.alkotzer.itzik.happybirthday.R;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainActivityContract.MainActivityView, DatePickerDialog.OnDateSetListener
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

    @AfterViews
    void initViews()
    {
      mPresenter.onViewCreated();
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, this, 0, 0, 0);
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
}
