package ir.sinasoheili.bookstore.VIEW;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_obj();

        ConnectivityManager connect_manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net_info = connect_manager.getActiveNetworkInfo();
        if(net_info == null || !(net_info.isConnected()))
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("اتصال به اینترنت");
            dialog.setMessage("اتصال به اینترنت ممکن نمی باشد !! لطفا سرویس دیتای خود را چک کنید!");
            dialog.setPositiveButton("باشه", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    finish();
                }
            });
            dialog.setCancelable(false);
            dialog.show();
        }

        bnv.setSelectedItemId(R.id.bottom_navigation_view_home_item);
    }

    private void init_obj()
    {
        bnv = findViewById(R.id.main_activity_bottom_navigation_view);
        bnv.setOnNavigationItemSelectedListener(this);

        home_page = new Home_Page_View();
        search_page = new Search_Page_View();
        bag_shop_page = new Bag_Shop_Page_View();
        profile_page = new Profile_Page_View();
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
