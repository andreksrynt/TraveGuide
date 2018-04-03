package skeletonteam.traveguide;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("RECOMMENDED"));
        tabs.addTab(tabs.newTab().setText("NEW"));
        tabs.addTab(tabs.newTab().setText("ALL"));
    }
}
