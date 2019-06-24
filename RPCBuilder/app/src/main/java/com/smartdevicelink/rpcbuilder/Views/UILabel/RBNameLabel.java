package com.smartdevicelink.rpcbuilder.Views.UILabel;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import com.smartdevicelink.rpcbuilder.DataModels.RBParam;

/**
 * Created by austinkirk on 11/16/16.
 */

public class RBNameLabel extends AppCompatTextView {
    private Boolean enabled = true;
    private String rbParamName = "";

    public RBNameLabel(Context context){
        super(context);
    }

    public void format(RBParam rbParam){
        if(rbParam.mIsMandatory == null) {
            rbParam.mIsMandatory = false; // set to false by default
        }else if(rbParam.mIsMandatory == false){
            enabled = false;
        }
        rbParamName = rbParam.name;
        String spaced_name = convertCamelCase(rbParam.name);
        this.setText( rbParam.mIsMandatory ? spaced_name + "*" : spaced_name );
        this.setClickable( rbParam.mIsMandatory ? false : true);
        this.setTextColor( enabled ? Color.WHITE : Color.GRAY);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                enabled = !enabled;
                ((TextView) view).setTextColor( enabled ? Color.WHITE : Color.GRAY);

                LinearLayout container = (LinearLayout) getParent();
                View child = container.getChildAt(1);
                child.setEnabled(enabled);
            }
        });
    }

    public static String convertCamelCase(String camelcase){
        String spaced_name = "";
        for (String w : camelcase.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            if(w.length() > 1)
                spaced_name += w.substring(0, 1).toUpperCase() + w.substring(1) + " ";
            else
                spaced_name += w.substring(0, 1).toUpperCase() + " ";
        }
        return spaced_name;
    }

    public Boolean isChecked(){return enabled;}

    public String getRBParamName(){return rbParamName;};
}
