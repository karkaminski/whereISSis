package pl.karkaminski.whereissis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import pl.karkaminski.whereissis.model.IssNowAPI
import pl.karkaminski.whereissis.model.IssNowResponseJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var locationTextView: TextView
    private lateinit var buttonRefresh: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextView = findViewById(R.id.textView_location)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.open-notify.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var issNow: IssNowAPI = retrofit.create(IssNowAPI::class.java)

        var call: Call<IssNowResponseJSON> = issNow.issNow

        call.enqueue(object : Callback<IssNowResponseJSON> {
            override fun onResponse(
                call: Call<IssNowResponseJSON>,
                response: Response<IssNowResponseJSON>
            ) {
                Log.i(TAG, "onResponse: ")
                if (!response.isSuccessful) {
                    locationTextView.text = response.code().toString()
                    return
                }
                var issNowResponseJSON = response.body()
                locationTextView.text = issNowResponseJSON?.issPosition?.latitude + " " + issNowResponseJSON?.issPosition?.longitude
            }

            override fun onFailure(call: Call<IssNowResponseJSON>, t: Throwable) {
                Log.i(TAG, "onFailure: ")
                locationTextView.text = t.message
            }

        })


    }


}