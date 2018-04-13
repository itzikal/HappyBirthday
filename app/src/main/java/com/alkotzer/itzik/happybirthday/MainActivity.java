package com.alkotzer.itzik.happybirthday;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
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
}
