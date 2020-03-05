package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import retrofit2.Call;
import retrofit2.http.GET;

public interface List_Book_API
{
    public static String base_url = "http://10.0.2.2/book_store_server/";

    @GET("book_list.php?type=top_discount")
    Call<ArrayList<Book>> top_discount();

    @GET("book_list.php?type=newest")
    Call<ArrayList<Book>> newest();

    @GET("book_list.php?type=favoriot")
    Call<ArrayList<Book>> favoriot();

    @GET("book_list.php?type=top_sell")
    Call<ArrayList<Book>> top_sell();
}
