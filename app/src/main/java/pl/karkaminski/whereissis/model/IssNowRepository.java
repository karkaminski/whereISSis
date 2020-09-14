package pl.karkaminski.whereissis.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IssNowRepository {

    private static final String TAG = "Debug: IssNowRepository";

    private static final String BASE_URL = "http://api.open-notify.org/";
    private static Retrofit retrofit;
    private static IssNowAPI issNowAPI;
    private MutableLiveData<IssNowResponseJSON> issNowResponseJSON;

    public IssNowRepository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        issNowAPI = retrofit.create(IssNowAPI.class);

        issNowResponseJSON = new MutableLiveData<>();

        setLocation();
    }


    public MutableLiveData<IssNowResponseJSON> getIssNowResponseJSON(){
        return issNowResponseJSON;
    }


    public void setLocation() {
        Call<IssNowResponseJSON> call = issNowAPI.getIssNow();
        call.enqueue(new Callback<IssNowResponseJSON>() {
            @Override
            public void onResponse(Call<IssNowResponseJSON> call, Response<IssNowResponseJSON> response) {
                Log.i(TAG, "onResponse: " + response.body().getIssPosition().getLatitude());
                issNowResponseJSON.postValue(response.body());

            }

            @Override
            public void onFailure(Call<IssNowResponseJSON> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
        });
    }
}
