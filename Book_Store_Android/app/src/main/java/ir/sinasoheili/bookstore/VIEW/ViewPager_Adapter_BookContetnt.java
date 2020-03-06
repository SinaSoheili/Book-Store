package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.R;

public class ViewPager_Adapter_BookContetnt extends PagerAdapter
{
    private Context context;
    private int id_image1 , id_image2;

    public ViewPager_Adapter_BookContetnt(Context context , int id_image1 , int id_image2)
    {
        this.context = context;
        this.id_image1 = id_image1;
        this.id_image2 = id_image2;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        LayoutInflater inflateer = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflateer.inflate(R.layout.view_pager_item_book_content , null , false);
        ImageView iv = view.findViewById(R.id.iv_item_viewpager_book_contetn);
        if(position == 0)
        {
            iv.setImageResource(id_image1);
        }
        else
        {
            iv.setImageResource(id_image2);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }
}
