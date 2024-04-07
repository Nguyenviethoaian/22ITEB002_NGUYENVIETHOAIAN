package com.example.a22iteb002_nguyenviethoaian

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a22iteb002_nguyenviethoaian.DBHelper.Companion.NUMBER_COL
import com.example.a22iteb002_nguyenviethoaian.DBHelper.Companion.SEMESTER_COL
import com.example.a22iteb002_nguyenviethoaian.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: DBHelper
    private lateinit var hocPhanAdapter: HocPhanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this, null)
        val courses = dbHelper.readCourses()
        val adapter = HocPhanAdapter(this)
        adapter.setCourses(courses)

        binding.btnAdd.setOnClickListener {
            val name = binding.edtNameCourse.text.toString()
            val number = binding.edtNumber.text.toString()
            val semester = binding.edtSemester.text.toString()
            dbHelper.addName(name, number, semester)
            Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()
            binding.edtNameCourse.text.clear()
            binding.edtNumber.text.clear()
            binding.edtSemester.text.clear()
            updateRecyclerView()
        }

        binding.recyclerview.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                binding.recyclerview,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {


                        binding.btnDele.setOnClickListener {
                            if (position >= 0) {
                                dbHelper.deleData(position)
                                Toast.makeText(this@MainActivity, "Đã xoá thành công", Toast.LENGTH_SHORT).show()
                                updateRecyclerView()
                            } else {
                                Toast.makeText(this@MainActivity, "Không có dữ liệu nào để xóa", Toast.LENGTH_SHORT).show()
                            }
                        }

                        binding.btnUpdate.setOnClickListener {
                            val newName = binding.edtNameCourse.text.toString()
                            val newNumber = binding.edtNumber.text.toString()
                            val newSemester = binding.edtSemester.text.toString()
                            val rowsAffected = dbHelper.updateData(position, newName, newNumber, newSemester)

                            if (rowsAffected > 0) {
                                Toast.makeText(this@MainActivity, "Dữ liệu đã được cập nhật thành công", Toast.LENGTH_SHORT).show()
                                updateRecyclerView()
                            } else {
                                Toast.makeText(this@MainActivity, "Không thể cập nhật dữ liệu", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // Handle long item click here
                        // 'position' is the clicked item position
                    }
                })
        )

        binding.btnSearch.setOnClickListener {
            val search = binding.edtSearch.text.toString()
            val cursor = dbHelper.search(search)
            // Handle Cursor here
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
            R.id.menuItem3_1 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3_2 -> {
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
            R.id.menuItem3_1 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuItem3_2 -> {
                val intent = Intent(this, ActivityPrime::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun updateRecyclerView() {
        val lstCourse = dbHelper.readCourses()
        hocPhanAdapter.setCourses(lstCourse)
    }
}
