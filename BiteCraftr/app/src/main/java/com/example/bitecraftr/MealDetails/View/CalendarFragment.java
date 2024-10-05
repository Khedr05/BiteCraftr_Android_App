package com.example.bitecraftr.MealDetails.View;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;

import com.example.bitecraftr.R;
import java.util.Calendar;

public class CalendarFragment extends DialogFragment {
    private OnDateSelectedListener listener;

    // This method allows the parent fragment to set the listener
    public void setOnDateSelectedListener(OnDateSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_calendar, null);

        // Get references to the views
        DatePicker datePicker = view.findViewById(R.id.datePicker);
        ImageButton btnSelectDate = view.findViewById(R.id.btnSelectDate);

        // Create dialog
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(view);

        // Set default date to current date
        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);

        // Set minimum date to current date
        datePicker.setMinDate(System.currentTimeMillis());

        // Handle button click to select date
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Month is 0-indexed
                int year = datePicker.getYear();

                String selectedDate = day + "/" + month + "/" + year;

                // Call the listener to notify the parent fragment
                if (listener != null) {
                    listener.onDateSelected(selectedDate);
                }

                // Dismiss dialog
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
