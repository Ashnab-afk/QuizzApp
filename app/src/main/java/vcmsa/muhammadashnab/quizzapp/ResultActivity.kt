package vcmsa.muhammadashnab.quizzapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat
        //Code goes here
        //link the elements from the GUI to the backend
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnFinish = findViewById<Button>(R.id.btnFinish)
        //code logic
        //get the score and username from the intent
        val score = intent.getIntExtra("score", 0)
        val username = intent.getStringExtra("username")
        //display the score and username in the textview
        tvScore.text = "$username your score is $score"
        btnFinish.setOnClickListener {
            finish()
        }
    }//end of onCreate
}//end of ResultActivity