package ir.sinasoheili.bookstore.VIEW;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

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
    private TextView tv_register_user;

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

        tv_register_user = root_view.findViewById(R.id.tv_register_user_profile_page);
        tv_register_user.setOnClickListener(this);

        pref = context.getSharedPreferences(User.PREF_NAME , Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(tv_book_list_bought))
        {
            if(user == null)
            {
                Toast t = Toast.makeText(context , "برای مشاهده لیست کتاب های خریده شده لطفا در برنامه ثبت نام کنید !" , Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER , 0 , 0);
                t.show();
                return;
            }

            Intent intent = new Intent(context  , Book_Bought_List_Activity.class);
            intent.putExtra("user_id" , user.getId());
            startActivity(intent);
        }
        else if(v.equals(tv_log_in_out_user))
        {
            if(user != null) // log out user
            {
                pref.edit().remove(User.PREF_KEY_USER_ID).commit();
                get_user();
                Toast.makeText(context, "log out", Toast.LENGTH_SHORT).show();
            }
            else if(user == null) //log in user
            {
                show_login_dialog();
            }
        }
        else if(v.equals(tv_register_user))
        {
            show_register_dialog();
        }
    }

    private void get_user() // read id of user from pref AND request to server to fetch data of user
    {
        int user_id = pref.getInt(User.PREF_KEY_USER_ID , -1);
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

    private void show_login_dialog()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.log_in_user_dialog , null , false);
        final EditText et_email = view.findViewById(R.id.et_email_user_login_dialog);
        final EditText et_phone = view.findViewById(R.id.et_phone_user_login_dialog);
        Button  btn_submit = view.findViewById(R.id.btn_login_dialog);
        final TextInputLayout til_phone = view.findViewById(R.id.til_phone_login_dialog);
        final TextInputLayout til_email = view.findViewById(R.id.til_email_login_dialog);

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        dialog.show();
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT , RelativeLayout.LayoutParams.WRAP_CONTENT);

        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = et_email.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();

                if(! is_email_valid(email))
                {
                    til_email.setError("ایمیل وارد شده صحیح نیست !!");
                    et_email.requestFocus();
                    return;
                }
                else
                {
                    til_email.setErrorEnabled(false);
                }

                if(! is_phone_valid(phone))
                {
                    til_phone.setError("شماره وارد شده معتبر نیست !!");
                    et_phone.requestFocus();
                    return;
                }
                else
                {
                    til_phone.setErrorEnabled(false);
                }

                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(User_Api.base_url)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                User_Api api = retrofit.create(User_Api.class);
                Call<User> call = api.is_user_valid(email , phone);
                call.enqueue(new Callback<User>()
                {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response)
                    {
                        user = response.body();
                        if(user != null)
                        {
                            pref.edit().putInt(User.PREF_KEY_USER_ID , user.getId()).commit();
                            get_user();
                        }
                        else
                        {
                            Toast t = Toast.makeText(context, "کاربر در سیستم موجود نیست لطفا ثبت نام کنید !", Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER , 0 , 0);
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t)
                    {
                        //todo : what to do if can't connect to server
                        Toast.makeText(context , "can't connect to server" , Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.dismiss();
            }
        });
    }

    private void show_register_dialog()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.register_user_dialog , null , false);
        final EditText et_name = view.findViewById(R.id.et_name_register_dialog);
        final EditText et_email = view.findViewById(R.id.et_email_user_register_dialog);
        final EditText et_phone = view.findViewById(R.id.et_phone_user_register_dialog);
        Button  btn_submit = view.findViewById(R.id.btn_register_dialog);
        final TextInputLayout til_name  = view.findViewById(R.id.til_name_register_dialog);
        final TextInputLayout til_phone = view.findViewById(R.id.til_phone_register_dialog);
        final TextInputLayout til_email = view.findViewById(R.id.til_email_register_dialog);

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(view);
        dialog.show();
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT , RelativeLayout.LayoutParams.WRAP_CONTENT);

        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name  = et_name .getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();

                if(name.isEmpty())
                {
                    til_name.setError("لطفا نام خود را وارد کنید !!");
                    et_name.requestFocus();
                    return;
                }
                else
                {
                    til_name.setErrorEnabled(false);
                }

                if(! is_email_valid(email))
                {
                    til_email.setError("ایمیل وارد شده صحیح نیست !!");
                    et_email.requestFocus();
                    return;
                }
                else
                {
                    til_email.setErrorEnabled(false);
                }

                if(! is_phone_valid(phone))
                {
                    til_phone.setError("شماره وارد شده معتبر نیست !!");
                    et_phone.requestFocus();
                    return;
                }
                else
                {
                    til_phone.setErrorEnabled(false);
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(User_Api.base_url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                User_Api api = retrofit.create(User_Api.class);
                Call<Integer> call = api.register_user(name , email , phone);
                call.enqueue(new Callback<Integer>()
                {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response)
                    {
                        int user_id = response.body();
                        if(user_id != -1)
                        {
                            pref.edit().putInt(User.PREF_KEY_USER_ID , user_id).commit();
                            get_user();
                            Toast t = Toast.makeText(context , "با موفقیت ثبت شد ." , Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER , 0 , 0);
                            t.show();
                        }
                        else
                        {
                            Toast t = Toast.makeText(context , "کاربر تکراری است .\n لطفا وارد شوید !!" , Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER , 0 , 0 );
                            t.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t)
                    {
                        //todo : what to do if can't connect to server
                        Toast.makeText(context , "can't connect to server" , Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.dismiss();
            }
        });
    }

    private boolean is_email_valid(String email)
    {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

    private boolean is_phone_valid(String phone)
    {
        Pattern pattern = Pattern.compile("09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}");
        return pattern.matcher(phone).matches();
    }
}
