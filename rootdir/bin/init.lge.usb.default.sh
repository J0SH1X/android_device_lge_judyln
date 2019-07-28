#!/vendor/bin/sh

target_operator=`getprop ro.vendor.lge.build.target_operator`
ui_version=`getprop ro.vendor.lge.lguiversion`
case "$target_operator" in
    "ATT")
        default="charge_only"
        ;;
    "VZW")
        if [ -f "/vendor/etc/usbautorun.iso" ]; then
            if [ -f "/sys/class/android_usb/android0/f_cdrom_storage/lun/cdrom_usbmode" ]; then
                echo 0 > /sys/class/android_usb/android0/f_cdrom_storage/lun/cdrom_usbmode
            fi
            default="charge_only"
        else
            default="mtp"
        fi
        ;;
    *)
        if [ "${ui_version//./}" -ge "50" ]; then
            default="charge_only"
        else
            default="mtp"
        fi
    ;;
esac

function waitForState() {
    local i
    local state=$1
    local value
    for i in {1..20}; do
        value=`getprop vendor.lge.usb.state`
        if [ "x${state}" = "x${value}" ]; then
            echo 0
            return
        fi
        sleep 0.050
    done

    echo 1
    return
}

function setUsbConfig() {
    setprop vendor.lge.usb.config $1
    waitForState $1
}

function updateDefaultFunction() {
    setprop vendor.lge.usb.persist.config $1
    setprop vendor.lge.usb.config $1
}

usb_config=$1
case "$usb_config" in
    "boot") #factory status, select default
        setUsbConfig none
        updateDefaultFunction $default
    ;;
    "boot,adb") #factory status, select default
        setUsbConfig none
        updateDefaultFunction ${default},adb
    ;;
    *) ;; #USB persist config exists, do nothing
esac
