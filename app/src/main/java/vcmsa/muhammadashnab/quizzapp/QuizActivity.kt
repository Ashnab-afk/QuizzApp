package vcmsa.muhammadashnab.quizzapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//end of ViewCompat
        //Code goes here
        //link the elements from the GUI to the backend
        val tvQuestion = findViewById<TextView>(R.id.tvQuizQuestion)
        val rbtngAnswers = findViewById<RadioGroup>(R.id.rbtngQuizAnswers)
        val btnNext = findViewById<Button>(R.id.button)
        val username = intent.getStringExtra("username")
        //Arrays of Questions and Answers
        val scienceQuestions = arrayOf(
            "Who is the goat of soccer",
            "How many ballondors does messi have?",
            "Which team does messi play for?",
            "How many freekick goals does messi have?",
            "How many champions league does messi have?"
        )
        val answerChoices = arrayOf(
            arrayOf("A: Lionel Messi", "B: Cristiano Ronaldo", "C: Neymar"),
            arrayOf("A: 5", "B: 6", "C: 8"),
            arrayOf("A: ManCity", "B: Inter Miami", "C: Barcelona"),
            arrayOf("A: 50", "B: 60", "C: 66"),
            arrayOf("A: 2", "B: 0", "C: 4")
        )
        var userAnswers = arrayOfNulls<String>(5)
        val correctAnswers = arrayOf(
            "A: Lionel Messi",
            "B: 8",
            "B: Inter Miami",
            "B: 66",
            "A: 4"
        )
        var counter = 0
        //code logic
        // Set the first question before waiting for user interaction
        tvQuestion.text = scienceQuestions[counter]
        for (i in 0 until rbtngAnswers.childCount) {
            val radioButton = rbtngAnswers.getChildAt(i) as RadioButton
            radioButton.text = answerChoices[counter][i]
        }
        btnNext.setOnClickListener {
            if (counter < 5) {
                var selectedOption = rbtngAnswers.checkedRadioButtonId

                if (selectedOption != -1) {
                    val selectedRbtn = findViewById<RadioButton>(selectedOption)
                    userAnswers[counter] = selectedRbtn.text.toString()
                    counter++
                } else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener // Stops execution if no answer is selected
                }

                if (counter < 5) {
                    tvQuestion.text = scienceQuestions[counter]
                    for (i in 0 until rbtngAnswers.childCount) {
                        val radioButton = rbtngAnswers.getChildAt(i) as RadioButton
                        radioButton.text = answerChoices[counter][i]
                    }
                    rbtngAnswers.clearCheck()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    var score = 0

                    // Calculate score
                    for (i in userAnswers.indices) {
                        if (userAnswers[i] == correctAnswers[i]) {
                            score++
                        }
                    }

                    intent.putExtra("score", score)
                    intent.putExtra("username", username)

                    startActivity(intent)
                    finish()
                }
            }
        }
    }//end of onCreate
}//end of QuizActivity

private fun RadioGroup.getChildAt(i: Int): RadioButton {
    TODO("Not yet implemented")
}
