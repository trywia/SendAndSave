package edu.ib.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String FOLDERNAME = "External";
    public static final String TAG = "EDUIB";
    public static ArrayList<Contact> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnSendClick(View view) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date now = new Date();
        final EditText edtMessage = (EditText) findViewById(R.id.edtMessage);
        final String MSG = edtMessage.getText().toString();
        final String FILENAME = "msg_" + formatter.format(now) + ".txt";

        Log.d(TAG, MSG); // logi, wyszukiwanie po tagu w Logcat

        // ustalenie ścieżki do pliku, File - donosi się do ścieżek do plików (file path)
        File myExternalFile = new File(getExternalFilesDir(FOLDERNAME), FILENAME);

        // try with resources
        try (FileOutputStream os = new FileOutputStream(myExternalFile)) {
            os.write(MSG.getBytes()); // zamieniamy na stumień bajtów
            os.close(); // zamknięcie strumienia
            Toast.makeText(this, "Message ready to send, file saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, MSG);

        list.add(new Contact(formatter.format(now), MSG));

        startActivity(intent);
    }

    public void onBtnArchiveClick(View view) {
        Intent intent = new Intent(this, ArchiveActivity.class);
        intent.putExtra("list", list);
        startActivity(intent);
    }
}