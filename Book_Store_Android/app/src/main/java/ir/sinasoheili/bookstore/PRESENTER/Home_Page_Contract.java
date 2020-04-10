package ir.sinasoheili.bookstore.PRESENTER;

import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;

public interface Home_Page_Contract
{
    public interface Home_Page_View
    {
        public void show_rv_top_discount(ArrayList<Book> items);
        public void show_rv_top_sell(ArrayList<Book> items);
        public void show_rv_newest(ArrayList<Book> items);
        public void show_rv_favoriot(ArrayList<Book> items);
        public void show_progress_bar();
        public void gone_progress_bar();
        public void fill_banner(int banner_num , Book book);
    }

    public interface Home_Page_Presenter
    {
        public void get_top_discount();
        public void get_top_sell();
        public void get_newest();
        public void get_favoriot();
    }
}
