/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/13/16 6:05 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.Book;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final List<Book> mBookList;

    public BookAdapter(List<Book> bookList) {
        mBookList = bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newCategoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(newCategoryView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateView(mBookList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.bookName)
        TextView mBookNameTextView;

        @Bind(R.id.bookAuthor)
        TextView mBookAuthorTextView;

        @Bind(R.id.bookCoverImage)
        ImageView mBookCoverImageView;

        public ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);
        }

        public void updateView(Book book) {
            mBookNameTextView.setText(book.getName());
            mBookAuthorTextView.setText(book.getAuthor());
            ImageLoader.getInstance().displayImage(book.getCoverImageUrl(), mBookCoverImageView);
        }
    }
}
