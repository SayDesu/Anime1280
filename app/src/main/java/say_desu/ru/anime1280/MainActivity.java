package say_desu.ru.anime1280;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPlay;
    Button btnScore;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnPlay = (Button) findViewById(R.id.buttonPlay);
        btnScore = (Button) findViewById(R.id.buttonScore);
        btnExit = (Button) findViewById(R.id.buttonExit);

        btnPlay.setOnClickListener(this);
        btnScore.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPlay:
                Intent intent = new Intent(this,PlayActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonScore:
                //TODO implement High Score button

                break;
            case R.id.buttonExit:
                //TODO implement exit button
                break;
        }
    }
}
