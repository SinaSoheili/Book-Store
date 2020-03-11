package ir.sinasoheili.bookstore.PRESENTER;

import ir.sinasoheili.bookstore.MODEL.User;

public interface Profile_Page_Contract
{
    public interface Profile_Page_Contract_view
    {
        public void fill_user_information(User user);
    }

    public interface Profile_Page_Contract_presenter
    {
        public void get_user();
        public void log_out_user();
        public void log_in_user();
        public void register_user();
    }
}
