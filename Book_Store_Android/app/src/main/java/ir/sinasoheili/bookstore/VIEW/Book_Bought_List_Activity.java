package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.User_Api;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Book_Bought_List_Activity extends AppCompatActivity
{

    private ArrayList<Book> book_list;
    private int user_id;
    private ListView lv;
    private TextView tv_empty_list;

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
        init_book_list();
    }

    private void init_obj()
    {
        lv = findViewById(R.id.lv_book_bought_page);
        book_list = new ArrayList<>();

        tv_empty_list = findViewById(R.id.tv_empty_list_book_bought_page);
    }

    private void init_book_list()
    {
        if(user_id == -1)
        {
            Toast t = Toast.makeText(this , "لطفا وارد حساب کاربری خود شوید !!" , Toast.LENGTH_SHORT);
            t.setGravity(Gravity.CENTER , 0 , 0 );
            t.show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(User_Api.base_url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        User_Api api = retrofit.create(User_Api.class);
        Call<ArrayList<Book>> call = api.get_book_of_user_bought(user_id);
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                book_list = response.body();
                init_list_view();
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                //todo : what to do if cannot connect to server
                Toast.makeText(Book_Bought_List_Activity.this, "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init_list_view()
    {
        if(book_list.isEmpty() == true)
        {
            tv_empty_list.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_empty_list.setVisibility(View.GONE);
        }
        ListView_Adapter_SearchPage adapter = new ListView_Adapter_SearchPage(this , book_list);
        lv.setAdapter(adapter);
    }
}
