package com.example.dbexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.itemList)
        userAdapter = UserListAdapter()
        recyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        findViewById<FloatingActionButton>(R.id.fabBtn).setOnClickListener {
            startActivity(DataActivity.newIntent(this))
        }
    }

    override fun onResume() {
        super.onResume()
        userAdapter.updateItem(getUsersFromDB())
    }

    private fun getUsersFromDB(): List<UserItem> {
        val cursor = (applicationContext as App)
                .dbHelper
                .readableDatabase
                .query("users", null, null, null, null, null, null)
        if (cursor != null) {
            val idIndex = cursor.getColumnIndex("id")
            val nameIndex = cursor.getColumnIndex("name")
            val list = mutableListOf<UserItem>()
            while (cursor.moveToNext()) {
                list.add(UserItem(cursor.getInt(idIndex), cursor.getString(nameIndex)))
            }
            cursor.close()
            return list
        }
        return emptyList()
    }


    class UserListAdapter() : RecyclerView.Adapter<UserListAdapter.ItemViewHolder>() {
        private val usersList = mutableListOf<UserItem>()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(usersList[position])
        }

        override fun getItemCount(): Int {
            return usersList.size
        }

        fun updateItem(list: List<UserItem>) {
            usersList.apply {
                clear()
                addAll(list)
            }
            notifyDataSetChanged()
        }

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textView = itemView.findViewById<TextView>(R.id.userItem)

            fun bind(userItem: UserItem) {
                textView.text = userItem.name
            }
        }
    }
}