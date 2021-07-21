package text.ocr.mostafa.harajchallenge.main.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import text.ocr.mostafa.harajchallenge.model.HarajItem;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<HarajItem>> haraj = new MutableLiveData<>();

    public void fetchHarajItems(InputStream harajStream) throws IOException {
        String harajItems = readJson(harajStream);

        List<HarajItem> harajItems1 = new Gson().fromJson(harajItems, new TypeToken<List<HarajItem>>() {
        }.getType());
        for (HarajItem harajItem: harajItems1)
            harajItem.setSinceDate();

        haraj.postValue(harajItems1);
    }

    public MutableLiveData<List<HarajItem>> getHaraj() {
        return haraj;
    }

    private String readJson(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, bytes.length);

        return new String(bytes);
    }
}
