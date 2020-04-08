package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.MODEL.Comment;
import ir.sinasoheili.bookstore.MODEL.User;
import ir.sinasoheili.bookstore.PRESENTER.Comment_API;
import ir.sinasoheili.bookstore.PRESENTER.Like_API;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private RecyclerView rv_comment;
    private ImageView iv_send_comment;
    private EditText et_comment ;

    private boolean like_state = false;
    private boolean added_to_shop_list = false;

    public static final String PREF_NAME = "pref_add_to_shop_list";
    private SharedPreferences pref ;

    private boolean dialog_is_show = false;

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
        init_comment();
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

        set_like_state();
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

        rv_comment = findViewById(R.id.recycler_view_comment_book_content);

        et_comment = findViewById(R.id.et_book_comment);

        iv_send_comment = findViewById(R.id.iv_book_comment_send);
        iv_send_comment.setOnClickListener(this);

        pref = getSharedPreferences(PREF_NAME , MODE_PRIVATE);
    }

    private void init_view_pager()
    {
        ViewPager_Adapter_BookContetnt adapter = new ViewPager_Adapter_BookContetnt(this ,book.getFront_pic() , book.getBack_pic());
        viewpager.setAdapter(adapter);
    }

    private void init_comment()
    {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(Comment_API.base_url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        Comment_API api = retrofit.create(Comment_API.class);
        Call<ArrayList<Comment>> call = api.get_comment(book.getId());
        call.enqueue(new Callback<ArrayList<Comment>>()
        {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response)
            {
                ArrayList<Comment> comment_item = response.body();
                if(comment_item.size() >= 5)
                {
                    rv_comment.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , 600));
                }
                Collections.reverse(comment_item);
                RecyclerView_Adapter_ContentPage adapter = new RecyclerView_Adapter_ContentPage(Book_Content.this , comment_item);
                rv_comment.setLayoutManager(new LinearLayoutManager(Book_Content.this , LinearLayoutManager.VERTICAL , false));
                rv_comment.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    private void fill_obj()
    {
        tv_book_name.setText(book.getName());
        tv_autor_name.setText(book.getAutor_name());
        tv_publisher.setText(book.getPublisher()   == null ? "" : book.getPublisher());
        tv_translator.setText(book.getTranslator() == null ? "" : book.getTranslator());
        tv_summery_name.setText(book.getSummery()  == null ? "" : book.getSummery());
        tv_group_name.setText(book.getGroup_name() == null ? "" : book.getGroup_name());
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(iv_comment))
        {
            rv_comment.requestFocus();
            et_comment.requestFocus();
        }
        else if(v.equals(lav_like))
        {
            if(get_user_id() == -1)
            {
                Toast t = Toast.makeText(this, "برای لایک کردن کتاب باید در برنامه ثبت نام کنید", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER , 0 , 0 );
                t.show();
                return;
            }

            if(like_state == true)
            {
                remove_like();
            }
            else
            {
                lav_like.playAnimation();

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        add_like();
                    }
                } , lav_like.getDuration());
            }

        }
        else if(v.equals(iv_bag))
        {
            if(added_to_shop_list == true) //remove from shop list
            {
                pref.edit().remove(String.valueOf(book.getId())).commit();
                iv_bag.animate().alpha(0).setStartDelay(60).setDuration(300).start();
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        iv_bag.setImageResource(R.drawable.bag);
                        iv_bag.animate().alpha(1).setStartDelay(0).setDuration(500).start();
                    }
                } , 300);

                added_to_shop_list = false;
            }
            else //add to shop list
            {
                pref.edit().putInt(String.valueOf(book.getId()) , book.getId()).commit();
                iv_bag.animate().alpha(0).setStartDelay(60).setDuration(300).start();
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        iv_bag.setImageResource(R.drawable.tick);
                        iv_bag.animate().alpha(1).setStartDelay(0).setDuration(500).start();
                    }
                } , 300);

                added_to_shop_list = true;
            }
        }
        else if(v.equals(iv_send_comment))
        {
            String msg = et_comment.getText().toString().trim();
            if(msg.isEmpty())
            {
                return;
            }

            int user_id = get_user_id();
            if(user_id == -1)
            {
                Toast t = Toast.makeText(this , "شما در برنامه ثبت نام نکرده اید . لطفا ثبت نام کنید!!" , Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER , 0 , 0);
                t.show();
                return;
            }

            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(Comment_API.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

            Comment_API api = retrofit.create(Comment_API.class);
            Call<String> call = api.insert_comment(user_id , book.getId() ,get_current_date() , msg);
            call.enqueue(new Callback<String>()
            {
                @Override
                public void onResponse(Call<String> call, Response<String> response)
                {
                    boolean result = Boolean.valueOf(response.body());
                    if(result == true)
                    {
                        et_comment.setText("");
                        init_comment();
                    }
                    else
                    {
                        Toast.makeText(Book_Content.this, "امکان ارسال کامنت وجود ندارد لطفا بعدا امتحان کنید !!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t)
                {
                    if(dialog_is_show == false)
                    {
                        show_error_dialog();
                    }
                }
            });
        }
    }

    private int get_user_id()
    {
        SharedPreferences pref = this.getSharedPreferences(User.PREF_NAME , MODE_PRIVATE);
        int id = pref.getInt(User.PREF_KEY_USER_ID , -1);
        return id;
    }

    private String get_current_date()
    {
        String date = "";
        Calendar cl = Calendar.getInstance(TimeZone.getTimeZone("Iran/Tehran"));
        String day = String.valueOf(cl.getTime().getDate());
        String month = String.valueOf(cl.getTime().getMonth()+1);
        String year = String.valueOf(cl.getTime().getYear()+1900);
        date = year+"-"+month+"-"+day;
        return date;
    }

    private void set_like_state()
    {
        int user_id = get_user_id();
        if(user_id == -1)
        {
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Like_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Like_API api = retrofit.create(Like_API.class);
        Call<String> call = api.check_like(user_id , book.getId());
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                like_state = Boolean.parseBoolean(response.body());
                if(like_state == true)
                {
                    lav_like.setProgress(100);
                }
                else
                {
                    lav_like.setProgress(0);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
                like_state = false;
            }
        });
    }

    private void remove_like()
    {
        int user_id = get_user_id();
        if(user_id == -1)
        {
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Like_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Like_API api = retrofit.create(Like_API.class);
        Call<String> call = api.delete_like(user_id , book.getId());
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                set_like_state();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    private void add_like()
    {
        int user_id = get_user_id();
        if(user_id == -1)
        {
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Like_API.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Like_API api = retrofit.create(Like_API.class);
        Call<String> call = api.insert_like(user_id , book.getId());
        call.enqueue(new Callback<String>()
        {
            @Override
            public void onResponse(Call<String> call, Response<String> response)
            {
                set_like_state();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
            }
        });
    }

    private void show_error_dialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("مشکل در ارتباط با سرور");
        dialog.setMessage("امکان برقراری ارتباط با سرور وجود ندارد لطفا بعدا امتحان کنید!");
        dialog.setCancelable(false);
        dialog.setPositiveButton("باشه", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog_is_show = true;
    }
}
