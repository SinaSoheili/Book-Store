package ir.sinasoheili.bookstore.VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ir.sinasoheili.bookstore.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private BottomNavigationView bnv;

    private Home_Page home_page;
    private Search_Page search_page;
    private Profile_Page profile_page;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_obj();
    }

    private void init_obj()
    {
        bnv = findViewById(R.id.main_activity_bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this);

        home_page = new Home_Page();
        search_page = new Search_Page();
        profile_page = new Profile_Page();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        if(menuItem.getItemId() == R.id.bottom_navigation_view_home_item)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_card_view_continer , home_page).commit();
        }
        else if(menuItem.getItemId() == R.id.bottom_navigation_view_search_item)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_card_view_continer , search_page).commit();
        }
        else if(menuItem.getItemId() == R.id.bottom_navigation_view_profile_item)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_card_view_continer , profile_page).commit();
        }

        return true;
    }
}