
package pl.karkaminski.whereissis.model;

import com.google.gson.annotations.SerializedName;

public class IssNowResponseJSON {

    @SerializedName("iss_position")
    private IssPosition issPosition;

    private String message;

    private Long timestamp;

    public IssPosition getIssPosition() {
        return issPosition;
    }

    public String getMessage() {
        return message;
    }

    public Long getTimestamp() {
        return timestamp;
    }


}
