package kg.nurzhamal.quizapp.db.converter;

import android.provider.ContactsContract;

import androidx.annotation.Nullable;
import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public static Long toTimeStamp(@Nullable Date date){
        if(date == null){
            return null;
        }
        return date.getTime();
    }

    @TypeConverter
    public static Date fromTimeStamp (@Nullable Long timeStamp){
        if(timeStamp == null)
            return null;
        return new Date(timeStamp);
    }


}
