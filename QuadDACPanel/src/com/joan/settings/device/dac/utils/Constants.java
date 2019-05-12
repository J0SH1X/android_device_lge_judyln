package com.joan.settings.device.dac.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String DAC_SWITCH_KEY = "quaddac_switch";
    public static final String SOUND_PRESET_KEY = "sound_preset_dropdown";
    public static final String DIGITAL_FILTER_KEY = "digital_filter_dropdown";
    public static final String BALANCE_KEY = "balance";
    public static final String HIFI_DOP_KEY = "hifi_dop_dropdown";
    public static final String HIFI_MODE_KEY = "hifi_mode_dropdown";
    public static final String AVC_VOLUME_KEY = "avc_volume_seekbar";


    public static final String SET_DAC_ON_COMMAND = "hifi_dac=on";
    public static final String SET_DAC_OFF_COMMAND = "hifi_dac=off";

    public static final String SET_HIFI_DAC_DOP_COMMAND = "hifi_dac_dop=";

    public static final String SET_DIGITAL_FILTER_COMMAND = "ESS_FILTER=";
    public static final String SET_SOUND_PRESET_COMMAND = "SOUND_PRESET=";

    public static final String SET_LEFT_BALANCE_COMMAND = "hifi_dac_l_volume=";
    public static final String SET_RIGHT_BALANCE_COMMAND = "hifi_dac_r_volume=";

    public static final String PROPERTY_HIFI_DAC_ENABLED = "persist.audio.hifi_dac";
    public static final String PROPERTY_DIGITAL_FILTER = "persist.audio.hifi_dac.digitalFilter";
    public static final String PROPERTY_SOUND_PRESET = "persist.audio.hifi_dac.soundPreset";
    public static final String PROPERTY_LEFT_BALANCE = "persist.audio.hifi_dac.left_balance";
    public static final String PROPERTY_RIGHT_BALANCE = "persist.audio.hifi_dac.right_balance";
    public static final String PROPERTY_HIFI_DAC_DOP = "persist.audio.hifi_dac.dop";
    public static final String PROPERTY_HIFI_DAC_MODE = "persist.audio.hifi_dac.mode";
    public static final String PROPERTY_HIFI_DAC_AVC_VOLUME = "persist.audio.hifi_dac.avc_volume";

    public static final String HEADSET_TYPE_SYSFS = "/sys/devices/soc/c175000.i2c/i2c-1/1-0048/headset_type";
    public static final String AVC_VOLUME_SYSFS = "/sys/devices/soc/c175000.i2c/i2c-1/1-0048/avc_volume";

    public static final HashMap<Double, Integer> balanceHashMap = new HashMap<>();
    public static final HashMap<Integer, Double> balanceHashMapReverse = new HashMap<>();

    static {
        balanceHashMap.put(0.0, 0);
        balanceHashMap.put(-0.5, 1);
        balanceHashMap.put(-1.0, 2);
        balanceHashMap.put(-1.5, 3);
        balanceHashMap.put(-2.0, 4);
        balanceHashMap.put(-2.5, 5);
        balanceHashMap.put(-3.0, 6);
        balanceHashMap.put(-3.5, 7);
        balanceHashMap.put(-4.0, 8);
        balanceHashMap.put(-4.5, 9);
        balanceHashMap.put(-5.0, 10);
        balanceHashMap.put(-5.5, 11);
        balanceHashMap.put(-6.0, 12);

        for (Map.Entry<Double, Integer> entry : balanceHashMap.entrySet()) {
            balanceHashMapReverse.put(entry.getValue(), entry.getKey());
        }

    }

}
