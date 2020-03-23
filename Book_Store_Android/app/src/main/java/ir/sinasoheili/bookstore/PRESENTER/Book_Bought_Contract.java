package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;

public interface Book_Bought_Contract
{
    public interface Book_Bought_Contract_view
    {
        public void show_book_list(ArrayList<Book> list);
        public void show_progress_bar();
        public void hide_progress_bar();
    }

    public interface Book_Bought_Contract_presenter
    {
        public void get_bought_book_list(int user_id);
    }
}
