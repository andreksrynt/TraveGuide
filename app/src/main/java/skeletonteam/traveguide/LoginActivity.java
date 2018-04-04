package skeletonteam.traveguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button  btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btnSave = (Button)findViewById(R.id.btnSave);
        String customFont1 = "GlacialIndifference-Regular.otf";
        String customFont2 = "Lora-Bold.ttf";
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), customFont1);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), customFont2);
        TextView textView1 = (TextView) findViewById(R.id.sign_w_google);
        TextView textView2 = (TextView) findViewById(R.id.sign_w_facebook);
        TextView textView3 = (TextView) findViewById(R.id.tittle);
        textView1.setTypeface(typeface1);
        textView2.setTypeface(typeface1);
        textView3.setTypeface(typeface2);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityDua = new Intent(getApplicationContext(), City.class);
                startActivity(activityDua);

            }
        });
    }
}