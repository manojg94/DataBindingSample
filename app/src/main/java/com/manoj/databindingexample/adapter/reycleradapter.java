package com.manoj.databindingexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.manoj.databindingexample.Post;
import com.manoj.databindingexample.R;
import com.manoj.databindingexample.databinding.PostRowItemBinding;

import java.util.List;

public class reycleradapter extends RecyclerView.Adapter<reycleradapter.MyViewHolder> {
    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private ReycleradapterListener listener;

    public reycleradapter(List<Post> postList,ReycleradapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PostRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.post_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.binding.setPosts(postList.get(position));
      holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (listener!=null){
                    listener.onPostClicked(postList.get(position));
              }
          }
      });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private PostRowItemBinding binding;

        public MyViewHolder(final PostRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

        }
    }

    public interface ReycleradapterListener{
        void onPostClicked(Post post);
    }

}
