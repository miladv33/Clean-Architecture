package com.example.quotes.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * String list converter
 *
 * @constructor Create empty String list converter
 */
class StringListConverter {

    /**
     * My objects to stored string
     *
     * @param myObjects
     * @return
     */
    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    /**
     * From string list model
     *
     * @param value
     * @return
     */
    @TypeConverter
    fun fromStringListModel(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    /**
     * From string array list
     *
     * @param list
     * @return
     */
    @TypeConverter
    fun fromStringArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}
