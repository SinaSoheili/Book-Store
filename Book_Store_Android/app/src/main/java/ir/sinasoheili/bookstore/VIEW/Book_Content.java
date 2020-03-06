package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.R;

public class Book_Content extends AppCompatActivity implements View.OnClickListener
{
    private Book book;

    private ViewPager viewpager;
    private ImageView iv_comment;
    private ImageView iv_like;
    private ImageView iv_bag;
    private TextView tv_book_name;
    private TextView tv_autor_name;
    private TextView tv_publisher;
    private TextView tv_translator;
    private TextView tv_price;
    private TextView tv_summery_name;
    private TextView tv_group_name;
    private TextView tv_available_count;

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
    }

    private void init_obj()
    {
        viewpager = findViewById(R.id.viewPager_book_content);

        iv_comment = findViewById(R.id.iv_comment_book_content);
        iv_comment.setOnClickListener(this);

        iv_like = findViewById(R.id.iv_like_book_content);
        iv_like.setOnClickListener(this);

        iv_bag = findViewById(R.id.iv_bag_book_content);
        iv_bag.setOnClickListener(this);

        tv_book_name = findViewById(R.id.tv_bookname_name_book_content);
        tv_autor_name = findViewById(R.id.tv_autor_name_book_content);
        tv_publisher = findViewById(R.id.tv_publisher_book_content);
        tv_translator = findViewById(R.id.tv_translator_name_book_content);
        tv_price = findViewById(R.id.tv_price_book_content);
        tv_summery_name = findViewById(R.id.tv_summery_name_book_content);
        tv_group_name = findViewById(R.id.tv_group_name_book_content);
        tv_available_count = findViewById(R.id.tv_available_count_book_content);
    }

    private void init_view_pager()
    {
        ViewPager_Adapter_BookContetnt adapter = new ViewPager_Adapter_BookContetnt(this , R.drawable.comment , R.drawable.like);
        viewpager.setAdapter(adapter);
    }

    private void fill_obj()
    {
        tv_book_name.setText(book.getName());
        tv_autor_name.setText("نویسنده "+book.getAutor_name());
        tv_publisher.setText("انتشارات "+book.getPublisher());
        tv_translator.setText("مترجم "+book.getTranslator());
        tv_price.setText(book.getPrice()+" ریال");
        tv_summery_name.setText(book.getSummery());
        tv_group_name.setText("دسته "+book.getGroup_name());
        tv_available_count.setText("تعداد موجود "+book.getAvailable_count()+" عدد");
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(iv_comment))
        {
            Toast.makeText(this, "comment", Toast.LENGTH_SHORT).show();
        }
        else if(v.equals(iv_like))
        {
            Toast.makeText(this, "like", Toast.LENGTH_SHORT).show();
        }
        else if(v.equals(iv_bag))
        {
            Toast.makeText(this, "bag", Toast.LENGTH_SHORT).show();
        }
    }
}
