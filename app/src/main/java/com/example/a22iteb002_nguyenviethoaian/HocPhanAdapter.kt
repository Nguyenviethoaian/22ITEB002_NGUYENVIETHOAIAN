package com.example.a22iteb002_nguyenviethoaian

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a22iteb002_nguyenviethoaian.DBHelper.Companion.TABLE_NAME

class HocPhanAdapter(private val context: Context) : RecyclerView.Adapter<HocPhanAdapter.ViewHolder>() {

    private var courseList: ArrayList<HocPhan> = ArrayList()
    private var dbHelper: DBHelper = DBHelper(context, null)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val maHocPhan: TextView = view.findViewById(R.id.tvMaHP)
        val tenHocPhan: TextView = view.findViewById(R.id.tvTenHP)
        val soTC: TextView = view.findViewById(R.id.tvSoTC)
        val hocKy: TextView = view.findViewById(R.id.tvHK)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hocphan_list_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courseList[position]
        holder.maHocPhan.text = course.id.toString()
        holder.tenHocPhan.text = course.name
        holder.soTC.text = course.number
        holder.hocKy.text = course.semester
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    fun setCourses(courses: ArrayList<HocPhan>) {
        courseList.clear()
        courseList.addAll(courses)
        notifyDataSetChanged()
    }



    fun closeCursor() {
        dbHelper.close()
    }


    fun getItem(position: Int): Cursor? {
        val db = dbHelper.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME LIMIT 1 OFFSET $position", null)
    }


}
