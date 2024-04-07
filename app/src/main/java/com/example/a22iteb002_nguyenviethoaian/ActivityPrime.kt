package com.example.a22iteb002_nguyenviethoaian

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

import kotlin.math.sqrt

class ActivityPrime : AppCompatActivity() {
    lateinit var edtNumber:EditText
    lateinit var btnCheck:Button
    lateinit var btnHuy:Button
    lateinit var txtKq:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prime)
        btnCheck = findViewById(R.id.btnCheck)
        btnHuy = findViewById<Button>(R.id.btnHuy)
        edtNumber = findViewById<EditText>(R.id.edtNumber)
        txtKq = findViewById<TextView>(R.id.txtKq)
        //controls()
        btnCheck.setOnClickListener{
            var inputN = edtNumber.text.toString()
            try {
                val number = Integer.parseInt(inputN)
                if(isPrime(number)){
                    txtKq.text = number.toString() + "là số nguyên tố"
                    edtNumber.setText("")
                }
            }catch (e: NumberFormatException){
                Toast.makeText(this,"Loi kieu so",Toast.LENGTH_SHORT).show()
            }



        }

        btnHuy.setOnClickListener {
            Toast.makeText(this,"Ban kich nut huy",Toast.LENGTH_LONG).show()
            edtNumber.setText("")
        }

    }
    private fun isPrime(n :Int) : Boolean{
        if(n<=1) return false
        if (n <=3) return true
        var count:Int=0
        for( i in 1..sqrt(n.toDouble()).toInt() ){
            if(n%i==0){
                count = count + 1
            }
        }
        if(count ==2){
            return true
        }
        return false
    }


    override fun onStart() {
        super.onStart()
        Log.d("msg","Call onStart fun !")
    }

    override fun onResume() {
        super.onResume()
        Log.d("msg","Call onResume fun !")
    }

    override fun onPause() {
        super.onPause()
        Log.d("msg","Call onPause fun !")
    }

    override fun onStop() {
        super.onStop()
        Log.d("msg","Call onStop fun !")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("msg","Call onRestart fun !")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("msg","Call onDestroy fun !")
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


