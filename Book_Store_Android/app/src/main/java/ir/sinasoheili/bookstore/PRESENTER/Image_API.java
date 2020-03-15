package ir.sinasoheili.bookstore.PRESENTER;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Image_API
{
    public static final String base_url = "http://10.0.2.2/book_store_server/";
    public static final String folder_url = "images/";

    @GET
    Call<ResponseBody> get_image(@Url String url);
}
