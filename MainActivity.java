package com.sharmaji.Calenderapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper mViewFlipper;
    private Button btnnxt,btnprevious;
    TouchImageView imageView;
    private InterstitialAd interstitial;
    private List<String> imageURLs = Arrays.asList(new String[] {
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22788761_931959300286828_86763258099277565_n.jpg?oh=074e64281af4b82fa02940cf006d14c3&oe=5AABC337",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22814424_931959170286841_4910579246083664727_n.jpg?oh=9faa6f17b99d5337828f65f918eedd5e&oe=5A6B7421",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22814532_931959240286834_2223534155431848874_n.jpg?oh=182cb7304b7224c057af63b50b9256c9&oe=5A72EC04",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22885966_931959166953508_38123469529888251_n.jpg?oh=659e2c975ee80ddd4782fb3cf621f82a&oe=5A6C31D2",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22780479_931959246953500_8075307015924509672_n.jpg?oh=9be8d46ca70f3db6ea6e3bdbf9ffe593&oe=5AAFE05A",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22730315_931959303620161_5014752418771221601_n.jpg?oh=881144d59d4e6d97a95e510bdbaa8100&oe=5A78C650",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22815565_931959376953487_6770136899114323452_n.jpg?oh=f54bbcec78ad7b56bdd0b102aa569ce3&oe=5AABE4A2",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22813995_931959243620167_5919253656074695097_n.jpg?oh=abc9f3a7c9edc0053729fbcdcbd2c8b7&oe=5A77E572",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22729159_931959173620174_4511101075724244694_n.jpg?oh=0f8ccf60a60156bc883cde69da232290&oe=5A780E75",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22780665_931959386953486_3532206016108427949_n.jpg?oh=852ad16d0994f6f2f809b98688ec383c&oe=5A7C8EC2",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22730456_931959383620153_1977709834051708359_n.jpg?oh=ffb51c1f2157eb54f246a4de0117c9c7&oe=5A653C69",
    "https://scontent.fdel8-1.fna.fbcdn.net/v/t1.0-9/22780467_931959296953495_1017126137317779040_n.jpg?oh=8c9763a0488545c936f5df76b071a57d&oe=5A736F42"});
    private int index = 0;
    ImageView imageview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewFlipper=(ViewFlipper)findViewById(R.id.imgsw);
        btnnxt=(Button)findViewById(R.id.btnnxt);
        btnprevious=(Button)findViewById(R.id.btnprevious);
        imageview=(TouchImageView)findViewById(R.id.imageview);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(MainActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest);
        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });
        btnnxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdRequest request = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();
                interstitial.loadAd(request);
                TouchImageView image = new TouchImageView(getApplicationContext());
                UrlImageViewHelper.setUrlDrawable(image, getNextImage(), R.drawable.loading);
               // mTextView.setText("Showing: " + index);
                mViewFlipper.addView(image);
                mViewFlipper.showNext();
            }
        });

        btnprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdRequest request = new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build();
                interstitial.loadAd(request);
                TouchImageView image = new TouchImageView(getApplicationContext());
                UrlImageViewHelper.setUrlDrawable(image, getNextImage(), R.drawable.loading);
                // mTextView.setText("Showing: " + index);
                mViewFlipper.addView(image);
                mViewFlipper.showPrevious();
            }
        });

        // Set in/out flipping animations
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

    }


    protected String getNextImage() {
        if (index == imageURLs.size())
            index = 0;
        return imageURLs.get(index++);
    }

    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

}
 
