package tpl.ads.banner.chartboost;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.LoggingLevel;
import com.chartboost.sdk.ads.Banner;
import com.chartboost.sdk.callbacks.BannerCallback;
import com.chartboost.sdk.callbacks.StartCallback;
import com.chartboost.sdk.events.CacheError;
import com.chartboost.sdk.events.CacheEvent;
import com.chartboost.sdk.events.ClickError;
import com.chartboost.sdk.events.ClickEvent;
import com.chartboost.sdk.events.ImpressionEvent;
import com.chartboost.sdk.events.ShowError;
import com.chartboost.sdk.events.ShowEvent;
import com.chartboost.sdk.events.StartError;
import com.chartboost.sdk.privacy.model.DataUseConsent;
import com.chartboost.sdk.privacy.model.GDPR;

public class MainActivity extends AppCompatActivity  {
    Banner chartboostBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Chartboost.addDataUseConsent(getApplicationContext(), new GDPR(GDPR.GDPR_CONSENT.BEHAVIORAL));
        Chartboost.startWithAppId(getApplicationContext(), getResources().getString(R.string.appId), getResources().getString(R.string.appSignature), new StartCallback() {
            @Override
            public void onStartCompleted(@Nullable StartError startError) {
                if (startError != null) {
                    Log.println(Log.INFO, "INFO", "*** MY MESSAGE ***: NOT STARTED");
                    Log.println(Log.INFO,"INFO",startError.toString());
                }
            }
        });

        chartboostBanner = new Banner(this.getApplicationContext(), "location", Banner.BannerSize.STANDARD, new BannerCallback() {
            @Override
            public void onAdLoaded(@NonNull CacheEvent cacheEvent, @Nullable CacheError cacheError) {
            }

            @Override
            public void onAdRequestedToShow(@NonNull ShowEvent showEvent) {
            }

            @Override
            public void onAdShown(@NonNull ShowEvent showEvent, @Nullable ShowError showError) {
            }

            @Override
            public void onAdClicked(@NonNull ClickEvent clickEvent, @Nullable ClickError clickError) {
            }

            @Override
            public void onImpressionRecorded(@NonNull ImpressionEvent impressionEvent) {
            }
        }, null);

        setContentView(R.layout.activity_main);

        // Needs to be set before SDK init
        //Chartboost.addDataUseConsent(getApplicationContext(), new GDPR(GDPR.GDPR_CONSENT.BEHAVIORAL));
        //Chartboost.addDataUseConsent(getApplicationContext(), new CCPA(CCPA.CCPA_CONSENT.OPT_IN_SALE));
        Chartboost.setLoggingLevel(LoggingLevel.ALL);

        Button cacheButton = findViewById(R.id.buttonCache);
        Button showButton = findViewById(R.id.buttonShow);
        RelativeLayout bannerHolder = findViewById(R.id.example_banner_holder);

        cacheButton.setOnClickListener(v -> cacheBanner());
        showButton.setOnClickListener(v -> showBanner());

        //Attach object to layout
        bannerHolder.addView(chartboostBanner);
    }

    private void showBanner() {
        chartboostBanner.show();
    }

    private void cacheBanner() {
        chartboostBanner.cache();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(chartboostBanner != null) {
            chartboostBanner.detach();
        }
    }
}