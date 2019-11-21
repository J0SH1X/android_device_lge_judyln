#!/vendor/bin/sh

F=`getprop ro.boot.vendor.lge.fingerprint_sensor`
G=`getprop ro.boot.vendor.lge.gyro`
N=`getprop ro.boot.vendor.lge.nfc.vendor`
HW_VARI=""
VENDOR_FINGERPRINT=$(echo $(grep -w ro.vendor.build.fingerprint /vendor/build.prop) | sed -e 's/ro.vendor.build.fingerprint=//;s/^[ \t]*//;s/[ \t].*//')

# For FGN HW Variants
if [ $F == "1" ]; then
    #add F
    log -p i "[LGE][SBP] F should be added!"
    HW_VARI=${HW_VARI}F
fi

if [ $G == "1" ]; then
    #add G
    log -p i "[LGE][SBP] G should be added!"
    HW_VARI=${HW_VARI}G
fi

if [ $N != "none" ]; then
    #add N
    log -p i "[LGE][SBP] N should be added!"
    HW_VARI=${HW_VARI}N
fi

if [ $HW_VARI != "" ]; then
    VENDOR_FINGERPRINT=$(echo ${VENDOR_FINGERPRINT} | sed -r "s/(:user)|(:eng)/.${HW_VARI}&/")
fi

# For Russia Build
RU=`getprop ro.boot.vendor.lge.ru`
VENDOR_PRODUCT_NAME=$(echo $(grep -w ro.product.vendor.name /vendor/build.prop) | sed -e 's/ro.product.vendor.name=//;s/^[ \t]*//;s/[ \t].*//')

if [ $RU == "1" ]; then
    VENDOR_FINGERPRINT=$(echo ${VENDOR_FINGERPRINT} | sed -r 's/_com/_ru/')
    VENDOR_PRODUCT_NAME=$(echo ${VENDOR_PRODUCT_NAME} | sed -r 's/_com/_ru/')
fi

# For EEA
EEA=`getprop ro.boot.product.lge.eea_type`

if [ -n "$EEA" ]; then
    log -p i -t hw_vari "[LGE][SBP] $EEA"
    VENDOR_FINGERPRINT=$(echo ${VENDOR_FINGERPRINT} | sed -r "s/_com/_eea/")
    VENDOR_PRODUCT_NAME=$(echo ${VENDOR_PRODUCT_NAME} | sed -r "s/_com/_eea/")
fi

setprop ro.vendor.lge.build.fingerprint ${VENDOR_FINGERPRINT}

# For Product Name Variants
if [ $VENDOR_PRODUCT_NAME != "" ]; then
    setprop ro.vendor.lge.product.name ${VENDOR_PRODUCT_NAME}
fi

# For Product Model Variants
BOOT_PRODUCT_MODEL=`getprop ro.boot.vendor.lge.product.model`
VENDOR_PRODUCT_MODEL=$(echo $(grep -w ro.product.vendor.model /vendor/build.prop) | sed -e 's/ro.product.vendor.model=//;s/^[ \t]*//;s/[ \t].*//')

if [ $VENDOR_PRODUCT_MODEL != "" ]; then
    setprop ro.vendor.lge.product.model ${BOOT_PRODUCT_MODEL}
fi

exit 0
