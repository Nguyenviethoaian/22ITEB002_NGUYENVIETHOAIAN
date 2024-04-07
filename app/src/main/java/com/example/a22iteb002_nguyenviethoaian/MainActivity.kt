package com.example.a22iteb002_nguyenviethoaian

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {
    private lateinit var lv: ListView
    private lateinit var myList: ArrayList<String>
    private lateinit var hocPhanAdapter: ArrayAdapter<String>
    private lateinit var myDatabase : SQLiteDatabase
    private lateinit var  edtId:EditText
    private lateinit var  edtNameCourse:EditText
    private lateinit var  edtNumber:EditText
    private lateinit var  edtSemester:EditText
    private lateinit var edtSearch:EditText
    private lateinit var btnAdd:Button
    private lateinit var btnSearch:Button
    private lateinit var btnDele:Button
    private lateinit var btnUpdate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtId=findViewById(R.id.edtId)
        edtNameCourse = findViewById(R.id.edtNameCourse)
        edtNumber= findViewById(R.id.edtNumber)
        edtSemester=findViewById(R.id.edtSemester)
        edtSearch=findViewById(R.id.edtSearch)
        btnAdd=findViewById(R.id.btnAdd)
        btnDele=findViewById(R.id.btnDele)
        btnUpdate=findViewById(R.id.btnUpdate)
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
            Toast.makeText(this,"Loi tao bang",Toast.LENGTH_SHORT).show()
        }



       btnAdd.setOnClickListener {
            val id = edtId.text.toString()
            val name = edtNameCourse.text.toString()
            val number =edtNumber.text.toString()
            val semester =edtSemester.text.toString()
            val myValue = ContentValues()
            myValue.put("id",id)
            myValue.put("name",name)
            myValue.put("number",number)
            myValue.put("semester",semester)
            val msg: String
            if(myDatabase.insert("myCourses",null,myValue).toInt() == -1 ) {
                msg  ="Fail to Insert"
            }else{
                msg ="Insert Sucessfully"
            }
            Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()
            edtId.text.clear()
            edtNameCourse.text.clear()
            edtNumber.text.clear()
            edtSemester.text.clear()
            hocPhanAdapter.notifyDataSetChanged()
        }
        btnDele.setOnClickListener {
            val id :String = edtId.text.toString()
            val n: Int = myDatabase.delete("myCourse","id=?", arrayOf(id))
            if(n==0){
                Toast.makeText(this, "Fail Delete record", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"Delete sucessfully",Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdate.setOnClickListener{
                val id = edtId.text.toString()
                val name =edtNameCourse.text.toString()
                val number = edtNumber.text.toString()
                val semester =edtSemester.text.toString()
                val myValue = ContentValues()
                myValue.put("id",id)
                myValue.put("name",name)
                myValue.put("number",number)
                myValue.put("semester",semester)
                val n: Int = myDatabase.update("myCourses",myValue,"id=?", arrayOf(id))
                if(n==0){
                    Toast.makeText(this, "Fail Delete record", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this,"Delete sucessfully",Toast.LENGTH_SHORT).show()
                }
                if(n==0){
                    Toast.makeText(this, "Fail Update record", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this,n.toString() +"Update sucessfully",Toast.LENGTH_SHORT).show()
                }
        }



        btnSearch.setOnClickListener {
            val search:String = edtSearch.text.toString()
            val msg:String="SELECT * FROM myCourses WHERE id LIKE '%$search%' OR name LIKE '%$search%'  OR number LIKE '%$search%' OR semester LIKE '%$search%'"
            try{
                myDatabase.execSQL(msg)
                Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                Toast.makeText(this,"Loi",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem = item.itemId
        when (idItem) {
            R.id.menuItem1 -> {
                val intent = Intent(this, ActivityPrime::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem2 -> {
                val intent = Intent(this, ActivityPT::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
//            R.id.menuItem3_1 -> {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.menuItem3_2 -> {
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
        val idItem = item.itemId
        when (idItem) {
            R.id.menuItem1 -> {
                val intent = Intent(this, ActivityPrime::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem2 -> {
                val intent = Intent(this, ActivityPT::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
//            R.id.menuItem3_1 -> {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.menuItem3_2 -> {
//                val intent = Intent(this, ActivityPrime::class.java)
//                startActivity(intent)
//                return true
//            }
        }
        return super.onContextItemSelected(item)
    }

}
