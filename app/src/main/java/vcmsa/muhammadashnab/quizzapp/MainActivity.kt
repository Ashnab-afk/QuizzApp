package vcmsa.muhammadashnab.quizzapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat
        //Code goes here
        //Link the elements from the GUI to the backend
        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val btnStartQuiz = findViewById<Button>(R.id.btnStartQuiz)
        //retrieve the data from the elements
        val username = edtUsername.text.toString()
        btnStartQuiz.setOnClickListener {
            //start the quiz activity
            val intent = Intent(this, QuizActivity::class.java).putExtra("username", username)
            startActivity(intent)
            finish()





        }
    }//end of onCreate
}//end of MainActivity