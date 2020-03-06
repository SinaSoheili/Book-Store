package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.Home_Page_Contract;
import ir.sinasoheili.bookstore.PRESENTER.Home_Page_Presenter;
import ir.sinasoheili.bookstore.PRESENTER.List_Book_API;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_Page_View extends Fragment implements Home_Page_Contract.Home_Page_View
{
    private Context context;

    private View root_view;
    private RecyclerView rv_top_discount , rv_top_sell , rv_newest , rv_favoriot;
    private ImageView iv_banner1 , iv_banner2;

    private Home_Page_Contract.Home_Page_Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        this.root_view = inflater.inflate(R.layout.home_page_layout , null , false);
        this.context = root_view.getContext();

        init_obj();

        presenter.show_top_discount(rv_top_discount);
        presenter.show_top_sell(rv_top_sell);
        presenter.show_favoriot(rv_favoriot);
        presenter.show_newest(rv_newest);
        presenter.show_banner1(iv_banner1);
        presenter.show_banner2(iv_banner2);

        return root_view;
    }

    private void init_obj()
    {
        presenter = new Home_Page_Presenter(getContext() , this);

        rv_top_discount = root_view.findViewById(R.id.home_page_recyclerview_topdiscount);
        rv_top_sell     = root_view.findViewById(R.id.home_page_recyclerview_topsell);
        rv_newest       = root_view.findViewById(R.id.home_page_recyclerview_newest);
        rv_favoriot     = root_view.findViewById(R.id.home_page_recyclerview_favoriot);

        iv_banner1 = root_view.findViewById(R.id.home_page_imageview_banner1);
        iv_banner2 = root_view.findViewById(R.id.home_page_imageview_banner2);
    }
}
