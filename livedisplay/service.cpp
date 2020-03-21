/*
 * Copyright (C) 2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifdef LIVES_IN_SYSTEM
#define LOG_TAG "lineage.livedisplay@2.0-service-sysfs"
#else
#define LOG_TAG "vendor.lineage.livedisplay@2.0-service-sysfs"
#endif

#include <android-base/logging.h>
#include <binder/ProcessState.h>
#include <hidl/HidlTransportSupport.h>

#include "AdaptiveBacklight.h"
#include "AutoContrast.h"
#include "ColorEnhancement.h"
#include "DisplayColorCalibration.h"
#include "ReadingEnhancement.h"
#include "SunlightEnhancement.h"

using android::OK;
using android::sp;
using android::status_t;
using android::hardware::configureRpcThreadpool;
using android::hardware::joinRpcThreadpool;

using ::vendor::lineage::livedisplay::V2_0::IAdaptiveBacklight;
using ::vendor::lineage::livedisplay::V2_0::IAutoContrast;
using ::vendor::lineage::livedisplay::V2_0::IColorEnhancement;
using ::vendor::lineage::livedisplay::V2_0::IDisplayColorCalibration;
using ::vendor::lineage::livedisplay::V2_0::IReadingEnhancement;
using ::vendor::lineage::livedisplay::V2_0::ISunlightEnhancement;
using ::vendor::lineage::livedisplay::V2_0::sysfs::AdaptiveBacklight;
using ::vendor::lineage::livedisplay::V2_0::sysfs::AutoContrast;
using ::vendor::lineage::livedisplay::V2_0::sysfs::ColorEnhancement;
using ::vendor::lineage::livedisplay::V2_0::sysfs::DisplayColorCalibration;
using ::vendor::lineage::livedisplay::V2_0::sysfs::ReadingEnhancement;
using ::vendor::lineage::livedisplay::V2_0::sysfs::SunlightEnhancement;

int main() {
    // sysfs-based HALs
    sp<AdaptiveBacklight> ab;
    sp<AutoContrast> ac;
    sp<ColorEnhancement> ce;
    sp<DisplayColorCalibration> dcc;
    sp<ReadingEnhancement> re;
    sp<SunlightEnhancement> se;

    status_t status = OK;

    LOG(INFO) << "LiveDisplay HAL service is starting.";

    ab = new AdaptiveBacklight();
    if (ab == nullptr) {
        LOG(ERROR) << "Can not create an instance of LiveDisplay HAL AdaptiveBacklight Iface, "
                      "exiting.";
        goto shutdown;
    }

    ac = new AutoContrast();
    if (ac == nullptr) {
        LOG(ERROR) << "Can not create an instance of LiveDisplay HAL AutoContrast Iface, exiting.";
        goto shutdown;
    }

    ce = new ColorEnhancement();
    if (ce == nullptr) {
        LOG(ERROR)
                << "Can not create an instance of LiveDisplay HAL ColorEnhancement Iface, exiting.";
        goto shutdown;
    }

    dcc = new DisplayColorCalibration();
    if (dcc == nullptr) {
        LOG(ERROR) << "Can not create an instance of LiveDisplay HAL DisplayColorCalibration Iface,"
                   << " exiting.";
        goto shutdown;
    }

    re = new ReadingEnhancement();
    if (re == nullptr) {
        LOG(ERROR) << "Can not create an instance of LiveDisplay HAL ReadingEnhancement Iface, "
                      "exiting.";
        goto shutdown;
    }

    se = new SunlightEnhancement();
    if (se == nullptr) {
        LOG(ERROR) << "Can not create an instance of LiveDisplay HAL SunlightEnhancement Iface, "
                      "exiting.";
        goto shutdown;
    }

    configureRpcThreadpool(1, true /*callerWillJoin*/);

    if (ab->isSupported()) {
        status = ab->registerAsService();
        if (status != OK) {
            LOG(ERROR) << "Could not register service for LiveDisplay HAL AdaptiveBacklight Iface ("
                       << status << ")";
            goto shutdown;
        }
    }

    if (ac->isSupported()) {
        status = ac->registerAsService();
        if (status != OK) {
            LOG(ERROR) << "Could not register service for LiveDisplay HAL AutoContrast Iface ("
                       << status << ")";
            goto shutdown;
        }
    }

    if (ce->isSupported()) {
        status = ce->registerAsService();
        if (status != OK) {
            LOG(ERROR) << "Could not register service for LiveDisplay HAL ColorEnhancement Iface ("
                       << status << ")";
            goto shutdown;
        }
    }

    if (dcc->isSupported()) {
        status = dcc->registerAsService();
        if (status != OK) {
            LOG(ERROR) << "Could not register service for LiveDisplay HAL DisplayColorCalibration"
                       << " Iface (" << status << ")";
            goto shutdown;
        }
    }

    if (re->isSupported()) {
        status = re->registerAsService();
        if (status != OK) {
            LOG(ERROR) << "Could not register service for LiveDisplay HAL ReadingEnhancement Iface"
                       << " (" << status << ")";
            goto shutdown;
        }
    }

    if (se->isSupported()) {
        status = se->registerAsService();
        if (status != OK) {
            LOG(ERROR) << "Could not register service for LiveDisplay HAL SunlightEnhancement Iface"
                       << " (" << status << ")";
            goto shutdown;
        }
    }

    LOG(INFO) << "LiveDisplay HAL service is ready.";
    joinRpcThreadpool();
    // Should not pass this line

shutdown:
    // In normal operation, we don't expect the thread pool to shutdown
    LOG(ERROR) << "LiveDisplay HAL service is shutting down.";
    return 1;
}
