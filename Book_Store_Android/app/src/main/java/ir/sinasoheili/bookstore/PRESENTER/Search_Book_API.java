package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Search_Book_API
{
    public static final String base_url = "http://10.0.2.2/book_store_server/";

    @GET("search_book.php?search_type=id")
    Call<ArrayList<Book>> search_book_by_id(@Query("id") int id);

    @GET("search_book.php?search_type=name")
    Call<ArrayList<Book>> search_book_by_name(@Query("name") String name);

    @GET("search_book.php?search_type=autor_name")
    Call<ArrayList<Book>> search_book_by_autor_name(@Query("autor_name") String autor_name);

    @GET("search_book.php?search_type=group_name")
    Call<ArrayList<Book>> search_book_by_group_name(@Query("group_name") String group_name);
}
