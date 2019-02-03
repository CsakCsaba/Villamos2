package tram.csabi.com.villamos2.View

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_data_base_handler.*
import tram.csabi.com.villamos2.Logic.LogicHandler
import tram.csabi.com.villamos2.R

class DataBaseHandler : AppCompatActivity() {


    private lateinit var mAuth : FirebaseAuth
    private var currentUser : FirebaseUser?=null
    var igaz = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base_handler)
        //Toasthoz kell
        val mContext : Context = this




        change.setOnClickListener {
            if(igaz) {
                startActivity(Intent(this, Login::class.java))


            }
            else{
                LogicHandler.loginSuccess = false
                deleteOrDownload.setBackgroundColor(resources.getColor(R.color.red))
                deleteOrDownload.setText("törlés")
                igaz = !igaz
                changeText.text = "váltás letöltésre"
            }
        }

        mAuth = FirebaseAuth.getInstance()

//        mAuth.signInWithEmailAndPassword("chuck23y@gmail.com","password")
//            .addOnCompleteListener {
//                task: Task<AuthResult> ->
//                if(task.isSuccessful){
//                    Toast.makeText(this,"Signed in successfully", Toast.LENGTH_LONG).show()
//                }else{
//                    Toast.makeText(this,"Signed in unsuccessfully", Toast.LENGTH_LONG).show()
//                }
//            }
        //HÁLÓZAT ELLENŐRZÉSE
//        if(LogicHandler.isNetworkConnected(this))
//            Toast.makeText(this,"Van hálózat", Toast.LENGTH_LONG).show()
//        else
//            Toast.makeText(this,"Nincs hálózat", Toast.LENGTH_LONG).show()
    }


    override fun onStart() {
        super.onStart()
        currentUser = mAuth.currentUser
        if(currentUser!=null){
            Toast.makeText(this,"User is logged in", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"User is logged out", Toast.LENGTH_LONG).show()
        }

    }

    override fun onResume() {
        Toast.makeText(this,"Fő ablak folytatva", Toast.LENGTH_LONG).show()
        if(LogicHandler.loginSuccess) {
            deleteOrDownload.setBackgroundColor(resources.getColor(R.color.green))
            deleteOrDownload.setText("letöltés")
            igaz = !igaz
            changeText.text = "váltás törlésere"
        }
        super.onResume()
    }
}

