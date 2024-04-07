package com.example.a22iteb002_nguyenviethoaian

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                NUMBER_COL + " TEXT," +
                SEMESTER_COL + " TEXT" + ")")
        db.execSQL(query)
    }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addName(name: String, number: String, semester: String){
        val values = ContentValues()
        values.put(NAME_COl, name)
        values.put(NUMBER_COL, number)
        values.put(SEMESTER_COL, semester)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun search(search: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE NAME_COL LIKE ? OR NUMBER_COL LIKE ? OR SEMESTER_COL LIKE ?", arrayOf("%$search%", "%$search%", "%$search%"))
    }

    fun deleData(id: Int): Boolean {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$ID_COL=?", arrayOf(id.toString())) > 0
    }

    fun readCourses(): ArrayList<HocPhan> {
        val db = this.readableDatabase

        val cursorCourses = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        val courseArrayList: ArrayList<HocPhan> = ArrayList<HocPhan>()
        if (cursorCourses.moveToFirst()) {
            do {
                courseArrayList.add(
                    HocPhan(
                        cursorCourses.getInt(0),
                        cursorCourses.getString(3),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2)
                    )
                )
            } while (cursorCourses.moveToNext())
        }
        cursorCourses.close()
        return courseArrayList
    }
    fun updateData(id: Int, name: String, number: String, semester: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COl, name)
        values.put(NUMBER_COL, number)
        values.put(SEMESTER_COL, semester)
        val rowsAffected = db.update(TABLE_NAME, values, "$ID_COL = ?", arrayOf(id.toString()))
        db.close()

        return rowsAffected
    }


    companion object{
        private val DATABASE_NAME = "COURSE"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "my_course"
        val ID_COL = "id"
        val NAME_COl = "name"
        val NUMBER_COL = "number"
        val SEMESTER_COL = "semester"
    }
}
