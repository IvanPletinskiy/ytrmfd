package com.handen.trends.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.handen.trends.PostComparator;
import com.handen.trends.R;
import com.handen.trends.data.Post;
import com.handen.trends.fragments.TilesFragment;
import com.handen.trends.rows.Row;
import com.handen.trends.rows.Row1;
import com.handen.trends.rows.Row2;
import com.handen.trends.rows.Row3;
import com.handen.trends.rows.Row4;
import com.handen.trends.rows.Row5;
import com.handen.trends.rows.Row6;
import com.handen.trends.rows.Row7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @author Vanya
 * Created by Vanya on 15.10.2017.
 *
 * Класс-адаптер, отвечающий за сортировку постов, их распределение по рядам и создание ViewHolder ов
 */
public class TilesAdapter extends RecyclerView.Adapter<TilesAdapter.ViewHolder> {
    /**
     * Список постов
     */
    private ArrayList<Post> posts;
    /**
     * Список рядов(элементов RecyclerView)
     */
    private ArrayList<Row> rows = new ArrayList<>();
    /**
     * Компаратор для постов
     */
    private PostComparator postComparator;
    /**
     * Высота одной ячейки, у каждого ряда своя высота, выраженная в ячейках. Для первого ряда 6,
     * для второго 3, для третьего и четвёртого 4, для пятого 2, для шестого 2, для седьмого 3
     */
    private float cellHeight;
    /**
     * Слушатель нажатия на tile
     */
    private TilesFragment.OnTileClickListener mListener;

    public TilesAdapter(ArrayList<Post> posts, TilesFragment.OnTileClickListener listener, int recyclerWidth) {
        this.posts = posts;
        this.mListener = listener;
        //Высота ячейки - 1/6 ширины экрана
        cellHeight = recyclerWidth / 6;
        //Генерируем ряды
        generateRows();
    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    @Override
    public int getItemViewType(int position) {
        return rows.get(position).getId();
    }

    @Override
    public TilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int rowType) {
        //В зависимости от типа ряда заливаем нужный xml из разметки и устанавливаем высоту
        int layoutFileResourceId = 0;
        int rowWidth = Math.round(cellHeight * 6);
        int rowHeight = 0;

        switch (rowType) {
            case 1:
                layoutFileResourceId = R.layout.row_1;
                rowHeight = Math.round(cellHeight * 6);
                break;
            case 2:
                layoutFileResourceId = R.layout.row_2;
                rowHeight = Math.round(cellHeight * 3);
                break;
            case 3:
                layoutFileResourceId = R.layout.row_3;
                rowHeight = Math.round(cellHeight * 4);
                break;
            case 4:
                layoutFileResourceId = R.layout.row_4;
                rowHeight = Math.round(cellHeight * 4);
                break;
            case 5:
                layoutFileResourceId = R.layout.row_5;
                rowHeight = Math.round(cellHeight * 2);
                break;
            case 6:
                layoutFileResourceId = R.layout.row_6;
                rowHeight = Math.round(cellHeight * 2);
                break;
            case 7:
                layoutFileResourceId = R.layout.row_7;
                rowHeight = Math.round(cellHeight * 3);
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutFileResourceId, parent, false);
        view.setLayoutParams(new LinearLayout.LayoutParams(rowWidth, rowHeight));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TilesAdapter.ViewHolder holder, int position) {
        //Определяем тип ряда
        int rowType = rows.get(position).getId();
        //id плиток в ряду
        ArrayList<Integer> postIndexes = rows.get(position).getIndexes();
        //В зависимости от типа ряда, находим 1,2,3 плитки и запоминаем их id
        switch (rowType) {
            case 3:
            case 4:
            case 5:
                holder.tile3 = (Button) holder.mView.findViewById(R.id.tile_3);
                holder.id3 = postIndexes.get(2);
            case 2:
                holder.tile2 = (Button) holder.mView.findViewById(R.id.tile_2);
                holder.id2 = postIndexes.get(1);
            case 1:
            case 6:
            case 7:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
        }
        //биндим плитки(устанавливаем название)
        holder.bindTiles();
    }

    private void generateRows() {
        //Сортируем посты
        sortPosts();
        //Заполняем созданные ряды постами
        fillPatternsWithPosts();

    }

