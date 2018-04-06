package skeletonteam.traveguide;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("RECOMMENDED"));
        tabs.addTab(tabs.newTab().setText("NEW"));
        tabs.addTab(tabs.newTab().setText("ALL"));

        //test
        ImageButton img_Click = (ImageButton) findViewById(R.id.user_profile);
        img_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), City.class);
                startActivity(intent);
            }
        });
    }
}
