package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ir.sinasoheili.bookstore.MODEL.User;
import ir.sinasoheili.bookstore.PRESENTER.Profile_Page_Contract;
import ir.sinasoheili.bookstore.PRESENTER.Profile_Page_Presenter;
import ir.sinasoheili.bookstore.R;

public class Profile_Page_View extends Fragment implements View.OnClickListener , Profile_Page_Contract.Profile_Page_Contract_view
{
    private Context context;

    private View root_view;

    private TextView tv_user_name;
    private TextView tv_email;
    private TextView tv_phone;
    private TextView tv_book_list_bought;
    private TextView tv_log_in_out_user;
    private TextView tv_register_user;

    private Profile_Page_Contract.Profile_Page_Contract_presenter presenter;
    private User current_user = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState)
    {
        this.root_view = inflater.inflate(R.layout.profile_page_layout , null , false);
        this.context = root_view.getContext();
        init_obj();
        presenter.get_user();
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

        tv_register_user = root_view.findViewById(R.id.tv_register_user_profile_page);
        tv_register_user.setOnClickListener(this);

        presenter = new Profile_Page_Presenter(context , this);
    }

    @Override
    public void fill_user_information(User user)
    {
        current_user = user;

        if(user == null)
        {
            tv_user_name.setText("نام کاربر");
            tv_email.setText("ایمیل ");
            tv_phone.setText("شماره همراه ");
            tv_log_in_out_user.setText("ورود به حساب کاربری");
        }
        else
        {
            tv_user_name.setText(user.getName());
            tv_email.setText("ایمیل : "+user.getEmail());
            tv_phone.setText("شماره همراه : "+user.getPhone());
            tv_log_in_out_user.setText("خروج از حساب کاربری");
        }
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(tv_book_list_bought))
        {
            if(current_user == null) // user don't exist or login into app
            {
                Toast t = Toast.makeText(context , "برای مشاهده لیست کتاب های خریده شده لطفا در برنامه ثبت نام کنید !" , Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER , 0 , 0);
                t.show();
                return;
            }

            Intent intent = new Intent(context  , Book_Bought_List_Activity.class);
            intent.putExtra("user_id" , current_user.getId());
            startActivity(intent);
        }
        else if(v.equals(tv_log_in_out_user))
        {
            if(current_user != null) // log out user
            {
                presenter.log_out_user();

                Toast.makeText(context, "log out", Toast.LENGTH_SHORT).show();
            }
            else if(current_user == null) //log in user
            {
                presenter.log_in_user();
            }
        }
        else if(v.equals(tv_register_user))
        {
            presenter.register_user();
        }
    }
}
