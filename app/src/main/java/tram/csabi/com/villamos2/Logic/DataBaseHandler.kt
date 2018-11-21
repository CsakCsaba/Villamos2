package tram.csabi.com.villamos2.Logic

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import tram.csabi.com.villamos2.R

class DataBaseHandler : AppCompatActivity() {


    private lateinit var mAuth : FirebaseAuth
    private var currentUser : FirebaseUser?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base_handler)
        //Toasthoz kell
        val mContext : Context = this
        //
        mAuth = FirebaseAuth.getInstance()

        mAuth.signInWithEmailAndPassword("chuck23y@gmail.com","password")
            .addOnCompleteListener {
                task: Task<AuthResult> ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Signed in successfully", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Signed in unsuccessfully", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun commitoljGeci(){
        
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
}

