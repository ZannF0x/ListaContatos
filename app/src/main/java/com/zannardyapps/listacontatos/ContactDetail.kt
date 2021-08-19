package com.zannardyapps.listacontatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zannardyapps.listacontatos.databinding.ActivityContactDetailBinding
import com.zannardyapps.listacontatos.models.DataContact

class ContactDetail : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    private var contact: DataContact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        getExtraContact()
        bindViewsContact()
    }

    private fun initToolbar(){
        val toolbarLayout = binding.toolbarApp
        setSupportActionBar(toolbarLayout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getExtraContact(){
        contact = intent.getParcelableExtra(EXTRA_CONTACT)
    }

    private fun bindViewsContact(){
        binding.detailContactName.text = contact?.contactName.toString()
        binding.detailContactNumber.text = contact?.contactNumber.toString()

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRA_CONTACT: String = "EXTRA_CONTACT"
    }
}