package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import ir.sinasoheili.bookstore.MODEL.User;
import ir.sinasoheili.bookstore.PRESENTER.User_Api;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_Page_View extends Fragment implements View.OnClickListener
{
    private Context context;

    private View root_view;

    private TextView tv_user_name;
    private TextView tv_email;
    private TextView tv_phone;
    private TextView tv_book_list_bought;
    private TextView tv_log_in_out_user;

    private SharedPreferences pref;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        this.root_view = inflater.inflate(R.layout.profile_page_layout , null , false);
        this.context = root_view.getContext();
        init_obj();
        get_user();
        return root_view;
    }

    private void init_obj()
    {
        tv_user_name = root_view.findViewById(R.id.tv_user_name_profile_page);
        tv_email = root_view.findViewById(R.id.tv_email_profile_page);
        tv_phone = root_view.findViewById(R.id.tv_phone_profile_page);

        tv_book_list_bought = root_view.findViewById(R.id.tv_book_bought_profile_page);
        tv_book_list_bought.setOnClickListener(this);

        tv_log_in_out_user  = root_view.findViewById(R.id.tv_log_in_out_user_profile_page);
        tv_log_in_out_user.setOnClickListener(this);

        pref = context.getSharedPreferences(User.PREF_NAME , Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(tv_book_list_bought))
        {
            Toast.makeText(context, "list", Toast.LENGTH_SHORT).show();
        }
        else if(v.equals(tv_log_in_out_user))
        {
            if(user != null) // log out user
            {
                pref.edit().remove(User.PREF_KEY_USER_ID).commit();
                get_user();
            }
            else if(user == null) //log in or register_user
            {
                Toast.makeText(context, "log in", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void get_user()
    {
        int user_id = pref.getInt(User.PREF_NAME , -1);
        if(user_id == -1)
        {
            user = null;
            fill_user_information();
        }
        else
        {
            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(User_Api.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

            User_Api api = retrofit.create(User_Api.class);
            Call<User> call = api.get_user_info(user_id);
            call.enqueue(new Callback<User>()
            {
                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    user = response.body();
                    fill_user_information();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    //todo:what to do if can't connect to server
                    Toast.makeText(context, "can't connect to server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void fill_user_information()
    {
        if(user == null)
        {
            tv_user_name.setText("نام کاربر");
            tv_email.setText("ایمیل ");
            tv_phone.setText("شماره همراه ");
            tv_log_in_out_user.setText("ورود / ثبت حساب کاربری");
        }
        else
        {
            tv_user_name.setText(user.getName());
            tv_email.setText("ایمیل : "+user.getEmail());
            tv_phone.setText("شماره همراه : "+user.getPhone());
            tv_log_in_out_user.setText("خروج از حساب کاربری");
        }
    }
}
