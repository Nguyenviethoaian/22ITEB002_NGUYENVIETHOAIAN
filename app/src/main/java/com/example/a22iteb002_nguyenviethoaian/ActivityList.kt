package com.example.a22iteb002_nguyenviethoaian

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb002_nguyenviethoaian.databinding.ActivityListBinding
import com.example.a22iteb002_nguyenviethoaian.databinding.ActivityMainBinding


class ActivityList : AppCompatActivity() {
    private lateinit var lv: ListView
    private lateinit var myList: ArrayList<String>
    private lateinit var hocPhanAdapter: ArrayAdapter<String>
    private lateinit var myDatabase : SQLiteDatabase
    private lateinit var edtSearch: EditText
    private lateinit var  edtId: EditText
    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        edtId=findViewById(R.id.edtId)
        edtSearch=findViewById(R.id.edtSearch)
        btnSearch=findViewById(R.id.btnSearch)


        lv= findViewById(R.id.lv)
        myList=  ArrayList<String>()
        hocPhanAdapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,myList)
        lv.adapter= hocPhanAdapter
        myDatabase = openOrCreateDatabase("COURSE", MODE_PRIVATE,null)

        try{
            val sql: String = "CREATE TABLE myCourses(id TEXT primary key,name,number,semester TEXT)"
            myDatabase.execSQL(sql)
        }catch (e : Exception){
            Toast.makeText(this,"Loi", Toast.LENGTH_SHORT).show()
        }
        btnSearch.setOnClickListener {
            val search:String = edtSearch.text.toString()
            val msg:String="SELECT * FROM myCourses WHERE id LIKE '%$search%' OR name LIKE '%$search%'  OR number LIKE '%$search%' OR semester LIKE '%$search%'"
            myDatabase.execSQL(msg)
            hocPhanAdapter.notifyDataSetChanged()
        }

        // Rest of your code
    }

    // Rest of your code
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem = item.itemId
        when(idItem){
            R.id.menuItem1->{
                val intent = Intent(this, ActivityPrime::class.java)
                startActivity(intent)
                return true
            }

            R.id.menuItem2->{
                val intent = Intent(this, ActivityPT::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
//            R.id.menuItem3_1->
//            {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.menuItem3_2->{
//                val intent = Intent(this, ActivityList::class.java)
//                startActivity(intent)
//                return true
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_context, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val idItem =item.itemId
        when(idItem){
            R.id.menuItem1->{
                val intent = Intent(this, ActivityPrime::class.java)
                startActivity(intent)
                return true
            }

            R.id.menuItem2->{
                val intent = Intent(this, ActivityPT::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
//            R.id.menuItem3_1->
//            {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.menuItem3_2->{
//                val intent = Intent(this, ActivityPrime::class.java)
//                startActivity(intent)
//                return true
//            }
        }
        return super.onContextItemSelected(item)
    }
}

//private fun RecyclerView.addOnItemClickListener(onClickListener: (position: Int, view: View) -> Unit) {
//    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
//        override fun onChildViewAttachedToWindow(view: View) {
//            view.setOnClickListener {
//                val holder = getChildViewHolder(view)
//                onClickListener(holder.adapterPosition, view)
//            }
//        }
//
//        override fun onChildViewDetachedFromWindow(view: View) {
//            // No implementation needed
//        }
//    })
//}
