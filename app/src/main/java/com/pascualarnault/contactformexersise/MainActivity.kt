package com.pascualarnault.contactformexersise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.pascualarnault.contactformexersise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Edit Contact"
        phoneValidator()
        zipCodeValidator()
        emailValidator()
    }

    private fun getPhoneValidatorWatcher(textInputLayout: com.google.android.material.textfield.TextInputLayout) : TextWatcher {
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