package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText,awayText;
    private ImageView homeImage,awayImage;
    private TextView scoreHome,scoreAway;
    int homeResult=0;
    int awayResult=0;
    private Uri imageUri1;
    private Uri imageUri2;
    String homeValue;
    String awayValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        scoreHome = findViewById(R.id.score_home);
        scoreAway = findViewById(R.id.score_away);


        //TODO

        Bundle extras = getIntent().getExtras();
        homeValue = extras.getString("home");
        awayValue = extras.getString("away");
        imageUri1 = Uri.parse(extras.getString("home_image"));
        imageUri2 = Uri.parse(extras.getString("away_image"));
        Bitmap homeImageValue = null;
        Bitmap awayImageValue = null;
        if (extras != null){
            try {
                homeImageValue = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                homeImage.setImageBitmap(homeImageValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayImageValue = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                awayImage.setImageBitmap(awayImageValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeText.setText(homeValue);
            awayText.setText(awayValue);
            homeImage.setImageBitmap(homeImageValue);
            awayImage.setImageBitmap(awayImageValue);
        }



        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void scoreHome(View view){
        homeResult +=1;
        scoreHome.setText(String.valueOf(homeResult));
    }
    public void scoreAway(View view){
        awayResult +=1;
        scoreAway.setText(String.valueOf(awayResult));
    }

    public void resultHandler(View view){
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("scoreHome",homeResult);
        intent.putExtra("scoreAway",awayResult);
        intent.putExtra("homeName",homeValue);
        intent.putExtra("awayName",awayValue);
        startActivity(intent);
    }
}
