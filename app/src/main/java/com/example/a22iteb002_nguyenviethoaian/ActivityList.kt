package com.example.a22iteb002_nguyenviethoaian

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb002_nguyenviethoaian.databinding.ActivityListBinding
import com.example.a22iteb002_nguyenviethoaian.databinding.ActivityMainBinding


class ActivityList : AppCompatActivity() {
    lateinit var binding1: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        val dbHelper = DBHelper(this, null)
        val courses = dbHelper.readCourses()
        val adapter = HocPhanAdapter(this)
        adapter.setCourses(courses)


        binding1.btnSearch.setOnClickListener {
            val db = DBHelper(this, null)
            val search = binding1.edtSearch.text.toString()
            val cursor = db.search(search)
            // Handle Cursor here
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
            R.id.menuItem3_1->
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3_2->{
                val intent = Intent(this, ActivityList::class.java)
                startActivity(intent)
                return true
            }
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
            R.id.menuItem3_1->
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3_2->{
                val intent = Intent(this, ActivityPrime::class.java)
                startActivity(intent)
                return true
            }
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
