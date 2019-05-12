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
$(call inherit-product, $(SRC_TARGET_DIR)/product/languages_full.mk)

DEVICE_PACKAGE_OVERLAYS += \
    debice/lge/judyln/overlay

PRODUCT_PACKAGES += \
    android.hardware.light@2.0-service.judyln

# Get non-open-source specific aspects
$(call inherit-product-if-exists, vendor/lge/judyln/judyln-vendor.mk)

# common v30
$(call inherit-product, device/lge/sdm845-common/sdm845.mk)

