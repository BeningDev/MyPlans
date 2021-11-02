package com.bening.myplans.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DatabaseHelper.DB_NAME, null, DatabaseHelper.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_PLAN = "CREATE TABLE 'Plan' (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, DESCRIPTION TEXT, STATUS TEXT)"
        db.execSQL(CREATE_TABLE_PLAN)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_PLAN = "DROP TABLE IF EXISTS 'Plan'"
        db.execSQL(DROP_TABLE_PLAN)
        onCreate(db)
    }

    fun addPlan(Name: String, Description: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", Name)
        contentValues.put("DESCRIPTION", Description)
        contentValues.put("STATUS", "start")
        db.insert("Plan", null, contentValues)
    }

    fun getPlans(): Cursor {
        val db = this.writableDatabase
        val res = db.rawQuery("SELECT ID, NAME, DESCRIPTION FROM 'Plan'", null)
        return res
    }

    fun delPlans(id: Int) {
        val db = this.writableDatabase
        db.delete("Plan", "ID='" + id + "'", null)
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "MyPlans"
    }
}