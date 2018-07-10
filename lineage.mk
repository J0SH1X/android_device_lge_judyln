#
# Copyright (C) 2018 The LineageOS Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Inherit from those products. Most specific first.
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base_telephony.mk)

# Inherit from judyln device
$(call inherit-product, device/lge/judyln/device.mk)

# Inherit some common Lineage stuff.
$(call inherit-product, vendor/lineage/config/common_full_phone.mk)

PRODUCT_NAME := lineage_judyln
PRODUCT_DEVICE := judyln
PRODUCT_MANUFACTURER := lge
PRODUCT_BRAND := lge
PRODUCT_MODEL := lge lm-g710em

PRODUCT_GMS_CLIENTID_BASE := android-lge

TARGET_VENDOR_PRODUCT_NAME := LG G7
TARGET_VENDOR_DEVICE_NAME := LG G7

PRODUCT_BUILD_PROP_OVERRIDES += \
    TARGET_DEVICE=LG G7 \
    PRODUCT_NAME=LG G7 \
    PRIVATE_BUILD_DESC="judyln_lao_com-user 8.0.0 OPR1.170623.032 1814122512cdb.FGN release-keys"

BUILD_FINGERPRINT := lge/judyln_lao_com/judyln:8.0.0/OPR1.170623.032/1814122512cdb.FGN:user/release-keys
