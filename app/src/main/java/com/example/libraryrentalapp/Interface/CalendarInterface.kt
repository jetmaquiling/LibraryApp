package com.example.libraryrentalapp.Interface

import java.time.LocalDate

interface CalendarInterface {
    var date_to_return: LocalDate
    fun setDateRent(year: Int, month:Int, day:Int)
}