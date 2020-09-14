package pl.karkaminski.whereissis;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import pl.karkaminski.whereissis.model.IssNowRepository;
import pl.karkaminski.whereissis.model.IssNowResponseJSON;

public class IssNowViewModel extends AndroidViewModel {

    private static final String TAG = "Debug: IssNowViewModel";

    private MutableLiveData<IssNowResponseJSON> issNowResponseJSON;
    private IssNowRepository issNowRepository;

    public IssNowViewModel(@NonNull Application application) {
        super(application);
        issNowRepository = new IssNowRepository();
        issNowResponseJSON = issNowRepository.getIssNowResponseJSON();
        Log.i(TAG, "IssNowViewModel: ");
    }

    public MutableLiveData<IssNowResponseJSON> getIssNowResponseJSON() {
        return issNowResponseJSON;
    }

    public void checkLocation(){
        issNowRepository.setLocation();
    }
}
