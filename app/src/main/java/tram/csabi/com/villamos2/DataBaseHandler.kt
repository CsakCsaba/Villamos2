package tram.csabi.com.villamos2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase

class DataBaseHandler : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base_handler)

        var firebaseDatabase =  FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.reference

        databaseRef.setValue("General Kenobi!")

    }
}
