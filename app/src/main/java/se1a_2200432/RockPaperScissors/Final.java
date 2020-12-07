package se1a_2200432.RockPaperScissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Final extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private static int user_pictID[] =  {R.drawable.gu, R.drawable.choki, R.drawable.pa};
    private static int com_pictID[] = {R.drawable.com_gu, R.drawable.com_choki, R.drawable.com_pa,};

    final int WIN = 0, LOSE = 1, DROW = 2;
    String[] textResult = {"あなたの勝ちだわ！", "あなたの負けよ！", "あいこだね！"};

    int[][] result = {{DROW,WIN,LOSE},
                      {LOSE,DROW,WIN},
                      {WIN,LOSE,DROW}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ImageView img1 = findViewById(R.id.imageView1);
        ImageView img2 = findViewById(R.id.imageView2);
        TextView text = findViewById(R.id.textView);
        Button btn = findViewById(R.id.button);

        Intent intent = getIntent();

        int user_jkp = -1;
        if (intent != null) {
            if (intent.hasExtra("user_jkp")) {
                user_jkp = intent.getIntExtra("user_jkp", 0); //ｲﾝﾃﾝﾄからﾃﾞｰﾀを取得する(0 はﾃﾞﾌｫﾙﾄ値)
            }
        }

        Random rd = new Random();
        int comp_jkp = rd.nextInt(3);
        //game_count
        final int gameCount = mPrefs.getInt("GAME_COUNT", 0);
        // Editorクラスのインスタンスを取得する
        final SharedPreferences.Editor editor = mPrefs.edit();
        //result_save
        int result_save = mPrefs.getInt("result_save", 0);
        SharedPreferences.Editor editor2 = mPrefs.edit();
        //comp_output_save
        int comp_output_save = mPrefs.getInt("comp_output_save", 0);
        final SharedPreferences.Editor editor3 = mPrefs.edit();
        //player_save
        int player_save = mPrefs.getInt("player_save", 0);
        SharedPreferences.Editor editor4 = mPrefs.edit();
        //win_count
        final int[] win_count = {mPrefs.getInt("win_count", 0)};
        final SharedPreferences.Editor editor5 = mPrefs.edit();
        //lose_count
        final int[] lose_count = {mPrefs.getInt("lose_count", 0)};
        final SharedPreferences.Editor editor6 = mPrefs.edit();
        //drow_count
        final int[] drow_count = {mPrefs.getInt("drow_count", 0)};
        final SharedPreferences.Editor editor7 = mPrefs.edit();



        if(result_save == LOSE && gameCount != 0){
            while(comp_jkp == comp_output_save){
                comp_jkp = rd.nextInt(3);
            }
        }else if(result_save == WIN){
            comp_jkp = player_save;
        }else if(player_save == user_jkp && result_save == WIN){
            while(comp_jkp == comp_output_save){
                comp_jkp = rd.nextInt(3);
            }
        }

        text.setText(String.valueOf(textResult[result[user_jkp][comp_jkp]]));
        final int end = result[user_jkp][comp_jkp];
        img1.setImageResource(user_pictID[(user_jkp)]);
        img2.setImageResource(com_pictID[(comp_jkp)]);


        editor2.putInt("result_save", result_save = result[user_jkp][comp_jkp] );
        editor2.commit();
        editor3.putInt("comp_output_save", comp_output_save = comp_jkp );
        editor3.commit();
        editor4.putInt("player_save", player_save = user_jkp );
        editor4.commit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // ここにクリック時の処理を書く
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                // キー名"content"に、入力された文字列を設定する例
                editor.putInt("GAME_COUNT", gameCount + 1 );
                // 設定を反映する
                editor.commit(); // もしくはeditor.apply();
                if(end == WIN) {
                    editor5.putInt("lose_count", lose_count[0] + 1);
                    editor5.commit();
                }else if(end == LOSE) {
                    editor6.putInt("win_count", win_count[0] + 1);
                    editor6.commit();
                }else if(end == DROW) {
                    editor7.putInt("drow_count", drow_count[0] + 1);
                    editor7.commit();
                }
                Log.d("保存値を確認","GAME_COUNT = " + mPrefs.getInt("GAME_COUNT",0));
                Log.d("保存値を確認","コンピュータが: " + mPrefs.getInt("win_count",0) + "勝, " + "相手が: "+ mPrefs.getInt("lose_count",0) + "勝, " + "あいこ: " + mPrefs.getInt("drow_count",0) + "回でした。");
//                Log.d("コンピュータが", mPrefs.getInt("win_count",0) + "勝");
//                Log.d("相手が", mPrefs.getInt("lose_count",0) + "勝");
//                Log.d("あいこ", mPrefs.getInt("drow_count",0) + "回でした。");
                finish();
            }
        });
    }
}