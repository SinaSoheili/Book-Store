package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;
import android.widget.Toast;
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

    public Book_Bought_Presenter(Context context , Book_Bought_Contract.Book_Bought_Contract_view book_bought_view)
    {
        this.context = context;
        this.book_bought_view = book_bought_view;
    }

    @Override
    public void get_bought_book_list(int user_id)
    {
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
                book_bought_view.show_book_list(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t)
            {
                //todo : what to do if cannot connect to server
                Toast.makeText(context , "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
