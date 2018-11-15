package tram.csabi.com.villamos2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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


        // example
        var employee = Employee("James", "general", "Bp 1234", 35)
        var employee1 = Employee("Rico", "private", "BA 5", 20)
        var employee2 = Employee("Rex", "leutenant", "Corrusant", 12)


        //írunk
        /*var firebaseDatabase =  FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages").push()
        databaseRef.setValue(employee)*/



        //Olvasunk
        /*databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
               /*var value = dataSnapshot!!.value as HashMap<String, Any>
                val toast = Toast.makeText( mContext , "${value.get("name").toString()}", Toast.LENGTH_LONG)
                toast.show()*/
            }
            override fun onCancelled(databaseError: DatabaseError) {
                val toast = Toast.makeText( mContext , "Adatbázis hiba!", Toast.LENGTH_LONG)
                toast.show()
            }
        })*/

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
data class Employee(var name: String, var position: String, var Addr: String, var age : Int){

}
