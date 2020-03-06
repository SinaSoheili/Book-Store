package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.VIEW.Book_Item_Click_Listener;
import ir.sinasoheili.bookstore.VIEW.RecyclerView_Adapter_HomePage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_Page_Presenter implements Home_Page_Contract.Home_Page_Presenter
{
    private Context context;
    private Home_Page_Contract.Home_Page_View home_page_view;


    //constructor
    public Home_Page_Presenter(Context context , Home_Page_Contract.Home_Page_View home_page_view)
    {
        this.context = context;
        this.home_page_view = home_page_view;
    }

    //function's
    @Override
    public void show_top_discount(final RecyclerView rv)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(List_Book_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        List_Book_API api = retrofit.create(List_Book_API.class);
        Call<ArrayList<Book>> call = api.top_discount();
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
                {
                    @Override
                    public void OnClick(Book book)
                    {
                        Toast.makeText(context, book.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                rv.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                //TODO:when can't get info from server what to do?
                Toast.makeText(context, "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void show_top_sell(final RecyclerView rv)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(List_Book_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        List_Book_API api = retrofit.create(List_Book_API.class);
        Call<ArrayList<Book>> call = api.top_sell();
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
                {
                    @Override
                    public void OnClick(Book book)
                    {
                        Toast.makeText(context, book.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                rv.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                //TODO:when can't get info from server what to do?
                Toast.makeText(context, "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void show_newest(final RecyclerView rv)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(List_Book_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        List_Book_API api = retrofit.create(List_Book_API.class);
        Call<ArrayList<Book>> call = api.newest();
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
                {
                    @Override
                    public void OnClick(Book book)
                    {
                        Toast.makeText(context, book.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                rv.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                //TODO:when can't get info from server what to do?
                Toast.makeText(context, "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void show_favoriot(final RecyclerView rv)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(List_Book_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        List_Book_API api = retrofit.create(List_Book_API.class);
        Call<ArrayList<Book>> call = api.favoriot();
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
                {
                    @Override
                    public void OnClick(Book book)
                    {
                        Toast.makeText(context, book.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                rv.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                //TODO:when can't get info from server what to do?
                Toast.makeText(context, "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void show_banner1(ImageView vi)
    {
        //TODO:download image of banner and show that
    }

    @Override
    public void show_banner2(ImageView vi)
    {
        //TODO:download image of banner and show that
    }
}
