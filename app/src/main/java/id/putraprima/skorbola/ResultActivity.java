package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView result,winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.textView2);
        winner = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        int homeResult = extras.getInt("scoreHome");
        int awayResult = extras.getInt("scoreAway");
        String homeName = extras.getString("homeName");
        String awayName = extras.getString("awayName");

        if(extras != null){
            result.setText(String.valueOf(homeResult)+" - "+String.valueOf(awayResult));
            if (homeResult > awayResult){
                winner.setText(homeName+" Memenangkan Pertandingan");
            }else if(homeResult < awayResult){
                winner.setText(awayName+" Memenangkan Pertandingan");
            }else{
                winner.setText("Pertandingan Imbang");
            }


        }
    }




}
