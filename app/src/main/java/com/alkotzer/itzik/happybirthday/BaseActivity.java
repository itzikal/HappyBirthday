package com.alkotzer.itzik.happybirthday;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.alkotzer.itzik.happybirthday.mainActivity.SelectImageSourceDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by itzikalkotzer on 14/04/2018.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    public static final String USER_IMAGE_JPEG = "/userImage.jpeg";
    private static final int RESULT_LOAD_IMG = 100;
    private static final int CAMERA_REQUEST = 101;

    protected void showImageSourceDialog()
    {
        new SelectImageSourceDialog().show(BaseActivity.this, new SelectImageSourceDialog.OnImageSourceSelected() {
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
                setUserImage(photo);
                saveBitmapLocaly(photo);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                Toast.makeText(BaseActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(BaseActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    protected abstract void setUserImage(final Bitmap photo);

    protected void loadUserImageInTo(final ImageView image)
    {
        String filename = getCacheDir().toString() + USER_IMAGE_JPEG;
        if(!new File(filename).exists())
        {
            return;
        }
        FileInputStream imageStream = null;
        try
        {
            imageStream = new FileInputStream(filename);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            image.setImageBitmap(selectedImage);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
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
