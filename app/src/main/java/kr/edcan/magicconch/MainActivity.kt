package kr.edcan.magicconch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var btnQuestion : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnQuestion = findViewById(R.id.btn_main_question)

        btnQuestion.setOnClickListener {
            val intent = Intent(this, AnswerActivity::class.java)
            startActivity(intent)
        }

    }
}