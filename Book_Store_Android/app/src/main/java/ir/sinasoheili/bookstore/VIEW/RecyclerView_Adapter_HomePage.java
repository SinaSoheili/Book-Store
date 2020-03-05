package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import ir.sinasoheili.bookstore.MODEL.Book;
import ir.sinasoheili.bookstore.R;

public class RecyclerView_Adapter_HomePage extends RecyclerView.Adapter<RecyclerView_Adapter_HomePage.ViewHolder_RecyclerView_HomePage>
{

    private ArrayList<Book> all_book;
    private Context context;

    public RecyclerView_Adapter_HomePage(Context context , ArrayList<Book> all_book)
    {
        this.context = context;
        this.all_book = all_book;
    }

    @NonNull
    @Override
    public ViewHolder_RecyclerView_HomePage onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_item_home_page , null , false);
        ViewHolder_RecyclerView_HomePage viewholder = new ViewHolder_RecyclerView_HomePage(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_RecyclerView_HomePage holder, int position)
    {
        holder.fill(all_book.get(position));
    }

    @Override
    public int getItemCount()
    {
        return all_book.size();
    }

    public class ViewHolder_RecyclerView_HomePage extends RecyclerView.ViewHolder
    {
        private ImageView iv_book;
        private TextView tv_book_title , tv_book_autor;

        public ViewHolder_RecyclerView_HomePage(@NonNull View itemView)
        {
            super(itemView);
            iv_book = itemView.findViewById(R.id.iv_item_recyclerview_homepage);
            tv_book_title = itemView.findViewById(R.id.tv_book_title_item_recyclerview_homepage);
            tv_book_autor = itemView.findViewById(R.id.tv_book_autor_item_recyclerview_homepage);
        }

        public void fill(Book book)
        {
            if(book.getFront_pic() != null)
            {
                //TODO : download image
            }
            this.tv_book_title.setText(book.getName());
            this.tv_book_autor.setText(book.getAutor_name());
        }
    }
}
