package ir.sinasoheili.bookstore.VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.LruCache;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import ir.sinasoheili.bookstore.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    private BottomNavigationView bnv;

    private Home_Page_View home_page;
    private Search_Page_View search_page;
    private Bag_Shop_Page_View bag_shop_page;
    private Profile_Page_View profile_page;

    private SharedPreferences pref;

    public static LruCache<String , Bitmap> cache;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_obj();

        if(pref.getBoolean(IntroSlider.INTROSLIDER_PREF_KEY , true) == true)
        {
            startActivity(new Intent(MainActivity.this , IntroSlider.class));
        }

        check_connection();

        bnv.setSelectedItemId(R.id.bottom_navigation_view_home_item);
    }

    private void init_obj()
    {
        pref = this.getSharedPreferences(IntroSlider.INTROSLIDER_PREF_NAME , MODE_PRIVATE);

        bnv = findViewById(R.id.main_activity_bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this);

        home_page = new Home_Page_View();
        search_page = new Search_Page_View();
        bag_shop_page = new Bag_Shop_Page_View();
        profile_page = new Profile_Page_View();

        cache = new LruCache<>((int)(Runtime.getRuntime().freeMemory()/6));
    }

    public void check_connection()
    {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if((cm.getActiveNetworkInfo() == null) || (cm.getActiveNetworkInfo().isConnected() == false))
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                                            .setTitle("مشکل در اتصال به اینترنت")
                                            .setMessage("اتصال به اینترنت ممکن نمی باشد !! لطفا سرویس دیتای خود را چک کنید")
                                            .setCancelable(false)
                                            .setNeutralButton("باشه", new DialogInterface.OnClickListener()
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which)
                                                {
                                                    finish();
                                                }
                                            });
            dialog.show();
        }
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
        else if(menuItem.getItemId() == R.id.bottom_navigation_view_bag_shop_item)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_card_view_continer , bag_shop_page).commit();
        }
        else if(menuItem.getItemId() == R.id.bottom_navigation_view_profile_item)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_card_view_continer , profile_page).commit();
        }

        return true;
    }
}
