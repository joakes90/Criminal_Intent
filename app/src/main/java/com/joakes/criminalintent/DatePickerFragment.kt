package com.joakes.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListner = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            val bundle = Bundle().apply {
                putSerializable(ARG_DATE, resultDate)
            }
            parentFragmentManager.setFragmentResult(DIALOG_REQUEST_KEY, bundle)
        }

        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListner,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {
        val DIALOG_REQUEST_KEY = "DateDialogRequestKey"
        val ARG_DATE = "date"

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)

            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}