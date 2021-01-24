package kg.nurzhamal.quizapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kg.nurzhamal.quizapp.model.QuizResult;

@Dao
public interface QuizDao {

    @Insert
    long insert(QuizResult quizResult);

    @Query("SELECT * FROM qresult WHERE id=:id")
    QuizResult getById(int id);

    @Delete
    void delete(QuizResult quizResult);

    @Query("SELECT * FROM qresult")
    List<QuizResult> getAll();

    @Query("DELETE FROM qresult")
    void deleteAll();

    @Query("DELETE FROM qresult WHERE id=:id")
    void deleteById(int id);


}
