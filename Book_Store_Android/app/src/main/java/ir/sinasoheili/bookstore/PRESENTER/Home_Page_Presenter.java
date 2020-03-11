package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.VIEW.Book_Content;
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
    private boolean dialog_is_show = false;


    //constructor
    public Home_Page_Presenter(Context context , Home_Page_Contract.Home_Page_View home_page_view)
    {
        this.context = context;
        this.home_page_view = home_page_view;
    }

    //function's
    @Override
    public void get_top_discount()
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
                home_page_view.show_rv_top_discount(items);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    @Override
    public void get_top_sell()
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
                home_page_view.show_rv_top_sell(items);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    @Override
    public void get_newest()
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
                home_page_view.show_rv_newest(items);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    @Override
    public void get_favoriot()
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
                home_page_view.show_rv_favoriot(items);
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    private void show_error_dialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("مشکل در ارتباط با سرور");
        dialog.setMessage("امکان برقراری ارتباط با سرور وجود ندارد لطفا بعدا امتحان کنید!");
        dialog.setCancelable(false);
        dialog.setPositiveButton("باشه", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog_is_show = true;
    }
}
