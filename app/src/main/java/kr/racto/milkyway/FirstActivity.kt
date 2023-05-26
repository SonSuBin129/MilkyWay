package kr.racto.milkyway

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kr.racto.milkyway.databinding.ActivityFirstBinding
import kr.racto.milkyway.login.App
import kr.racto.milkyway.login.JoinActivity
import kr.racto.milkyway.login.LoginActivity


class FirstActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        check()
    }


    fun init(){
        binding.nonmemberbtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        binding.loginbtn.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
        binding.joinbtn.setOnClickListener {
            val i = Intent(this, JoinActivity::class.java)
            startActivity(i)
        }
    }

    fun check(){
        val autoCheck=application as App
        var check: Boolean
        synchronized(autoCheck){
            check=autoCheck.getSharedValue()
        }
        if(check){
            Auto()
        }
    }

    fun Auto(){
        val mAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.getCurrentUser()

        if(user!=null){
            user.getIdToken(true).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken = task.result.token
                    Log.d(TAG, "아이디 토큰 = $idToken")
                    val homeMove_intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(homeMove_intent)
                }
            }
        }else{
            Toast.makeText(this,"앱을 최초 실행한다",Toast.LENGTH_SHORT).show()
        }
    }
}