package kg.nurzhamal.quizapp.data;

import kg.nurzhamal.quizapp.model.Category;
import kg.nurzhamal.quizapp.model.QuizResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuizApi {
    @GET("api.php")
    Call<QuizResponse> getQuestions(
            @Query("amount") int amount,
            @Query("category") Integer category,
            @Query("difficulty") String difficulty
    );

    @GET("api_category.php")
    Call<Category> getCategory();
}
