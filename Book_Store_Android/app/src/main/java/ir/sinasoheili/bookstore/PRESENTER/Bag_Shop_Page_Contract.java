package ir.sinasoheili.bookstore.PRESENTER;

import ir.sinasoheili.bookstore.MODEL.Book;

public interface Bag_Shop_Page_Contract
{
    public interface Bag_Shop_Page_Contract_view
    {
        public void show_list();
        public void update_list(Book book);
        public void show_progress_bar();
        public void hide_progress_bar();
    }

    public interface Bag_Shop_Page_Contract_presenter
    {
        public void get_shop_list_book();
    }
}
