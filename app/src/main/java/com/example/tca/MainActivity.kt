package com.example.tca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tca.fragments.LoginFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UserRoomController.createDb(this)
        GameRoomController.createDb(this)

        if (UserRoomController.isLog(this)) {
            val intent = Intent(this, GameActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent)
            finish()
        } else {
            val new = LoginFragment()
            fragment(new, false)
        }

    }

     fun fragment(fragment: Fragment, addToBackStack: Boolean = true){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.ConstraintLayout, fragment)
            if(addToBackStack)
                addToBackStack(null)
            commit()
        }
    }
}
