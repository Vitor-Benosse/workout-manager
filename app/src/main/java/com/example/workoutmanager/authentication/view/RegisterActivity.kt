package com.example.workoutmanager.authentication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workoutmanager.R
import com.example.workoutmanager.Util
import com.example.workoutmanager.authentication.viewmodel.AuthenticationViewModel
import com.example.workoutmanager.databinding.ActivityRegisterBinding
import com.example.workoutmanager.home.view.HomeActivity
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(
            AuthenticationViewModel::class.java
        )
    }

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.registerButton.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()
            val name = binding.inputName.text.toString()

            when {
                Util.validateNameEmailAndPassword(name ,email, password) -> {
                    viewModel.register(email, password)
                }
                else -> {
                    Snackbar.make(binding.registerButton, "Register Failed", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        binding.signText.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.stateRegister.observe(this, { state ->
            state?.let {
                navigateToHome(it)
            }
        })

        viewModel.error.observe(this, { error ->
            error?.let {
                showErrorMessage(it)
            }
        })
    }

    private fun navigateToHome(status : Boolean) {
        when {
            status -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    private fun showErrorMessage(message : String) {
        Snackbar.make(binding.registerButton, message, Snackbar.LENGTH_LONG).show()
    }
}