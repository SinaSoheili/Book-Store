package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;

public interface Search_Page_Contract
{
    public interface Search_Page_view
    {
        public void show_search_result(ArrayList<Book> items);
        public void show_progress_bar();
        public void hide_progress_bar();
    }

    public interface Search_Page_presenter
    {
        public void search_book_by_name(String book_name);
        public void search_book_by_autor_name(String book_autor_name);
        public void search_book_by_group_name(String book_group_name);
    }
}
