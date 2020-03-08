package ir.sinasoheili.bookstore.VIEW;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.sinasoheili.bookstore.MODEL.Comment;
import ir.sinasoheili.bookstore.MODEL.User;
import ir.sinasoheili.bookstore.PRESENTER.User_Api;
import ir.sinasoheili.bookstore.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerView_Adapter_ContentPage extends RecyclerView.Adapter<RecyclerView_Adapter_ContentPage.ListView_ViewHolder_ContentPage>
{
    private Context context;
    private ArrayList<Comment> comments;

    public RecyclerView_Adapter_ContentPage(Context context , ArrayList<Comment> comments)
    {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public ListView_ViewHolder_ContentPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_view_item_cotent_page, null , false);
        ListView_ViewHolder_ContentPage holder = new ListView_ViewHolder_ContentPage(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListView_ViewHolder_ContentPage holder, int position)
    {
        holder.fill(comments.get(position));
    }

    @Override
    public int getItemCount()
    {
        return comments.size();
    }


    public class ListView_ViewHolder_ContentPage extends RecyclerView.ViewHolder
    {
        private TextView tv_user_name ;
        private TextView tv_date ;
        private TextView tv_content ;

        public ListView_ViewHolder_ContentPage(@NonNull View root_view)
        {
            super(root_view);

            this.tv_user_name = root_view.findViewById(R.id.tv_user_name_comment);
            this.tv_date = root_view.findViewById(R.id.tv_date_comment);
            this.tv_content = root_view.findViewById(R.id.tv_content_comment);
        }

        public void fill(Comment comment)
        {
            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(User_Api.base_url)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

            User_Api api = retrofit.create(User_Api.class);
            Call<User> call = api.get_user_info(comment.getUser_id());
            call.enqueue(new Callback<User>()
            {
                @Override
                public void onResponse(Call<User> call, Response<User> response)
                {
                    tv_user_name.setText(response.body().getName());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t)
                {
                    //TODO:if can't connect to server what to do
                    Toast.makeText(context , "can not connect to server", Toast.LENGTH_SHORT).show();
                }
            });

            tv_date.setText(comment.getDate());
            tv_content.setText(comment.getContent());
        }
    }
}
