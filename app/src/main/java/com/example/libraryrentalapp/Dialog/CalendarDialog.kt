package com.example.libraryrentalapp.Dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.libraryrentalapp.BookPage
import com.example.libraryrentalapp.Interface.CalendarInterface
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalQueries.localDate
import java.util.*



class CalendarDialog(calendarInterface: CalendarInterface): DialogFragment() {

    var calendarInterface: CalendarInterface

    init {
        this.calendarInterface = calendarInterface
    }

    var minPeriod = Date()
    var maxPeriod = Date()
    lateinit var defaultDate: LocalDate
    lateinit var nDate: LocalDate

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        defaultDate = LocalDate.now().plusDays(3)
        nDate = LocalDate.now().plusDays(30)

        val defaultZoneId: ZoneId = ZoneId.systemDefault()
        var maxPeriod = Date.from(nDate.atStartOfDay(defaultZoneId).toInstant())
        isCancelable = false
        val cal = Calendar.getInstance()


        val dialog = DatePickerDialog(requireContext(), { datePicker, i, i1, i2 ->
            calendarInterface.setDateRent(i,i1,i2)
        }, calendarInterface.date_to_return.year, calendarInterface.date_to_return.monthValue - 1, calendarInterface.date_to_return.dayOfMonth)


        dialog.datePicker.minDate = minPeriod.time
        dialog.datePicker.maxDate =maxPeriod.time
        return dialog
    }
}