package ir.sinasoheili.bookstore.VIEW;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.PRESENTER.Search_Page_Contract;
import ir.sinasoheili.bookstore.PRESENTER.Search_Page_Presenter;
import ir.sinasoheili.bookstore.R;

public class Search_Page_View extends Fragment implements Search_Page_Contract.Search_Page_view, SearchView.OnQueryTextListener, View.OnClickListener
{
    private Search_Page_Contract.Search_Page_presenter presenter;
    private Context context;

    private View root_view;

    private SearchView searchview;
    private ImageView iv_filter;
    private RadioGroup radiogroup;
    private ListView listview;
    private TextView tv_filter_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        this.root_view = inflater.inflate(R.layout.search_page_layout , null , false);
        this.context = root_view.getContext();
        init_obj();
        return root_view;
    }

    private void init_obj()
    {
        presenter = new Search_Page_Presenter(context , this);

        radiogroup  = root_view.findViewById(R.id.radio_group_Search_View);

        listview     = root_view.findViewById(R.id.list_view_Search_page);

        tv_filter_title = root_view.findViewById(R.id.tv_filter_Search_View);

        iv_filter   = root_view.findViewById(R.id.image_view_filter_Search_Page);
        iv_filter.setOnClickListener(this);

        searchview  = root_view.findViewById(R.id.searchview_Search_page);
        searchview.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        switch(radiogroup.getCheckedRadioButtonId())
        {
            case R.id.rb_book_name_Search_View:
                presenter.search_book_by_name(query);
                break;

            case R.id.rb_autor_name_Search_View:
                presenter.search_book_by_autor_name(query);
                break;

            case R.id.rb_group_name_Search_View:
                presenter.search_book_by_group_name(query);
                break;
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        return false;
    }

    @Override
    public void show_search_result(ArrayList<Book> items)
    {
        ListView_Adapter_SearchPage adapter = new ListView_Adapter_SearchPage(context , items);
        listview.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(iv_filter))
        {
            String state = (String) radiogroup.getTag();

            if(state.equals("close"))
            {
                //open
                tv_filter_title.animate().alpha(1).setStartDelay(100).setDuration(400).start();

                int max_height = 150;
                ValueAnimator animator = ValueAnimator.ofInt(0 , max_height);
                animator.setDuration(1000);
                animator.setStartDelay(150);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        radiogroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , (int)animation.getAnimatedValue()));
                        radiogroup.invalidate();
                    }
                });
                animator.start();

                radiogroup.setTag("open");
            }
            else if(state.equals("open"))
            {
                //close
                int max_height = 150;
                ValueAnimator animator = ValueAnimator.ofInt(max_height , 0);
                animator.setStartDelay(100);
                animator.setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
                {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation)
                    {
                        radiogroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , (int)animation.getAnimatedValue()));
                        radiogroup.invalidate();
                    }
                });
                animator.start();

                tv_filter_title.animate().alpha(0).setStartDelay(800).setDuration(400).start();

                radiogroup.setTag("close");
            }
        }
    }
}
