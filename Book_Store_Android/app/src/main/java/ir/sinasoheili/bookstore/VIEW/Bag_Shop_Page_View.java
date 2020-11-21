package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Map;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.Bag_Shop_Page_Contract;
import ir.sinasoheili.bookstore.PRESENTER.Bag_Shop_Page_Presenter;
import ir.sinasoheili.bookstore.R;

public class Bag_Shop_Page_View extends Fragment implements Bag_Shop_Page_Contract.Bag_Shop_Page_Contract_view, AdapterView.OnItemClickListener
{
    private View root_view;
    private ListView list_view_Bag_Shop_page;
    private Bag_Shop_Page_Contract.Bag_Shop_Page_Contract_presenter presenter;
    private ArrayList<Book> book_list;
    private ListView_Adapter_Bag_Shop_Page adapter;
    private TextView tv_empty_list;

    private ProgressBar progressBar;

    private ArrayList<String> keys;
    private SharedPreferences pref;
    private Map<String , Integer> map;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        root_view = inflater.inflate(R.layout.bag_shop_page_layout , null , false);
        init_obj();
        presenter.get_shop_list_book();
        return root_view;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        check_removed_list();
    }

    private void init_obj()
    {
        list_view_Bag_Shop_page = root_view.findViewById(R.id.list_view_Bag_Shop_page);
        list_view_Bag_Shop_page.setOnItemClickListener(this);

        tv_empty_list = root_view.findViewById(R.id.tv_empty_list_Bag_Shop_page);

        presenter = new Bag_Shop_Page_Presenter(getContext() , this);

        book_list = new ArrayList<>();

        progressBar = root_view.findViewById(R.id.bag_shop_page_progress_bar);
    }

    @Override
    public void show_list()
    {
        if(book_list.isEmpty())
        {
            tv_empty_list.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_empty_list.setVisibility(View.GONE);
        }

        adapter = new ListView_Adapter_Bag_Shop_Page(getContext() , book_list);
        list_view_Bag_Shop_page.setAdapter(adapter);
    }

    @Override
    public void update_list(Book book)
    {
        book_list.add(book);

        if(book_list.isEmpty())
        {
            tv_empty_list.setVisibility(View.VISIBLE);
        }
        else
        {
            tv_empty_list.setVisibility(View.GONE);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void show_progress_bar()
    {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide_progress_bar()
    {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(getContext() , Book_Content.class);
        intent.putExtra("BOOK" , book_list.get(position));
        getContext().startActivity(intent);
    }

    private void check_removed_list()
    {
        pref = getContext().getSharedPreferences(Book_Content.PREF_NAME , Context.MODE_PRIVATE);
        map = (Map<String, Integer>) pref.getAll();
        keys = new ArrayList<>(map.keySet());

        for(int i=0 ; i<book_list.size() ; i++)
        {
            int book_id = book_list.get(i).getId();
            boolean find = false;
            for(int j=0 ; j<keys.size() ; j++)
            {
                if(Integer.parseInt(keys.get(j)) == book_id)
                {
                    find = true;
                }
            }

            if(find == false)
            {
                book_list.remove(book_list.get(i));
            }
        }

        show_list();
    }
}
