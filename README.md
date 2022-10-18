# ChartboostAdsBanner

This is a minimal application, only featuring an activity. It is meant to be
cloned to study Chartboost SDK performance.

## Scenario

The `scenario.sh` script allows you to automatically run the application.

It will automatically launch the app and wait a defined amount of time to load ads. NOTE that it is not possible to call API show method directly after cache method for the same location, or the SDK will fail silently. This is why the minimal app has two buttons: one to cache the ad and another one to show it. Therefore, the scenario must tap these two buttons in order to load ads.

To use it, make sure you have `adb` installed and an Android device with dev mode plugged-in.

## Credits

[https://github.com/rsain/Android-TPLs](https://github.com/rsain/Android-TPLs)
