package com.pascualarnault.contactformexersise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.textfield.TextInputLayout
import com.pascualarnault.contactformexersise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var contact = Contact()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Edit Contact"
        setupListeners()
        phoneValidator()
        zipCodeValidator()
        emailValidator()
        setEditTextFromContact()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_edit -> {
                switchEditableState(true)
                item.setIcon(R.drawable.close)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setContact(){
        contact.firstname    = binding.firstNameTL.editText!!.text.toString()
        contact.lastname    = binding.lastNameTL.editText!!.text.toString()
        contact.company     = binding.companyNameTL.editText!!.text.toString()
        contact.phone       = binding.phoneTL.editText!!.text.toString()
        contact.landline    = binding.landlineTL.editText!!.text.toString()
        contact.mail        = binding.mailTL.editText!!.text.toString()
        contact.address     = binding.addressTL.editText!!.text.toString()
        contact.zipcode     = binding.zipCodeTL.editText!!.text.toString()
        contact.description = binding.descriptionTL.editText!!.text.toString()
    }

    private fun setEditTextFromContact(){
        binding.firstNameTL.editText!!.text = getEditableFromString(contact.firstname)
        binding.lastNameTL.editText!!.text = getEditableFromString(contact.lastname)
        binding.companyNameTL.editText!!.text = getEditableFromString(contact.company)
        binding.phoneTL.editText!!.text = getEditableFromString(contact.phone)
        binding.landlineTL.editText!!.text = getEditableFromString(contact.landline)
        binding.mailTL.editText!!.text = getEditableFromString(contact.mail)
        binding.addressTL.editText!!.text = getEditableFromString(contact.address)
        binding.zipCodeTL.editText!!.text = getEditableFromString(contact.zipcode)
        binding.descriptionTL.editText!!.text = getEditableFromString(contact.description)
    }

    private fun getEditableFromString(string: String) : Editable {
        return Editable.Factory.getInstance().newEditable(string)
    }

    private fun switchEditableState(isEditable: Boolean){
        binding.firstNameTL.isEnabled    = isEditable
        binding.lastNameTL.isEnabled    = isEditable
        binding.companyNameTL.isEnabled = isEditable
        binding.phoneTL.isEnabled       = isEditable
        binding.landlineTL.isEnabled    = isEditable
        binding.mailTL.isEnabled        = isEditable
        binding.addressTL.isEnabled     = isEditable
        binding.zipCodeTL.isEnabled     = isEditable
        binding.descriptionTL.isEnabled = isEditable

        if (isEditable){
            binding.cancelEdit.visibility = View.VISIBLE
            binding.confirmEdit.visibility = View.VISIBLE
        }else{
            binding.cancelEdit.visibility = View.GONE
            binding.confirmEdit.visibility = View.GONE
        }
    }

    private fun setupListeners(){
        binding.cancelEdit.setOnClickListener {
            switchEditableState(false)
        }
        binding.confirmEdit.setOnClickListener {
            setContact()
            switchEditableState(false)
        }
    }

    private fun getPhoneValidatorWatcher(textInputLayout: TextInputLayout) : TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!Patterns.PHONE.matcher(s).matches())
                    textInputLayout.error = "Enter a valid Phone Number"
                else
                    textInputLayout.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun phoneValidator(){
        binding.phoneTL.editText!!.addTextChangedListener (getPhoneValidatorWatcher(binding.phoneTL))
        binding.landlineTL.editText!!.addTextChangedListener (getPhoneValidatorWatcher(binding.landlineTL))
    }

    private fun zipCodeValidator(){
        binding.zipCodeTL.editText!!.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (s.matches(".*\\d+.*".toRegex()))
                    binding.zipCodeTL.error = "Numeric character not allowed"
                else
                    binding.zipCodeTL.error = null
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun emailValidator(){
        binding.mailTL.editText!!.addTextChangedListener ( object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (!Patterns.EMAIL_ADDRESS.matcher(s).matches())
                    binding.mailTL.error = "Enter a valid mail address"
                else
                    binding.mailTL.error = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}