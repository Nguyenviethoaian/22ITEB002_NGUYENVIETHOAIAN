package com.example.a22iteb002_nguyenviethoaian

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityPT : AppCompatActivity() {
    lateinit var edtA: EditText
    lateinit var edtB: EditText
    lateinit var btnTinh: Button
    lateinit var btnHuy: Button
    lateinit var txtKq: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pt)
        btnTinh = findViewById(R.id.btnTinh)
        btnHuy = findViewById(R.id.btnHuy)
        edtA = findViewById<EditText>(R.id.edtA)
        edtB = findViewById<EditText>(R.id.edtB)
        txtKq = findViewById<TextView>(R.id.txtKq)
        //controls()
        btnTinh.setOnClickListener{
            var inputA= edtA.text.toString()
            var inputB= edtB.text.toString()
            try{
                val a = Integer.parseInt(inputA)
                val b = Integer.parseInt(inputB)
                txtKq.setText(giaiPT(a,b))
            }catch (e: NumberFormatException){
                Toast.makeText(this,"Invalid input. Please enter a valid integer.", Toast.LENGTH_SHORT).show()
            }
        }

        btnHuy.setOnClickListener {
            Toast.makeText(this,"Ban kich nut huy", Toast.LENGTH_LONG).show()
            edtA.setText("")
            edtB.setText("")
            txtKq.setText("Kết Quả")
        }

    }
    private fun giaiPT( a:Int,  b:Int): String? {
        var x: Double=0.0
        if(a!=0 && b!=0){
            x= (-b.toDouble())/a.toDouble()
            return "Phương trình có nghiệm X=" +x.toString()
        }
        if (a!=0 && b==0) {
            x=0.0
            return "Phương trình có nghiệm X=" +x.toString()
        }
        if(a==0 && b==0) {
            return "Phương trình có vô số nghiệm thoả mãn"
        }
        return "Phương trình vô nghiệm"
    }
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
