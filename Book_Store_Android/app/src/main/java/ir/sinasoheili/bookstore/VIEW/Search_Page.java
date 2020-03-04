package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.sinasoheili.bookstore.R;

public class Search_Page extends Fragment
{
    private Context context;

    private View root_view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        this.root_view = inflater.inflate(R.layout.search_page_layout , null , false);
        this.context = root_view.getContext();
        return root_view;
    }
}
