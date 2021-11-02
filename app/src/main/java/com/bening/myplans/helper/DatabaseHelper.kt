package com.bening.myplans.helper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DatabaseHelper.DB_NAME, null, DatabaseHelper.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_PLAN = "CREATE TABLE 'Plan' (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, DESCRIPTION TEXT, STATUS TEXT)"
        val CREATE_TABLE_PROBLEM = "CREATE TABLE 'Problem' (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, DESCRIPTION TEXT, STATUS TEXT, PlanID INTEGER)"
        db.execSQL(CREATE_TABLE_PROBLEM)
        db.execSQL(CREATE_TABLE_PLAN)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE_PLAN = "DROP TABLE IF EXISTS 'Plan'"
        db.execSQL(DROP_TABLE_PLAN)
        onCreate(db)
    }

    fun addProblem(Name: String, Description: String, PlanID: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", Name)
        contentValues.put("DESCRIPTION", Description)
        contentValues.put("STATUS", "active")
        contentValues.put("PlanID", PlanID)
        db.insert("Problem", null, contentValues)
    }

    fun updateProblem(id: Int, Name: String, Description: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", Name)
        contentValues.put("DESCRIPTION", Description)
        db.update("Problem", contentValues, "ID='$id'", null)
    }

    fun addPlan(Name: String, Description: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", Name)
        contentValues.put("DESCRIPTION", Description)
        contentValues.put("STATUS", "start")
        db.insert("Plan", null, contentValues)
    }

    fun getPlansActive(): Cursor {
        val db = this.writableDatabase
        val res = db.rawQuery(
            "SELECT ID, NAME, DESCRIPTION FROM 'Plan' WHERE STATUS='start'",
            null
        )
        return res
    }

    fun getPlansFinish(): Cursor {
        val db = this.writableDatabase
        val res = db.rawQuery(
            "SELECT ID, NAME, DESCRIPTION FROM 'Plan' WHERE STATUS='finish'",
            null
        )
        return res
    }

    fun delPlans(id: Int) {
        val db = this.writableDatabase
        db.delete("Plan", "ID='$id'", null)
        db.delete("Problem", "PlanID='$id'", null)
    }

    fun getProblems(id: Int): Cursor {
        val db = this.writableDatabase
        val res = db.rawQuery(
            "SELECT ID, NAME, DESCRIPTION, STATUS FROM 'Problem' WHERE PlanID='$id'",
            null
        )
        return res
    }

    fun finishPlan(id: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("STATUS", "finish")
        db.update("Plan", contentValues, "ID='$id'", null)
    }

    fun solvedProblem(id: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("STATUS", "solved")
        db.update("Problem", contentValues, "ID='$id'", null)
    }

    fun unSolvedProblem(id: Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("STATUS", "active")
        db.update("Problem", contentValues, "ID='$id'", null)
    }

    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "MyPlans"
    }
}