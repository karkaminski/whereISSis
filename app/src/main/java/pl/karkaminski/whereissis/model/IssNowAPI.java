package pl.karkaminski.whereissis.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IssNowAPI {

    @GET("iss-now.json")
    Call<IssNowResponseJSON> getIssNow();
}
