package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.VIEW.Book_Bought_List_Activity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Book_Bought_Presenter implements Book_Bought_Contract.Book_Bought_Contract_presenter
{
    private Book_Bought_Contract.Book_Bought_Contract_view book_bought_view;
    private Context context;
    private boolean dialog_is_show = false;

    public Book_Bought_Presenter(Context context , Book_Bought_Contract.Book_Bought_Contract_view book_bought_view)
    {
        this.context = context;
        this.book_bought_view = book_bought_view;
    }

    @Override
    public void get_bought_book_list(int user_id)
    {
        book_bought_view.show_progress_bar();

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
                book_bought_view.hide_progress_bar();

                book_bought_view.show_book_list(response.body());
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
