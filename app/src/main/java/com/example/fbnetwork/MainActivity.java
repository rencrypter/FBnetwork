package com.example.fbnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.ads.*;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    AdView adView;
    InterstitialAd interstitialAd;
    RewardedVideoAd rewardedVideoAd;

    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.btn2);
        button1 = findViewById(R.id.btn1);

        AdSettings.addTestDevice("e3c3d521-ec33-44d5-9486-a196853162e3");

        AudienceNetworkAds.initialize(this);

        bannerAd();



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interstitialAd();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rewardAd();
            }
        });

    }

    private void rewardAd() {

        rewardedVideoAd = new RewardedVideoAd(this, "YOUR_PLACEMENT_ID");

        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {

            @Override
            public void onRewardedVideoCompleted() {

            }

            @Override
            public void onRewardedVideoClosed() {

            }

            @Override
            public void onError(Ad ad, AdError adError) {
                Log.d("TAG", "onError: "+adError.getErrorMessage());

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                Log.d("TAG", "onAdLoaded: ");
                rewardedVideoAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());

    }


    private void bannerAd() {

        adView = new AdView(this, "IMG_16_9_APP_INSTALL#2290808671067177_2291948940953150", AdSize.BANNER_HEIGHT_50);


// Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

// Add the ad view to your activity layout
        adContainer.addView(adView);

        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
// Ad error callback
                Log.d("TAG", "onError: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
// Ad loaded callback

                Log.d("TAG", "onAdLoaded: ");
            }

            @Override
            public void onAdClicked(Ad ad) {
// Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Ad impression logged callback
            }
        };
        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build());


    }

    private void interstitialAd() {


        interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e("TAG", "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e("TAG", "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d("TAG", "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d("TAG", "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d("TAG", "Interstitial ad impression logged!");
            }
        };

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());


    }


    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


}