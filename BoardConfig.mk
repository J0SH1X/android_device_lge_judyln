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

DEVICE_PATH := device/lge/judypn

# inherit from common v30
-include device/lge/sdm845-common/BoardConfigCommon.mk

# Bluetooth
BOARD_BLUETOOTH_BDROID_BUILDCFG_INCLUDE_DIR := $(DEVICE_PATH)/bluetooth

# HIDL
DEVICE_MANIFEST_FILE := $(DEVICE_PATH)/manifest.xml
DEVICE_MATRIX_FILE := $(DEVICE_PATH)/compatibility_matrix.xml
DEVICE_FRAMEWORK_COMPATIBILITY_MATRIX_FILE := $(DEVICE_PATH)/framework_compatibility_matrix.xml

# Kernel
BOARD_KERNEL_CMDLINE += androidboot.hardware=judypn
TARGET_KERNEL_CONFIG := lineageos_judypn_defconfig
TARGET_KERNEL_SOURCE := kernel/lge/sdm845

# Partitions
BOARD_USERDATAIMAGE_PARTITION_SIZE := 113775689728
BOARD_FLASH_BLOCK_SIZE := 262144 # (BOARD_KERNEL_PAGESIZE * 64)

BOARD_ROOT_EXTRA_FOLDERS := oem persdata
BOARD_ROOT_EXTRA_SYMLINKS := \
    /mnt/vendor/persist:/persist \
    /mnt/product/carrier:/carrier \
    /vendor/dps:/dsp \
    /mnt/vendor/eri:/eri \
    /mnt/vendor/absolute:/persdata/absolute \
    /vendor/firmware_mnt:/firmware

# Recovery
TARGET_RECOVERY_FSTAB := $(DEVICE_PATH)/rootdir/etc/fstab.judypn

# Treble
BOARD_VNDK_VERSION := current

# inherit from the proprietary version
-include vendor/lge/judypn/BoardConfigVendor.mk
