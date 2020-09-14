package pl.karkaminski.whereissis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karkaminski.whereissis.model.IssNowAPI
import pl.karkaminski.whereissis.model.IssNowResponseJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Debug: MainActivity"
    }

    private lateinit var locationTextView: TextView
    private lateinit var refreshButton : Button
    private lateinit var issNowViewModel: IssNowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "onCreate: ")

        locationTextView = findViewById(R.id.textView_location)
        refreshButton = findViewById(R.id.checkButton);

        issNowViewModel = ViewModelProviders.of(this).get(IssNowViewModel::class.java)

        issNowViewModel.issNowResponseJSON.observe(this, object : Observer<IssNowResponseJSON> {
            override fun onChanged(t: IssNowResponseJSON?) {
                Log.i(TAG, "onChanged:" )
                locationTextView.text = t?.issPosition?.latitude + " " + t?.issPosition?.longitude
            }

        })

        refreshButton.setOnClickListener {
            issNowViewModel.checkLocation()
        }

    }


}