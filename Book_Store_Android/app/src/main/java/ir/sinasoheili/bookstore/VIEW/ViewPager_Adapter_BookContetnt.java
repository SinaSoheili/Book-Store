package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.io.InputStream;

import ir.sinasoheili.bookstore.PRESENTER.Image_API;
import ir.sinasoheili.bookstore.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewPager_Adapter_BookContetnt extends PagerAdapter
{
    private Context context;
    private String s_image_front, s_image_back;
    private Retrofit retrofit;
    private Image_API api;

    public ViewPager_Adapter_BookContetnt(Context context , String s_image_front , String s_image_back)
    {
        this.context = context;
        this.s_image_front = s_image_front;
        this.s_image_back = s_image_back;

        retrofit = new Retrofit.Builder().baseUrl(Image_API.base_url).build();
        api = retrofit.create(Image_API.class);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        LayoutInflater inflateer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflateer.inflate(R.layout.view_pager_item_book_content , null , false);
        final ImageView iv = view.findViewById(R.id.iv_item_viewpager_book_contetn);

        if(position == 0)
        {
            if(s_image_front != null)
            {
                Call<ResponseBody> call = api.get_image(Image_API.folder_url + s_image_front);
                call.enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                    {
                        InputStream is = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t)
                    {
                        iv.setImageResource(R.drawable.book);
                    }
                });
            }
            else
            {
                iv.setImageResource(R.drawable.book);
            }
        }
        else if(position == 1)
        {
            if(s_image_back != null)
            {
                Call<ResponseBody> call = api.get_image(Image_API.folder_url + s_image_back);
                call.enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                    {
                        InputStream is = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t)
                    {
                        iv.setImageResource(R.drawable.book);
                    }
                });
            }
            else
            {
                iv.setImageResource(R.drawable.book);
            }
        }

        iv.setClipToOutline(true);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }
}
