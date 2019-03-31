package org.lineageos.settings.device.dac.utils;

import android.media.AudioSystem;
import android.os.SystemProperties;
import android.util.Log;

public class QuadDAC {

    private static final String TAG = "QuadDAC";

    private QuadDAC() {}

    public static void enable()
    {
        //int digital_filter = SystemProperties.getInt(Constants.PROPERTY_DIGITAL_FILTER,0);
        //int sound_preset = SystemProperties.getInt(Constants.PROPERTY_SOUND_PRESET,0);
        int left_balance = SystemProperties.getInt(Constants.PROPERTY_LEFT_BALANCE,0);
        int right_balance = SystemProperties.getInt(Constants.PROPERTY_RIGHT_BALANCE,0);
        //int dop = SystemProperties.getInt(Constants.PROPERTY_HIFI_DAC_DOP,0);
        AudioSystem.setParameters(Constants.SET_DAC_ON_COMMAND);
        //setSoundPreset(sound_preset);
        //setHifiDACdop(dop);
        //setDigitalFilter(digital_filter);
        setLeftBalance(left_balance);
        setRightBalance(right_balance);
    }

    public static void disable()
    {
        AudioSystem.setParameters(Constants.SET_DAC_OFF_COMMAND);
    }

    public static void setHifiDACdop(int dop)
    {
        AudioSystem.setParameters(Constants.SET_HIFI_DAC_DOP_COMMAND + dop);
        SystemProperties.set(Constants.PROPERTY_HIFI_DAC_DOP, Integer.toString(dop));
    }

    public static int getHifiDACdop()
    {
        return SystemProperties.getInt(Constants.PROPERTY_HIFI_DAC_DOP, 0);
    }

    public static void setDigitalFilter(int filter)
    {
        AudioSystem.setParameters(Constants.SET_DIGITAL_FILTER_COMMAND + filter);
        SystemProperties.set(Constants.PROPERTY_DIGITAL_FILTER, Integer.toString(filter));
    }

    public static void setSoundPreset(int preset)
    {
        AudioSystem.setParameters(Constants.SET_SOUND_PRESET_COMMAND + preset);
        SystemProperties.set(Constants.PROPERTY_SOUND_PRESET, Integer.toString(preset));
    }

    public static void setLeftBalance(int balance)
    {
        AudioSystem.setParameters(Constants.SET_LEFT_BALANCE_COMMAND + balance);
        SystemProperties.set(Constants.PROPERTY_LEFT_BALANCE, Integer.toString(balance));
    }

    public static void setRightBalance(int balance)
    {
        AudioSystem.setParameters(Constants.SET_RIGHT_BALANCE_COMMAND + balance);
        SystemProperties.set(Constants.PROPERTY_RIGHT_BALANCE, Integer.toString(balance));
    }

    public static boolean isEnabled()
    {
        String hifi_dac = SystemProperties.get(Constants.PROPERTY_HIFI_DAC_ENABLED);
        return hifi_dac.equals("ON");
    }

}
