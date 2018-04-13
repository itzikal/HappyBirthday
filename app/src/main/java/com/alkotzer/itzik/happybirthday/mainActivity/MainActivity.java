package com.alkotzer.itzik.happybirthday.mainActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alkotzer.itzik.happybirthday.Birthday;
import com.alkotzer.itzik.happybirthday.R;
import com.alkotzer.itzik.happybirthday.birthdayActivity.BirthdayActivity_;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements MainActivityContract.MainActivityView, DatePickerDialog.OnDateSetListener
{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int RESULT_LOAD_IMG = 100;
    private static final int CAMERA_REQUEST = 101;
    public static final String USER_IMAGE_JPEG = "/userImage.jpeg";

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
        String filename = getCacheDir().toString() + USER_IMAGE_JPEG;
        FileInputStream imageStream = null;
        try
        {
            imageStream = new FileInputStream(filename);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            mImage.setImageBitmap(selectedImage);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

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
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, this, birthday.getYear(), birthday.getMonth(), birthday.getYear());
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

    @Override
     public void showSelectImageSourceDialog()
    {
        new SelectImageSourceDialog().show(MainActivity.this, new SelectImageSourceDialog.OnImageSourceSelected() {
            @Override
            public void onGallerySelected()
            {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }

            @Override
            public void onCameraSelected()
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data)
    {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK)
        {
            try
            {
                Bitmap photo = null;
            if(reqCode == CAMERA_REQUEST)
            {
                photo = (Bitmap) data.getExtras().get("data");
            }
            else if (reqCode == RESULT_LOAD_IMG)
            {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                photo = BitmapFactory.decodeStream(imageStream);
            }

            if(photo == null)
            {
                return;
            }
                mImage.setImageBitmap(photo);
                saveBitmapLocaly(photo);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(MainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    public void saveBitmapLocaly(final Bitmap bmp)
    {

        String filename = getCacheDir().toString() + USER_IMAGE_JPEG;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(Bitmap.CompressFormat.JPEG, 85, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
