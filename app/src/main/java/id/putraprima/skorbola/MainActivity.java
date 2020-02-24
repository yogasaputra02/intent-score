package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    private ImageView home_logo_image;
    private ImageView away_logo_image;
    private EditText home;
    private EditText away;
    private Uri imageUri1;
    private Uri imageUri2;
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_logo_image = findViewById(R.id.home_logo);
        away_logo_image = findViewById(R.id.away_logo);
        home = findViewById(R.id.home_team);
        away = findViewById(R.id.away_team);


        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

        public static final String HOME_KEY = "home";
        public static final String AWAY_KEY = "away";
        public static final String AWAY_IMAGE = "away_image";
        public static final String HOME_IMAGE = "home_image";

    //3. Ganti Logo Home Team

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == 1) {
            if (data != null) {
                try {
                     imageUri1 = data.getData();
                     bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                    home_logo_image.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }else if (requestCode == 2) {
            if (data != null) {
                try {
                    imageUri2 = data.getData();
                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                    away_logo_image.setImageBitmap(bitmap2);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void clickButton_next(View view){
        String Home = home.getText().toString();
        String Away = away.getText().toString();

        if (!(Home).equals("") && !(Away).equals("")){
            if(bitmap1 != null && bitmap2 != null){
                Intent intent = new Intent(this,MatchActivity.class);

                intent.putExtra(HOME_KEY,Home);
                intent.putExtra(AWAY_KEY,Away);
                intent.putExtra(AWAY_IMAGE,imageUri1.toString());
                intent.putExtra(HOME_IMAGE,imageUri2.toString());
                startActivity(intent);
            }else {
                Toast.makeText(this, "Lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickhandleChangeImage(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void clickhandleChangeImage2(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }


}

