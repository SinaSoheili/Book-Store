package ir.sinasoheili.bookstore.VIEW;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.sinasoheili.bookstore.R;

public class IntroSlider extends AppCompatActivity implements View.OnClickListener
{
    public static final String INTROSLIDER_PREF_NAME = "intro_pref";
    public static final String INTROSLIDER_PREF_KEY  = "show_intro";
    private SharedPreferences pref;
    private ImageView imageview;
    private TextView tv_content;
    private Button btn_next , btn_prev;

    private int id_image[] = {R.drawable.search_introslider , R.drawable.discount_introslider , R.drawable.shoppingbag_introslider};
    private int image_title[] = {R.string.IntroSlider_slide1 , R.string.IntroSlider_slide2 , R.string.IntroSlider_slide3};

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);

        init_obj();

        show_content();
    }

    private void init_obj()
    {
        pref = this.getSharedPreferences(INTROSLIDER_PREF_NAME , MODE_PRIVATE);
        imageview   = findViewById(R.id.iv_introslider);
        tv_content  = findViewById(R.id.tv_introslider);

        btn_next    = findViewById(R.id.btn_introslider_next);
        btn_next.setOnClickListener(this);

        btn_prev    = findViewById(R.id.btn_introslider_prev);
        btn_prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(btn_next))
        {
            if(position+1 < id_image.length)
            {
                btn_prev.setVisibility(View.VISIBLE);

                position++;
                show_content();

                if(position == id_image.length-1)
                {
                    btn_next.setText("شروع برنامه");
                }
            }
            else if(position+1 == id_image.length)
            {
                pref.edit().putBoolean(INTROSLIDER_PREF_KEY , false).commit();
                finish();
            }
        }
        else if(v.equals(btn_prev))
        {
            if(position-1 >= 0)
            {
                btn_next.setText(this.getString(R.string.IntroSlider_next));
                position--;
                if(position == 0)
                {
                    btn_prev.setVisibility(View.INVISIBLE);
                }
                show_content();
            }
        }
    }

    private void show_content()
    {
        imageview.animate().setStartDelay(30).alpha(0).setDuration(800).rotationX(20).start();
        tv_content.animate().setStartDelay(30).alpha(0).setDuration(800).rotationY(30).start();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                imageview.setRotationX(20);
                tv_content.setRotationY(30);

                imageview.setImageResource(id_image[position]);
                imageview.animate().setStartDelay(100).alpha(1).setDuration(500).rotationX(0).start();

                tv_content.setText(IntroSlider.this.getString(image_title[position]));
                tv_content.animate().setStartDelay(100).alpha(1).setDuration(500).rotationY(0).start();
            }
        } , 830);

    }
}
