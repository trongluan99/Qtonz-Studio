package com.qtonz.module;

import com.ads.qtonz.admob.Admob;
import com.ads.qtonz.admob.AppOpenManager;
import com.ads.qtonz.ads.QtonzAd;
import com.ads.qtonz.application.AdsMultiDexApplication;
import com.ads.qtonz.billing.AppPurchase;
import com.ads.qtonz.config.AdjustConfig;
import com.ads.qtonz.config.QtonzAdConfig;

import java.util.ArrayList;
import java.util.List;

public class App extends AdsMultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initAds();
        initBilling();
    }

    private void initAds() {
        String environment = BuildConfig.DEBUG ? QtonzAdConfig.ENVIRONMENT_DEVELOP : QtonzAdConfig.ENVIRONMENT_PRODUCTION;
        mQtonzAdConfig = new QtonzAdConfig(this, environment);

        AdjustConfig adjustConfig = new AdjustConfig(true, getString(R.string.adjust_token));
        mQtonzAdConfig.setAdjustConfig(adjustConfig);
        mQtonzAdConfig.setFacebookClientToken(getString(R.string.facebook_client_token));
        mQtonzAdConfig.setAdjustTokenTiktok(getString(R.string.tiktok_token));

        mQtonzAdConfig.setIdAdResume("");

        QtonzAd.getInstance().init(this, mQtonzAdConfig);
        Admob.getInstance().setDisableAdResumeWhenClickAds(true);
        Admob.getInstance().setOpenActivityAfterShowInterAds(true);
        AppOpenManager.getInstance().disableAppResumeWithActivity(MainActivity.class);
    }

    private void initBilling() {
        List<String> listIAP = new ArrayList<>();
        listIAP.add("android.test.purchased");
        List<String> listSub = new ArrayList<>();
        AppPurchase.getInstance().initBilling(this, listIAP, listSub);
    }
}
