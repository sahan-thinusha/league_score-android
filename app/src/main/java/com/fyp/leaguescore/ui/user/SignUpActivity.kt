package com.fyp.leaguescore.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Patterns
import android.widget.Toast
import com.fyp.leaguescore.R
import com.fyp.leaguescore.app.LeagueScoreApplication
import com.fyp.leaguescore.model.Gamer
import com.fyp.leaguescore.model.User
import com.fyp.leaguescore.network.APIInterface
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class SignUpActivity : AppCompatActivity() {
    @set:Inject
    internal var apiInterface: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        (application as LeagueScoreApplication).getAppComponent()?.inject(this@SignUpActivity)

        btnSignUp.setOnClickListener {
            val email = edtSignUpEmail.text.toString()
            val password = edtSignUpPassword.text.toString()
            val name = edtSignUpName.text.toString()
            val cnfPassword = edtsignUpConfirmPassword.text.toString()
            val riotId = edtsignUpRiotID.text.toString()

            //field validation
            if (isValidate(email, password,name,riotId,cnfPassword)) {
                signup(email, password,name,riotId)
            } else {
                showError(getMessage(email, password,name,riotId,cnfPassword))
            }
        }

    }

    private fun signup(email: String, password: String, name: String, riotId: String) {
        val credentials = "leaguescore:leaguescore@123"
        val basic = "Basic " + Base64.encodeToString(
            credentials.toByteArray(),
            Base64.NO_WRAP
        )
        var user = User(name,password,email,"","")
        var gamer = Gamer(name,riotId,user)

        apiInterface!!.registerGamer(basic,gamer).enqueue(object : Callback<Gamer> {
            override fun onResponse(call: Call<Gamer>, response: Response<Gamer>) {
                if (response.code() == 200) {
                    showSuccess("Success")
                    finish()
                }else{
                    showError("Something went wrong")
                }
            }
            override fun onFailure(call: Call<Gamer>, t: Throwable) {
                showError("Something went wrong")
            }
        })
    }


    /**
     * validate fields
     * @param email - email address from email edit text
     * @param password - password field
     * return - Boolean
     */
    private fun isValidate(email: String, password: String,name: String,contactNo: String,cnfPassword:String): Boolean {

        if (TextUtils.isEmpty(name)) {
            return false
        }

        //check field is empty or not
        if (TextUtils.isEmpty(email)) {
            return false
        }

        //Regex match for email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        if (TextUtils.isEmpty(contactNo)) {
            return false
        }

        //check field is empty or not
        if (TextUtils.isEmpty(password)) {
            return false
        }

        if (!password.equals(cnfPassword)){
            return false
        }
        return true
    }


    /**
     * return String error
     * @param email - email address from email edit text
     * @param password - password field
     */
    private fun getMessage(email: String, password: String,name: String,contactNo: String,cnfPassword:String): String {


        if (TextUtils.isEmpty(name)) {
            return "Name is empty.."
        }

        if (TextUtils.isEmpty(email)) {
            return "Email address is empty.."
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "Invalid Email Address"
        }


        if (TextUtils.isEmpty(contactNo)) {
            return "Contact no is empty.."
        }


        if (TextUtils.isEmpty(password)) {
            return "Password is empty.."
        }



        if (!password.equals(cnfPassword)){
            return "Password and confirm password dose not match.."
        }
        return "Something went wrong...!"
    }

    fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }

    fun showSuccess(txt: String) {
        Toasty.success(this, txt, Toast.LENGTH_LONG, true).show()//show  toast message
    }
}
