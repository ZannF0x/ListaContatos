package com.zannardyapps.listacontatos

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zannardyapps.listacontatos.databinding.DrawerMenuOptionsBinding
import com.zannardyapps.listacontatos.models.ContactAdapter
import com.zannardyapps.listacontatos.models.DataContact
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zannardyapps.listacontatos.ContactDetail.Companion.EXTRA_CONTACT

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    private lateinit var binding: DrawerMenuOptionsBinding

    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DrawerMenuOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDrawerLayout()
        fetchListContact()
        bindViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.itemMenuOne -> {
                toastMessage("Item menu 01 clicado!")
                true
            }
            R.id.itemMenuTwo -> {
                toastMessage("Item menu 02 clicado!")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
     */
    private fun initDrawerLayout(){
        val drawerLayout = binding.drawerLayout

        val toolbarLayout = binding.includeMainActivity.toolbarApp
        setSupportActionBar(toolbarLayout)

        val toggle = ActionBarDrawerToggle(this, drawerLayout,
            toolbarLayout,R.string.open_drawer, R.string.close_drawer)

        toggle.syncState()
        drawerLayout.addDrawerListener(toggle)
    }

    private fun fetchListContact(){
        val listContacts = arrayListOf<DataContact>(
            DataContact(
                "Zannardy A. Oliveira",
                "+00 00 0000-0000",
                "img.png"
            ),
            DataContact(
                "Lays Samara",
                "+11 11 1111-1111",
                "img.png"
            ),
            DataContact(
                "Rafael Jackson",
                "+22 22 2222-2222",
                "img.png"
            ),
            DataContact(
                "Moisés dos Santos",
                "+33 33 3333-3333",
                "img.png"
            ),
        )

        getInstanceSharedPreferences()?.edit {
            val jsonListContacts =  Gson().toJson(listContacts)
            putString("contacts", jsonListContacts)
            commit()
        }
    }

    private fun getInstanceSharedPreferences(): SharedPreferences? {
        return getSharedPreferences("com.zannardyapps.listacontatos.PREFERENCES", Context.MODE_PRIVATE)
    }


    private fun toastMessage(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun bindViews(){
        val recyclerViewLayout = binding.includeMainActivity.recyclerViewLayout
        recyclerViewLayout.adapter = adapter
        recyclerViewLayout.layoutManager = LinearLayoutManager(this)

        updateList()
    }

    private fun getListContact(): List<DataContact>{
        val list = getInstanceSharedPreferences()?.getString("contacts", "[]")
        val turnsType = object: TypeToken<List<DataContact>>() {}.type
        return Gson().fromJson(list, turnsType)
    }

    private fun updateList(){
        val list = getListContact()
        adapter.updateList(list)
    }


    override fun itemClickedAction(contact: DataContact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }

}