package ir.sinasoheili.bookstore.PRESENTER;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import ir.sinasoheili.bookstore.MODEL.User;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Profile_Page_Presenter implements Profile_Page_Contract.Profile_Page_Contract_presenter
{
    private Profile_Page_Contract.Profile_Page_Contract_view profile_page_view;
    private Context context;

    private SharedPreferences pref;

    private boolean dialog_is_show = false;

    public Profile_Page_Presenter(Context context , Profile_Page_Contract.Profile_Page_Contract_view profile_page_view)
    {
        this.context = context;
        this.profile_page_view = profile_page_view;
        init_obj();
    }

    private void init_obj()
    {
        pref = context.getSharedPreferences(User.PREF_NAME , Context.MODE_PRIVATE);
    }

    @Override
    public void get_user() // read id of user from pref AND request to server to fetch data of user
    {
        int user_id = pref.getInt(User.PREF_KEY_USER_ID , -1);
        if(user_id == -1)
        {
            User user = null;
            profile_page_view.fill_user_information(user);
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
                    User user = response.body();
                    profile_page_view.fill_user_information(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    if(dialog_is_show == false)
                    {
                        show_error_dialog();
                    }
                }
            });
        }
    }

    @Override
    public void log_out_user()
    {
        pref.edit().remove(User.PREF_KEY_USER_ID).commit();
        profile_page_view.fill_user_information(null);
    }

    @Override
    public void log_in_user()
    {
        show_login_dialog();
    }

    @Override
    public void register_user()
    {
        show_register_dialog();
    }

    private void show_login_dialog()
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.log_in_user_dialog , null , false);

        final EditText et_email = view.findViewById(R.id.et_email_user_login_dialog);
        final EditText et_phone = view.findViewById(R.id.et_phone_user_login_dialog);
        final Button btn_submit = view.findViewById(R.id.btn_login_dialog);
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

                check_user_valid(email , phone);
                dialog.dismiss();
            }
        });
    }

    private void check_user_valid(String email , String phone) // connect to server and check if user is in db show user info else show error toast
    {
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
                User user = response.body();

                if(user != null) //user exist and must be login to system
                {
                    pref.edit().putInt(User.PREF_KEY_USER_ID , user.getId()).commit();
                    profile_page_view.fill_user_information(user);
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
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
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
        final Button  btn_submit = view.findViewById(R.id.btn_register_dialog);
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

                insert_user_todb(name , email , phone);

                dialog.dismiss();
            }
        });
    }

    private void insert_user_todb(String name , String email , String phone) // insert new user to db if insert is success show user info in view else show error toast
    {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(User_Api.base_url)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

        User_Api api = retrofit.create(User_Api.class);
        Call<User> call = api.register_user(name , email , phone);
        call.enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                User user = response.body();
                if(user != null) //mean user created
                {
                    pref.edit().putInt(User.PREF_KEY_USER_ID , user.getId()).commit();
                    profile_page_view.fill_user_information(user);
                    Toast t = Toast.makeText(context , "با موفقیت ثبت شد ." , Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER , 0 , 0);
                    t.show();
                }
                else //mean user exist in db and can not add to db
                {
                    Toast t = Toast.makeText(context , "کاربر تکراری است .\n لطفا وارد شوید !!" , Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER , 0 , 0 );
                    t.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                if(dialog_is_show == false)
                {
                    show_error_dialog();
                }
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

    private void show_error_dialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
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
