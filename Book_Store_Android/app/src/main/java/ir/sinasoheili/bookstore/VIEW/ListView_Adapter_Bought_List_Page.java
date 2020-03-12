package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.R;

public class ListView_Adapter_Bought_List_Page extends ArrayAdapter<Book>
{
    private Context context;
    private ArrayList<Book> book_item;

    public ListView_Adapter_Bought_List_Page(@NonNull Context context , @NonNull ArrayList<Book> objects)
    {
        super(context, R.layout.list_view_item_bag_list_page, objects);
        this.context = context;
        this.book_item = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        List_view_holder_Bought_list_page holder;

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item_bag_list_page, null , false);
            holder = new List_view_holder_Bought_list_page(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (List_view_holder_Bought_list_page) convertView.getTag();
        }

        holder.fill(book_item.get(position));
        return convertView;
    }

    public class List_view_holder_Bought_list_page
    {
        private ImageView iv_book;
        private TextView tv_book_name;
        private TextView tv_book_autor_name;
        private TextView tv_price;
        private TextView tv_buy;

        public List_view_holder_Bought_list_page(@NonNull View itemView)
        {
            iv_book             = itemView.findViewById(R.id.iv_item_recyclerview_boughtList_page);
            tv_book_name        = itemView.findViewById(R.id.tv_book_name_item_recyclerview_BoughtList_page);
            tv_book_autor_name  = itemView.findViewById(R.id.tv_book_autor_name_item_recyclerview_BoughtList_page);
            tv_price            = itemView.findViewById(R.id.tv_book_price_item_recyclerview_BoughtList_page);
            tv_buy             = itemView.findViewById(R.id.tv_buy_book_item_recyclerview_BoughtList_page);
        }

        public void fill(Book book)
        {
            if(book.getFront_pic() != null)
            {
                //TODO:download image and set to iv
            }
            tv_book_name.setText(book.getName());
            tv_book_autor_name.setText("نام نویسنده : "+book.getAutor_name());
            tv_price.setText(String.valueOf(book.getPrice())+" ریال");
            tv_buy.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //todo : set zarrin pal to buy book
                    Toast t = Toast.makeText(context , "در حال توسعه ..." , Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.CENTER , 0 , 0);
                    t.show();
                }
            });
        }
    }
}
