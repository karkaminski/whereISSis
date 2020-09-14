package pl.karkaminski.whereissis;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import pl.karkaminski.whereissis.model.IssNowResponseJSON;

public class IssNowViewModel extends AndroidViewModel {

    private LiveData<IssNowResponseJSON> issNowResponseJSONLiveData;

    public IssNowViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<IssNowResponseJSON> getIssNowResponseJSON(){
        return issNowResponseJSONLiveData;
    }
}
