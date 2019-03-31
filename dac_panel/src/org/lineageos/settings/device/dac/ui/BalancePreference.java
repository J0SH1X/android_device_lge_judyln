package org.lineageos.settings.device.dac.ui;


import android.content.Context;
import android.os.SystemProperties;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.widget.TextView;

import org.lineageos.settings.device.dac.R;
import org.lineageos.settings.device.dac.utils.Constants;
import org.lineageos.settings.device.dac.utils.QuadDAC;

public class BalancePreference extends Preference {

    private static final String TAG = "BalancePreference";

    private double left_balance = 0;
    private double right_balance = 0;

    private double max_allowed_value = 0;
    private double min_allowed_value = -6;

    private Button bt_left_plus, bt_left_minus, bt_right_plus, bt_right_minus;
    private TextView tv_left, tv_right;

    public BalancePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setLayoutResource(R.layout.balance_preference);
    }

    public BalancePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, defStyleAttr);
    }

    public BalancePreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BalancePreference(Context context) {
        this(context, null);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        holder.itemView.setClickable(false);

        bt_left_plus = (Button) holder.findViewById(R.id.bt_left_plus);
        bt_left_minus = (Button) holder.findViewById(R.id.bt_left_minus);

        bt_right_plus = (Button) holder.findViewById(R.id.bt_right_plus);
        bt_right_minus = (Button) holder.findViewById(R.id.bt_right_minus);

        tv_left = (TextView) holder.findViewById(R.id.tv_left_val);
        tv_right = (TextView) holder.findViewById(R.id.tv_right_val);

        bt_left_plus.setClickable(true);
        bt_left_minus.setClickable(true);
        bt_right_plus.setClickable(true);
        bt_right_minus.setClickable(true);

        bt_left_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLeftBalance(true);
            }
        });

        bt_left_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateLeftBalance(false);
            }
        });

        bt_right_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRightBalance(true);
            }
        });

        bt_right_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRightBalance(false);
            }
        });

        loadBalanceConfiguration();

    }

    private void loadBalanceConfiguration()
    {
        int lb = SystemProperties.getInt(Constants.PROPERTY_LEFT_BALANCE, 0);
        int rb = SystemProperties.getInt(Constants.PROPERTY_RIGHT_BALANCE,0);

        left_balance = Constants.balanceHashMapReverse.get(lb);
        right_balance = Constants.balanceHashMapReverse.get(rb);

        tv_left.setText(left_balance + " db");
        tv_right.setText(right_balance + " db");

        if(left_balance == max_allowed_value)
        {
            bt_left_plus.setEnabled(false);
        } else if(left_balance == min_allowed_value)
        {
            bt_left_minus.setEnabled(false);
        } else
        {
            bt_left_plus.setEnabled(true);
            bt_left_minus.setEnabled(true);
        }

        if(right_balance == max_allowed_value)
        {
            bt_right_plus.setEnabled(false);
        } else if(right_balance == min_allowed_value)
        {
            bt_right_minus.setEnabled(false);
        } else
        {
            bt_right_plus.setEnabled(true);
            bt_right_minus.setEnabled(true);
        }
    }

    private void updateLeftBalance(boolean increase)
    {
        StringBuilder sb = new StringBuilder();
        if(increase)
        {
            if(left_balance < max_allowed_value)
            {
                left_balance += 0.5;
                bt_left_minus.setEnabled(true);
                if(left_balance == max_allowed_value)
                {
                    bt_left_plus.setEnabled(false);
                }
            }

        } else {
            if(left_balance > min_allowed_value)
            {
                left_balance -= 0.5;
                bt_left_plus.setEnabled(true);
                if(left_balance == min_allowed_value)
                {
                    bt_left_minus.setEnabled(false);
                }
            }
        }
        sb.append(left_balance);
        sb.append(" db");
        QuadDAC.setLeftBalance(Constants.balanceHashMap.get(left_balance));
        tv_left.setText(sb.toString());
    }

    private void updateRightBalance(boolean increase)
    {
        StringBuilder sb = new StringBuilder();
        if(increase)
        {
            if(right_balance < max_allowed_value)
            {
                right_balance += 0.5;
                bt_right_minus.setEnabled(true);
                if(right_balance == max_allowed_value)
                {
                    bt_right_plus.setEnabled(false);
                }
            }

        } else {
            if(right_balance > min_allowed_value)
            {
                right_balance -= 0.5;
                bt_right_plus.setEnabled(true);
                if(right_balance == min_allowed_value)
                {
                    bt_right_minus.setEnabled(false);
                }
            }
        }
        sb.append(right_balance);
        sb.append(" db");
        QuadDAC.setRightBalance(Constants.balanceHashMap.get(right_balance));
        tv_right.setText(sb.toString());
    }

}
