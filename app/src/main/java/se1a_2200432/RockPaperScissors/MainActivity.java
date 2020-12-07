package se1a_2200432.RockPaperScissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = mPrefs.edit().clear();
        editor.commit();
        ImageView img1 = findViewById(R.id.imageView1);
        ImageView img2 = findViewById(R.id.imageView2);
        ImageView img3 = findViewById(R.id.imageView3);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // ここにクリック時の処理を書く
                int idx = 1;
                Intent intent = new Intent(getApplicationContext(), Final.class);
                intent.putExtra("user_jkp", idx);
                startActivity(intent);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // ここにクリック時の処理を書く
                int idx = 0;
                Intent intent = new Intent(getApplicationContext(), Final.class);
                intent.putExtra("user_jkp", idx);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // ここにクリック時の処理を書く
                int idx = 2;
                Intent intent = new Intent(getApplicationContext(), Final.class);
                intent.putExtra("user_jkp", idx);
                startActivity(intent);
            }
        });
    }
}