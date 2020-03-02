package com.example.menuscalendar


import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 * A simple [DialogFragment] [DatePickerDialog.OnDateSetListener] subclass.
 */
class CalendarPickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(activity, "You clicked year $year month $month day $dayOfMonth",
            Toast.LENGTH_SHORT).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_picker, container, false)
    }


}
