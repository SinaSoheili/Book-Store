package ir.sinasoheili.bookstore.PRESENTER;

import android.content.Context;

public class Search_Page_Presenter implements Search_Page_Contract.Search_Page_presenter
{
    private Search_Page_Contract.Search_Page_view view;
    private Context context;

    public Search_Page_Presenter(Context context , Search_Page_Contract.Search_Page_view view)
    {
        this.view = view;
    }
}
