package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.Book_Bought_Contract;
import ir.sinasoheili.bookstore.PRESENTER.Book_Bought_Presenter;
import ir.sinasoheili.bookstore.PRESENTER.User_Api;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Book_Bought_List_Activity extends AppCompatActivity implements Book_Bought_Contract.Book_Bought_Contract_view
{
    private int user_id;
    private ListView lv;
    private TextView tv_empty_list;
    private ProgressBar progressbar;
    private Book_Bought_Contract.Book_Bought_Contract_presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__bought__list_);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            user_id = bundle.getInt("user_id");
        }

        init_obj();

        if(user_id == -1)
        {
            Toast t = Toast.makeText(this , "لطفا وارد حساب کاربری خود شوید !!" , Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER , 0 , 0 );
            t.show();
            return;
        }

        presenter.get_bought_book_list(user_id);
    }

    private void init_obj()
    {
        lv = findViewById(R.id.lv_book_bought_page);

        tv_empty_list = findViewById(R.id.tv_empty_list_book_bought_page);

        progressbar = findViewById(R.id.book_bought_progress_bar);

        presenter = new Book_Bought_Presenter(getApplicationContext() , this);
    }

    @Override
    public void show_book_list(ArrayList<Book> list)
    {
        if(list.isEmpty() == true)
        {
            tv_empty_list.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_empty_list.setVisibility(View.GONE);
        }

        ListView_Adapter_SearchPage adapter = new ListView_Adapter_SearchPage(this , list);
        lv.setAdapter(adapter);
    }

    @Override
    public void show_progress_bar()
    {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide_progress_bar()
    {
        progressbar.setVisibility(View.GONE);
    }
}
