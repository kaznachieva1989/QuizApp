package kg.nurzhamal.quizapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizResponse {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("results")
    @Expose
    private ArrayList<Question> results = null;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<Question> getResults() {
        return results;
    }

    public void setResults(ArrayList<Question> results) {
        this.results = results;
    }
}

