package com.alkotzer.itzik.happybirthday;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
        mBirthday.setText(new Birthday(year, month, dayOfMonth).asDate());
    }
}
