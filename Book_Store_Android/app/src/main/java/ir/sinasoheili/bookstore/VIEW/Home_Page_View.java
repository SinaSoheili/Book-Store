package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.Home_Page_Contract;
import ir.sinasoheili.bookstore.PRESENTER.Home_Page_Presenter;
import ir.sinasoheili.bookstore.PRESENTER.Image_API;
import ir.sinasoheili.bookstore.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_Page_View extends Fragment implements Home_Page_Contract.Home_Page_View
{
    private Context context;

    private View root_view;
    private RecyclerView rv_top_discount , rv_top_sell , rv_newest , rv_favoriot;

    private ImageView iv_banner1 , iv_banner2;
    private TextView tv_banner1_name , tv_banner1_summery , tv_banner2_name , tv_banner2_summery;

    private ProgressBar progressbar;

    private Home_Page_Contract.Home_Page_Presenter presenter;

    private Bitmap bimp_banner = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        this.root_view = inflater.inflate(R.layout.home_page_layout , null , false);
        this.context = root_view.getContext();

        init_obj();

        presenter.get_top_discount();
        presenter.get_top_sell();
        presenter.get_favoriot();
        presenter.get_newest();

        return root_view;
    }

    private void init_obj()
    {
        presenter = new Home_Page_Presenter(getContext() , this);

        rv_top_discount = root_view.findViewById(R.id.home_page_recyclerview_topdiscount);
        rv_top_sell     = root_view.findViewById(R.id.home_page_recyclerview_topsell);
        rv_newest       = root_view.findViewById(R.id.home_page_recyclerview_newest);
        rv_favoriot     = root_view.findViewById(R.id.home_page_recyclerview_favoriot);

        progressbar = root_view.findViewById(R.id.Home_page_progress_bar);

        iv_banner1 = root_view.findViewById(R.id.homepage_imageview_banner1);
        tv_banner1_name = root_view.findViewById(R.id.homepage_textview_name_banner1);
        tv_banner1_summery = root_view.findViewById(R.id.homepage_textview_summery_banner1);
        tv_banner1_summery.setMovementMethod(new ScrollingMovementMethod());

        iv_banner2 = root_view.findViewById(R.id.homepage_imageview_banner2);
        tv_banner2_name = root_view.findViewById(R.id.homepage_textview_name_banner2);
        tv_banner2_summery = root_view.findViewById(R.id.homepage_textview_summery_banner2);
        tv_banner2_summery.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void show_rv_top_discount(ArrayList<Book> items)
    {
        RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context , items , new Book_Item_Click_Listener()
        {
            @Override
            public void OnClick(Book book)
            {
                Intent intent = new Intent(context , Book_Content.class);
                intent.putExtra("BOOK" , book);
                context.startActivity(intent);
            }
        });
        rv_top_discount.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
        rv_top_discount.setAdapter(adapter);
    }

    @Override
    public void show_rv_top_sell(ArrayList<Book> items)
    {
        RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
        {
            @Override
            public void OnClick(Book book)
            {
                Intent intent = new Intent(context , Book_Content.class);
                intent.putExtra("BOOK" , book);
                context.startActivity(intent);
            }
        });
        rv_top_sell.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
        rv_top_sell.setAdapter(adapter);
    }

    @Override
    public void show_rv_newest(ArrayList<Book> items)
    {
        RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
        {
            @Override
            public void OnClick(Book book)
            {
                Intent intent = new Intent(context , Book_Content.class);
                intent.putExtra("BOOK" , book);
                context.startActivity(intent);
            }
        });
        rv_newest.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
        rv_newest.setAdapter(adapter);
    }

    @Override
    public void show_rv_favoriot(ArrayList<Book> items)
    {
        RecyclerView_Adapter_HomePage adapter = new RecyclerView_Adapter_HomePage(context, items, new Book_Item_Click_Listener()
        {
            @Override
            public void OnClick(Book book)
            {
                Intent intent = new Intent(context , Book_Content.class);
                intent.putExtra("BOOK" , book);
                context.startActivity(intent);
            }
        });
        rv_favoriot.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.HORIZONTAL , true));
        rv_favoriot.setAdapter(adapter);
    }

    @Override
    public void show_progress_bar()
    {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void gone_progress_bar()
    {
        progressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fill_banner(final int banner_num, final Book book)
    {
        if(banner_num == 0)
        {
            tv_banner1_name.setText(book.getName());
            tv_banner1_summery.setText(book.getSummery());
        }
        else if(banner_num == 1)
        {
            tv_banner2_name.setText(book.getName());
            tv_banner2_summery.setText(book.getSummery());
        }

        if(book.getFront_pic() != null)
        {
            if( (bimp_banner = MainActivity.cache.get(book.getFront_pic())) == null)
            {
                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(Image_API.base_url)
                                        .build();

                Image_API api = retrofit.create(Image_API.class);
                Call<ResponseBody> call = api.get_image(Image_API.folder_url+book.getFront_pic()+".jpg");
                call.enqueue(new Callback<ResponseBody>()
                {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
                    {
                        bimp_banner = BitmapFactory.decodeStream(response.body().byteStream());
                        MainActivity.cache.put(book.getFront_pic() , bimp_banner);

                        if(banner_num == 0)
                        {
                            iv_banner1.setImageBitmap(bimp_banner);
                        }
                        else if(banner_num == 1)
                        {
                            iv_banner2.setImageBitmap(bimp_banner);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t)
                    {
                        bimp_banner = BitmapFactory.decodeResource(getContext().getResources() , R.drawable.book);

                        if(banner_num == 0)
                        {
                            iv_banner1.setImageBitmap(bimp_banner);
                        }
                        else if(banner_num == 1)
                        {
                            iv_banner2.setImageBitmap(bimp_banner);
                        }
                    }
                });
            }
        }
        else
        {
            bimp_banner = BitmapFactory.decodeResource(this.getResources() , R.drawable.book);

            if(banner_num == 0)
            {
                iv_banner1.setImageBitmap(bimp_banner);
            }
            else if(banner_num == 1)
            {
                iv_banner2.setImageBitmap(bimp_banner);
            }
        }
    }
}
