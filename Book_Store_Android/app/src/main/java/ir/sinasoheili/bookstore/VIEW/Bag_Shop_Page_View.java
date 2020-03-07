package ir.sinasoheili.bookstore.VIEW;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ir.sinasoheili.bookstore.R;

public class Bag_Shop_Page_View extends Fragment
{
    private View root_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        root_view = inflater.inflate(R.layout.bag_shop_page_layout , null , false);
        init_obj();
        return root_view;
    }

    private void init_obj()
    {

    }
}
