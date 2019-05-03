package org.lineageos.settings.device.dac;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class QuadDACPanelActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new QuadDACPanelFragment()).commit();
    }

}
