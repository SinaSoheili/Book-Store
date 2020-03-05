package ir.sinasoheili.bookstore.PRESENTER;

import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;

public interface Home_Page_Contract
{
    public interface Home_Page_View
    {

    }

    public interface Home_Page_Presenter
    {
        public void show_top_discount(RecyclerView rv);
        public void show_top_sell(RecyclerView rv);
        public void show_newest(RecyclerView rv);
        public void show_favoriot(RecyclerView rv);
        public void show_banner1(ImageView vi);
        public void show_banner2(ImageView vi);
    }
}
