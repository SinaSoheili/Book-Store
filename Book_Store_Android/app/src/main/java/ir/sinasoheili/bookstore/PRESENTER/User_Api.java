package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.MODEL.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface User_Api
{
    public static final String base_url = "http://sinasoheili.ir/";
//    public static final String base_url = "http://10.0.2.2/book_store_server/";

    @GET("user_get_specifications.php")
    Call<User> get_user_info(@Query("user_id") int user_id);

    @GET("book_of_user_buy.php")
    Call<ArrayList<Book>> get_book_of_user_bought(@Query("user_id") int user_id);

    @GET("insert_user.php")
    Call<User> register_user(@Query("name") String name , @Query("email") String email , @Query("phone") String phone);

    @GET("insert_user.php")
    Call<User> register_user(@Query("name") String name , @Query("email") String email , @Query("phone") String phone , @Query("family_name") String family_name , @Query("avatar") String avatar);

    @GET("is_user_valid.php")
    Call<User> is_user_valid(@Query("email") String email , @Query("phone") String phone);
}
