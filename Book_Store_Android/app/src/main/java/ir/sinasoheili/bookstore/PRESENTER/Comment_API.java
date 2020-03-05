package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Comment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Comment_API
{
    public static String base_url = "http://10.0.2.2/book_store_server/";

    @GET("get_book_comment.php")
    Call<ArrayList<Comment>> get_comment(@Query("book_id") int book_id);
}
