package ir.sinasoheili.bookstore.PRESENTER;

import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;

public interface Bag_Shop_Page_Contract
{
    public interface Bag_Shop_Page_Contract_view
    {
        public void show_list();
        public void update_list(Book book);
    }

    public interface Bag_Shop_Page_Contract_presenter
    {
        public void get_shop_list_book();
    }
}
