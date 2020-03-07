package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.R;

public class Book_Content extends AppCompatActivity implements View.OnClickListener
{
    private Book book;

    private ViewPager viewpager;
    private ImageView iv_comment;
    private LottieAnimationView lav_like;
    private ImageView iv_bag;
    private TextView tv_book_name;
    private TextView tv_autor_name;
    private TextView tv_publisher;
    private TextView tv_translator;
    private TextView tv_summery_name;
    private TextView tv_group_name;

    private boolean like_state = false;
    private boolean added_to_shop_list = false;

    public static final String PREF_NAME = "pref_add_to_shop_list";
    private SharedPreferences pref ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__content);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
            book = (Book) bundle.get("BOOK");
        }

        init_obj();
        init_view_pager();
        fill_obj();

        if(pref.contains(String.valueOf(book.getId())) == true)
        {
            added_to_shop_list = true;
        }
        if(added_to_shop_list == true)
        {
            iv_bag.setImageResource(R.drawable.tick);
        }
        else
        {
            iv_bag.setImageResource(R.drawable.bag);
        }

        if(like_state == true)
        {
            lav_like.setProgress(100);
        }
        else
        {
            lav_like.setProgress(0);
        }
    }

    private void init_obj()
    {
        viewpager = findViewById(R.id.viewPager_book_content);

        iv_comment = findViewById(R.id.iv_comment_book_content);
        iv_comment.setOnClickListener(this);

        lav_like = findViewById(R.id.lav_like_book_content);
        lav_like.setOnClickListener(this);

        iv_bag = findViewById(R.id.iv_bag_book_content);
        iv_bag.setOnClickListener(this);

        tv_book_name = findViewById(R.id.tv_bookname_name_book_content);
        tv_autor_name = findViewById(R.id.tv_autor_name_book_content);
        tv_publisher = findViewById(R.id.tv_publisher_book_content);
        tv_translator = findViewById(R.id.tv_translator_name_book_content);
        tv_summery_name = findViewById(R.id.tv_summery_name_book_content);
        tv_group_name = findViewById(R.id.tv_group_name_book_content);

        pref = getSharedPreferences(PREF_NAME , MODE_PRIVATE);
    }

    private void init_view_pager()
    {
        ViewPager_Adapter_BookContetnt adapter = new ViewPager_Adapter_BookContetnt(this , R.drawable.book , R.drawable.book);
        viewpager.setAdapter(adapter);
    }

    private void fill_obj()
    {
        tv_book_name.setText(book.getName());
        tv_autor_name.setText("نویسنده "+book.getAutor_name());
        tv_publisher.setText("انتشارات "+book.getPublisher());
        tv_translator.setText("مترجم "+book.getTranslator());
        tv_summery_name.setText("توضیحات : "+book.getSummery());
        tv_group_name.setText("گروه "+book.getGroup_name());
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(iv_comment))
        {
            Toast.makeText(this, "comment", Toast.LENGTH_SHORT).show();
        }
        else if(v.equals(lav_like))
        {
            if(like_state == true)
            {
                lav_like.setProgress(0);
                like_state = false;
            }
            else
            {
                lav_like.playAnimation();
                like_state = true;
            }

        }
        else if(v.equals(iv_bag))
        {
            if(added_to_shop_list == true)
            {
                pref.edit().remove(String.valueOf(book.getId())).commit();
                iv_bag.setImageResource(R.drawable.bag);
                added_to_shop_list = false;
            }
            else
            {
                pref.edit().putInt(String.valueOf(book.getId()) , book.getId()).commit();
                iv_bag.setImageResource(R.drawable.tick);
                added_to_shop_list = true;
            }
        }
    }
}