    private void sortPosts() {
        //Для каждого поста высчитываем вес и знак(+ или -)
        calculateAndSetPostWeight();
        //    postComparator = new PostComparator(getAveragePopularity(), getAverageHours());
        postComparator = new PostComparator();
        //Сортируем посты
        Collections.sort(posts, postComparator);
        System.err.println(posts);
    }

    private void calculateAndSetPostWeight() {
        //Среднее количество часов с момента публикации
        double averageHours = getAverageHours();
        //Средняя популярность(без даты)
        double averagePopularity = getAveragePopularity();
        //Текущее время
        long currentMillis = new Date().getTime();
        //Миллисекунд в часе
        double hourMillis = 3600000;
        //Популярность поста
        double postPopularity;
        //Часы поста с момента публикации
        double postHours;
        //Дата публикации поста в миллисекундах
        double postMillis;
        for (Post p : posts) {
            postMillis = p.getPostDate().getTime();
            postPopularity = p.getPopularity();
            //Рассчитываем часы поста
            postHours = (currentMillis - postMillis) / hourMillis;
            //Если отношение популярности к среднему, больше чем часов к среднему, то +, иначе -
            if (postPopularity / averagePopularity > averageHours / postHours) {
                p.setPositive(true);
                //Устанавливаем вес поста
                p.setWeight(postPopularity, averagePopularity);
            }
            else {
                //Устанавливаем вес поста
                p.setWeight(averageHours, postHours);
            }
        }
    }

    private void fillPatternsWithPosts() {
        int i = 0;
        for (; i <= posts.size() - 3; i += 3) {
            Post p1 = posts.get(i);
            Post p2 = posts.get(i + 1);
            Post p3 = posts.get(i + 2);
            //Сколько положительных постов
            int positiveCount = getPositiveCount(p1, p2, p3);
            //В зависимости от кол-ва +, создаём нужный ряд
            switch (positiveCount) {
                case 3:
                    rows.add(new Row1(i));
                    rows.add(new Row2(i + 1, i + 2));
                    break;
                case 2:
                    rows.add(new Row3(i, i + 1, i + 2));
                    break;
                case 1:
                    rows.add(new Row4(i, i + 1, i + 2));
                    break;
                case 0:
                    rows.add(new Row5(i, i + 1, i + 2));
                    break;
            }
        }
        //Один элемент
        if (i + 1 == posts.size()) {

            rows.add(new Row6(i));
        }
        //Два элемента
        if (i + 2 == posts.size()) {
            rows.add(new Row7(i));
        }
    }

    private int getPositiveCount(Post p1, Post p2, Post p3) {
        //Считаем кол-во положительных постов
        int count = 0;
        if (p1.isPositive())
            count++;
        if (p2.isPositive())
            count++;
        if (p3.isPositive())
            count++;
        return count;
    }

    private double getAveragePopularity() {
        double totalPopularity = 0;
        for (Post post : posts) {
            totalPopularity += post.getPopularity();
        }
        return totalPopularity / posts.size();
    }

    private double getAverageHours() {
        double totalHours = 0;
        long currentMillis = new Date().getTime();
        for (Post post : posts) {
            totalHours += ((double) (currentMillis - post.getPeriod()) / 3600000);
        }
        return totalHours / posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private Button tile1;
        private Button tile2;
        private Button tile3;
        //индексы постов в списке
        private int id1 = -1;
        private int id2 = -1;
        private int id3 = -1;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

        private void bindTiles() {
            //Устанавливаем названия для плиток
            String postTitle = "emptyPostTitle";
            if (id1 != -1) {
                postTitle = posts.get(id1).getTitle();
                tile1.setText(postTitle);
                tile1.setOnClickListener(createTileOnClickListener(id1));
            }
            if (id2 != -1) {
                postTitle = posts.get(id2).getTitle();
                tile2.setText(postTitle);
                tile2.setOnClickListener(createTileOnClickListener(id2));
            }
            if (id3 != -1) {
                postTitle = posts.get(id3).getTitle();
                tile3.setText(postTitle);
                tile3.setOnClickListener(createTileOnClickListener(id3));
            }
        }

        private View.OnClickListener createTileOnClickListener(final int clickPosition) {
            //Создаём onClickListener для каждой плитки, по нажатию открывается PostActivity
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.startPostActivity(clickPosition, posts);
                }
            };
        }
    }
}
