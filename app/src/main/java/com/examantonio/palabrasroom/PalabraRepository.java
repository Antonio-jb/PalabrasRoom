package com.examantonio.palabrasroom;
import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

public class PalabraRepository {
    private PalabraDAO mPalabraDao;
    private LiveData<List<Palabra>> mPalabras;

    PalabraRepository(Application application) {
        PalabraDB db = PalabraDB.getDatabase(application);
        mPalabraDao = db.palabraDao();
        mPalabras = mPalabraDao.getPalabrasOrdenadas();
    }

        LiveData<List<Palabra>> getAllPalabras(){
            return mPalabras;
        }

        void insert(Palabra palabra) {
            PalabraDB.databaseWriteExecutor.execute(() -> {
                mPalabraDao.insert(palabra);
            });
    }

    void delete(Palabra palabra) {
        PalabraDB.databaseWriteExecutor.execute(() -> {
            mPalabraDao.delete(palabra);
        });
    }
}
