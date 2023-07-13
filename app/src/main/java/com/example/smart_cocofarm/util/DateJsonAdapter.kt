package com.example.smart_cocofarm.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.google.gson.JsonParseException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.io.IOException

class DateJsonAdapter : TypeAdapter<Date>() {
    private val DATE_FORMAT = SimpleDateFormat("MMM dd, yyyy, h:mm:ss a", Locale.US)

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Date?) {
        if (value == null) {
            out.nullValue()
        } else {
            val formattedDate = DATE_FORMAT.format(value)
            out.value(formattedDate)
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Date? {
        if (`in`.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return null
        }
        val dateString = `in`.nextString()
        return try {
            DATE_FORMAT.parse(dateString)
        } catch (e: ParseException) {
            throw JsonParseException("Error parsing date: $dateString", e)
        }
    }
}