package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Search_Page_Presenter implements Search_Page_Contract.Search_Page_presenter
{
    private Search_Page_Contract.Search_Page_view search_page_view;
    private Context context;
    private boolean dialog_is_show = false;

    public Search_Page_Presenter(Context context , Search_Page_Contract.Search_Page_view search_page_view)
    {
        this.context = context;
        this.search_page_view = search_page_view;
    }

    @Override
    public void search_book_by_name(String book_name)
    {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Search_Book_API.base_url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        Search_Book_API api = retrofit.create(Search_Book_API.class);
        Call<ArrayList<Book>> call = api.search_book_by_name(book_name);
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                search_page_view.show_search_result(items);
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
    public void search_book_by_autor_name(String book_autor_name)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Search_Book_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Search_Book_API api = retrofit.create(Search_Book_API.class);
        Call<ArrayList<Book>> call = api.search_book_by_autor_name(book_autor_name);
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                search_page_view.show_search_result(items);
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
    public void search_book_by_group_name(String book_group_name)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Search_Book_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Search_Book_API api = retrofit.create(Search_Book_API.class);
        Call<ArrayList<Book>> call = api.search_book_by_group_name(book_group_name);
        call.enqueue(new Callback<ArrayList<Book>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
            {
                ArrayList<Book> items = response.body();
                search_page_view.show_search_result(items);
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
