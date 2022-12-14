#!/bin/bash

PACKAGE="tpl.ads.banner.chartboost"
ACTIVITY="tpl.ads.banner.chartboost.MainActivity"

TIME_IN_SECONDS_TO_LOAD_ADS=57

# Launch app
adb shell am start -n $PACKAGE/$ACTIVITY

# Wait to fully load app
sleep 1

# tap on cache button
adb shell input tap 127 300

# Wait to fully cache ad
sleep 2

# tap on show button
adb shell input tap 380 300

# Wait TIME_IN_SECONDS_TO_LOAD_ADS seconds to simulate ads load
sleep TIME_IN_SECONDS_TO_LOAD_ADS