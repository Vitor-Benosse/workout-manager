package com.example.workoutmanager.authentication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workoutmanager.R
import com.example.workoutmanager.Util
import com.example.workoutmanager.authentication.viewmodel.AuthenticationViewModel
import com.example.workoutmanager.databinding.ActivityLoginBinding
import com.example.workoutmanager.home.view.HomeActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private val viewModel: AuthenticationViewModel by lazy {
        ViewModelProvider(this).get(
                AuthenticationViewModel::class.java
        )
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginButton.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            when {
                Util.validateEmailAndPassword(email, password) -> {
                    viewModel.login(email, password)
                }
                else -> {
                    Snackbar.make(binding.loginButton, "Login Failed", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        binding.registerButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.stateLogin.observe(this, { state ->
            state?.let {
                navigateToHome(it)
            }
        })

        viewModel.error.observe( this, { error ->
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
        Snackbar.make(binding.loginButton, message, Snackbar.LENGTH_LONG).show()
    }
}