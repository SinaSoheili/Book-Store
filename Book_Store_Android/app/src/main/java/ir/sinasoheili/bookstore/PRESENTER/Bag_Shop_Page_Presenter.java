package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;
import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.VIEW.Book_Content;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Bag_Shop_Page_Presenter implements Bag_Shop_Page_Contract.Bag_Shop_Page_Contract_presenter
{
    private Context context;
    private Bag_Shop_Page_Contract.Bag_Shop_Page_Contract_view bag_shop_page_view;

    public Bag_Shop_Page_Presenter(Context context , Bag_Shop_Page_Contract.Bag_Shop_Page_Contract_view bag_shop_page_view)
    {
        this.context = context;
        this.bag_shop_page_view = bag_shop_page_view;
    }

    @Override
    public void get_shop_list_book()
    {
        SharedPreferences pref = context.getSharedPreferences(Book_Content.PREF_NAME , Context.MODE_PRIVATE);
        Map<String , Integer> map = (Map<String, Integer>) pref.getAll();
        ArrayList<String> keys = new ArrayList<>(map.keySet());
        bag_shop_page_view.show_list();
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Search_Book_API.base_url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        Search_Book_API api = retrofit.create(Search_Book_API.class);
        for(int i=0 ; i<keys.size() ; i++)
        {
            int id = Integer.parseInt(keys.get(i));
            Call<ArrayList<Book>> call = api.search_book_by_id(id);
            call.enqueue(new Callback<ArrayList<Book>>()
            {
                @Override
                public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response)
                {
                    Book cb = response.body().get(0);
                    bag_shop_page_view.update_list(cb);
                }

                @Override
                public void onFailure(Call<ArrayList<Book>> call, Throwable t)
                {
                    //TODO : what do when cannot get item
                    Toast.makeText(context , "con not connect to database", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
