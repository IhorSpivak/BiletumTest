package com.example.biletum.helper

import java.text.SimpleDateFormat
import java.util.*


object DateHelper {

    val sdfFacebook = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val sdfVk = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val sdfServer = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val sdfOrder = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    val sdfOrder1 = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    val sdfEditProfile = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())


    val sdfServerTime = SimpleDateFormat("HH:mm")

    fun parseFacebookDate(dateString: String?): String {
        if (dateString != null) {
            try {
                return sdfServer.format(sdfFacebook.parse(dateString))
            } catch (ignored: Exception) {
                return ""
            }

        }
        return ""
    }

    fun parseDateForDateFlight(dateString: String?): String {
        if (dateString != null) {
            try {
                return sdfOrder1.format(sdfServer.parse(dateString))
            } catch (ignored: Exception) {
                return ""
            }

        }
        return ""
    }

    fun parseDateForServerDateBirth(dateString: String?): String {
        if (dateString != null) {
            try {
                return sdfServer.format(sdfOrder1.parse(dateString))
            } catch (ignored: Exception) {
                return ""
            }

        }
        return ""
    }


    fun parseVkDate(dateString: String?): String {
        if (dateString != null) {
            try {
                return sdfServer.format(sdfVk.parse(dateString))
            } catch (ignored: Exception) {
                return ""
            }

        }
        return ""
    }

    fun getDateForServerFromCalendar(calendar: Calendar): String {
        return sdfServer.format(calendar.getTime())
    }

    fun getDateForServerFromCalendar(date: Date): String {
        return sdfServer.format(date)
    }

    fun getTimeForServerFromCalendar(calendar: Calendar): String {
        return sdfServerTime.format(calendar.getTime())
    }

    fun getStringFromCalendarForOrder(calendar: Calendar): String {
        return sdfOrder.format(calendar.getTime())

    }

    fun getStringFromCalendarForEditProfile(calendar: Calendar): String {
        return sdfEditProfile.format(calendar.getTime())

    }
}