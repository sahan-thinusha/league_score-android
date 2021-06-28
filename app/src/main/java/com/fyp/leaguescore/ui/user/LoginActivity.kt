package com.fyp.leaguescore.ui.user;

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fyp.leaguescore.R
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.LoginRequest
import com.fyp.leaguescore.model.User
import com.fyp.leaguescore.network.APIInterface
import com.fyp.leaguescore.ui.dashboard.DashboardActivity
import com.fyp.leaguescore.ui.user.SignUpActivity
import com.fyp.leaguescore.util.SharedPreferencesHandler
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @set:Inject
    internal var apiInterface: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (application as LeagueScoreApplication).getAppComponent()?.inject(this@LoginActivity)

        btnLogin.setOnClickListener {
            val email = edtLoginEmail.text.toString()
            val password = edtLoginPassword.text.toString()
            //field validation
            if (isValidate(email, password)) {

                login(email, password)
            } else {
                showError(getMessage(email, password))
            }
        }

        btnLoginRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)//start new activity
        }
    }


    /**
     * validate fields
     * @param email - email address from email edit text
     * @param password - password field
     * return - Boolean
     */
    private fun isValidate(email: String, password: String): Boolean {

        //check field is empty or not
        if (TextUtils.isEmpty(email)) {
            return false
        }

        if (!email.equals("admin")) {
            //Regex match for email address
            if (!EMAIL_ADDRESS.matcher(email).matches()) {
                return false
            }
        }
        //check field is empty or not
        if (TextUtils.isEmpty(password)) {
            return false
        }

        return true
    }


    /**
     * return String error
     * @param email - email address from email edit text
     * @param password - password field
     */
    private fun getMessage(email: String, password: String): String {

        if (TextUtils.isEmpty(email)) {
            return "Email address is empty.."
        }

        if (!email.equals("admin")) {

            if (!EMAIL_ADDRESS.matcher(email).matches()) {
                return "Invalid Email Address"
            }
        }

        if (TextUtils.isEmpty(password)) {
            return "Password is empty.."
        }

        return "Something went wrong...!"
    }

    fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }

    private fun login(email: String, password: String) {
        val credentials = "league_score:league_score@123"
        val basic = "Basic " + Base64.encodeToString(
            credentials.toByteArray(),
            Base64.NO_WRAP
        )
        var request = LoginRequest(email, password)

        apiInterface!!.login(basic, request).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200) {
                    val token: String? = response.body()?.token
                    if (token != null) {
                        Log.e("t",token)
                    }
                    SharedPreferencesHandler().saveIntoSharedPreferences(applicationContext, token)
                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                    startActivity(intent)//start new activity
                    finish()
                } else {
                    showError("Something went wrong")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                showError("Something went wrong")
            }
        })


    }
}

