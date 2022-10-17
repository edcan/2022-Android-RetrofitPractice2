package kr.edcan.magicconch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AnswerActivity : AppCompatActivity() {
    val BASE_URL = "https://yesno.wtf"

    lateinit var txtAnswer: TextView
    lateinit var imganswerImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        txtAnswer = findViewById(R.id.txt_answer_answer)
        imganswerImage = findViewById(R.id.img_answer)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()

        val retrofitService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitService.getAnswer()

            if (response.isSuccessful) { // 서버 통신에 성공 했을때
                val data = response.body()!!

                withContext(Dispatchers.Main) {
                    txtAnswer.text = data.answer
                    Glide.with(this@AnswerActivity)
                        .load(data.imageUrl)
                        .placeholder(R.drawable.magic_conch)
                        .into(imganswerImage)
                }

                Log.d("apiResult", response.body().toString())
            } else { // 서버 통신에 실패 했을 때
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AnswerActivity, "소라고동님이 응답하지 못했습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }
}