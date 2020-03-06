package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.R;

public class ListView_Adapter_SearchPage extends ArrayAdapter<Book>
{
    private Context context;
    private ArrayList<Book> book_item;

    public ListView_Adapter_SearchPage(@NonNull Context context , @NonNull ArrayList<Book> objects)
    {
        super(context, R.layout.recycler_view_item_search_page , objects);
        this.context = context;
        this.book_item = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        List_view_holder_Search_page holder;

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recycler_view_item_search_page , null , false);
            holder = new List_view_holder_Search_page(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (List_view_holder_Search_page) convertView.getTag();
        }

        holder.fill(book_item.get(position));
        return convertView;
    }

    public class List_view_holder_Search_page
    {
        private ImageView iv_book;
        private TextView tv_book_name;
        private TextView tv_book_autor_name;
        private TextView tv_publisher;
        private TextView tv_group_name;
        private TextView tv_price;

        public List_view_holder_Search_page(@NonNull View itemView)
        {
            iv_book             = itemView.findViewById(R.id.iv_item_recyclerview_searchpage);
            tv_book_name        = itemView.findViewById(R.id.tv_book_name_item_recyclerview_searchpage);
            tv_book_autor_name  = itemView.findViewById(R.id.tv_book_autor_name_item_recyclerview_searchpage);
            tv_publisher        = itemView.findViewById(R.id.tv_book_publisher_item_recyclerview_searchpage);
            tv_group_name       = itemView.findViewById(R.id.tv_book_group_name_item_recyclerview_searchpage);
            tv_price            = itemView.findViewById(R.id.tv_book_price_item_recyclerview_searchpage);
        }

        public void fill(Book book)
        {
            if(book.getFront_pic() != null)
            {
                //TODO:download image and set to iv
            }
            tv_book_name.setText(book.getName());
            tv_book_autor_name.setText("نام نویسنده : "+book.getAutor_name());
            tv_publisher.setText("انتشارات "+book.getPublisher());
            tv_group_name.setText("گروه : "+book.getGroup_name());
            tv_price.setText(String.valueOf(book.getPrice())+" ریال");
        }
    }
}
