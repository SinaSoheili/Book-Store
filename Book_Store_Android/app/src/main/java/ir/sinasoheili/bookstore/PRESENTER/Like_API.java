package ir.sinasoheili.bookstore.PRESENTER;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Like_API
{
    public static String base_url = "http://10.0.2.2/book_store_server/";

    @GET("user_like_book.php?type=delete_like")
    Call<String> delete_like(@Query("user_id") int user_id , @Query("book_id") int book_id);

    @GET("user_like_book.php?type=insert_like")
    Call<String> insert_like(@Query("user_id") int user_id , @Query("book_id") int book_id);

    @GET("user_like_book.php?type=check_like")
    Call<String> check_like(@Query("user_id") int user_id , @Query("book_id") int book_id);
}
