package com.alkotzer.itzik.happybirthday.birthdayActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alkotzer.itzik.happybirthday.Birthday;
import com.alkotzer.itzik.happybirthday.R;
import com.alkotzer.itzik.happybirthday.general.AppManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Random;

import static com.alkotzer.itzik.happybirthday.mainActivity.MainActivity.USER_IMAGE_JPEG;

@EActivity(R.layout.activity_birthday)
public class BirthdayActivity extends AppCompatActivity
{
    private static final String LOG_TAG = BirthdayActivity.class.getSimpleName();
    private static final int[] mBackgrounds = new int[]{R.drawable.android_elephant_popup, R.drawable.android_fox_popup, R.drawable.android_pelican_popup};
    private static final int[] mPlaceHolders = new int[]{R.drawable.default_place_holder_yellow, R.drawable.default_place_holder_green, R.drawable.default_place_holder_blue};
    private static final int[] mAges = new int[]{
        R.drawable.ic_0,
        R.drawable.ic_1,
        R.drawable.ic_2,
        R.drawable.ic_3,
        R.drawable.ic_4,
        R.drawable.ic_5,
        R.drawable.ic_6,
        R.drawable.ic_7,
        R.drawable.ic_8,
        R.drawable.ic_9,
        R.drawable.ic_10,
        R.drawable.ic_11,
        R.drawable.ic_12
};
    @ViewById(R.id.background)
    View mBackground;

    @ViewById(R.id.user_image)
    ImageView mUserImage;

    @ViewById(R.id.user_name)
    TextView mUserName;

    @ViewById(R.id.year_or_month)
    TextView mYearOrMonthText;

    @ViewById(R.id.age)
    ImageView mAge;


    @AfterViews
    void initViews()
    {
        Random r = new Random(System.currentTimeMillis());
        int random = r.nextInt(2);

        mBackground.setBackgroundResource(mBackgrounds[random]);

        String filename = getCacheDir().toString() + USER_IMAGE_JPEG;
        if(new File(filename).exists())
        {
            FileInputStream imageStream = null;
            try
            {
                imageStream = new FileInputStream(filename);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                mUserImage.setImageBitmap(selectedImage);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
                mUserImage.setImageResource(mPlaceHolders[random]);
            }
        }
        else
        {
            mUserImage.setImageResource(mPlaceHolders[random]);
        }


        calculateAge();
        String name = AppManager.getInstance().getPreferance().getName();
        mUserName.setText(getString(R.string.today_name_is, name));

    }

    private void calculateAge()
    {
        Birthday birthday = AppManager.getInstance().getPreferance().getBirthday();
        if(birthday == null)
        {
            Log.e(LOG_TAG, "calculateAge(), Birthday was null, check if save currcly before opening this screen.");
            throw new RuntimeException("Birthday was null, check if save currcly before opening this screen");
        }
        Calendar today = Calendar.getInstance();

        int years = today.get(Calendar.YEAR) - birthday.getYear();
        if(years > 0)
        {
            setYears(years);
            return;
        }
        int month = today.get(Calendar.MONTH) - birthday.getMonth();
        if(month >= 0)
        {
            setMonth(month);
        }
    }

    private void setMonth(final int month)
    {
        mAge.setImageResource(mAges[month]);
        mYearOrMonthText.setText(R.string.month_old);
    }

    private void setYears(final int years)
    {
        mAge.setImageResource(mAges[years > 12 ? 12 : years]);
        mYearOrMonthText.setText(R.string.years_old);
    }


    @Click(R.id.close_screen)
    void onCloseClicked()
    {
        finish();
    }
}
