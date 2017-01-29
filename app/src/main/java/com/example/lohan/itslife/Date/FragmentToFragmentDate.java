package com.example.lohan.itslife.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by lohan on 13-11-2016.
 */

public class FragmentToFragmentDate extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    FragmentToFragmentDateChanged act;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act=(FragmentToFragmentDateChanged)context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        //month:0-11
        String y = String.valueOf(year);
        String m = String.valueOf(month + 1);
        String d = String.valueOf(day);
        String date = d + "/" + m + "/" + y;
        act.onFragmentToFragmentDateChanged(date);
    }
}
