package com.handen.trends;

import com.handen.trends.data.AccountInfo;
import com.handen.trends.data.Category;
import com.handen.trends.data.Comment;
import com.handen.trends.data.Post;
import com.handen.trends.data.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Vanya on 21.10.2017.
 */

public class ClientInterface {

    public static ArrayList<Post> availablePosts;
    public static ArrayList<User> availableUsers;
    public static ArrayList<Comment> availableComments;

    static {
        availablePosts = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Обо всём"));
        availablePosts.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения1", new ArrayList<>(Arrays.asList("день рожденья1")), false, 0, 1, 0,0));
        availablePosts.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения2", new ArrayList<>(Arrays.asList("день рожденья2")), false, 1, 1, 0,0));
        availablePosts.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения3", new ArrayList<>(Arrays.asList("день рожденья3")), false, 2, 1, 0,0));
        availablePosts.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения4", new ArrayList<>(Arrays.asList("день рожденья4")), false, 3, 1, 0,0));
        availablePosts.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения5", new ArrayList<>(Arrays.asList("день рожденья5")), false, 4, 1, 0,0));
        availablePosts.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения6", new ArrayList<>(Arrays.asList("день рожденья6")), false, 5, 2, 0,0));
        availablePosts.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения7", new ArrayList<>(Arrays.asList("день рожденья7")), false, 6, 2, 0,0));
        availablePosts.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения8", new ArrayList<>(Arrays.asList("день рожденья8")), false, 7, 2, 0,0));
        availablePosts.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения9", new ArrayList<>(Arrays.asList("день рожденья9")), false, 8, 2, 0,0));
        availablePosts.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения10", new ArrayList<>(Arrays.asList("день рожденья10")), false, 9, 3, 0,0));
        availablePosts.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения11", new ArrayList<>(Arrays.asList("день рожденья11")), false, 10, 3, 0,0));
        availablePosts.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения12", new ArrayList<>(Arrays.asList("день рожденья12")), false, 11, 3, 0,0));

        availableUsers = new ArrayList<>();
        availableUsers.add(new User(1, 1, "Handen", new Date()));
        availableUsers.add(new User(2, 2, "Handen2", new Date()));
        availableUsers.add(new User(3, 3, "Handen3", new Date()));


    }


    static public ArrayList<Post> getPosts(int region) {

        ArrayList<Post> ret = new ArrayList<>();

        //Если возвращаем посты для всех регионов
        if(region == 0) {
            ret = availablePosts;
        }
        else {
            for (Post post : availablePosts) {
                int userId = post.getUserId();

                User user = availableUsers.get(userId - 1); //TODO костыль

                if (user.getRegion() == region)
                    ret.add(post);
            }

        }
        return ret;
    }

    static public ArrayList<Post> getSubscribedPosts() {

        return availablePosts;
    }

    static public ArrayList<Post> getUserPosts(long userId) {

        ArrayList<Post> ret = new ArrayList<>();
        for(Post post : availablePosts) {
            if(post.getUserId() == userId)
                ret.add(post);
        }


        return ret;
    }
    static public void likePost(long postId) {


    }
    static public void viewPost(long postId) {

    }

    static public ArrayList<Comment> getComments(long postId) {
        ArrayList<Comment> list = new ArrayList<>();

        list.add(new Comment("Ха-ха. Это комментарий #1", 1));
        list.add(new Comment("Ха-ха. Это комментарий #2", 2));
        list.add(new Comment("Ха-ха. Это комментарий #3", 3));
        list.add(new Comment("Ха-ха. Это комментарий #4", 4));
        list.add(new Comment("Ха-ха. Это комментарий #5", 5));
        return list;
    }

    static public void writeComment(long postId, Comment comment) {



    }
    static public void likeComment(long commentId) {

    }
    static public String register(String nickName, String email, String password) {

        return "OK";

    }

    static public String signIn(String email, String password) {
        return "OK";
    }

    static public void logout() {

    }
    static public void writePost(String title, Category category, String text, ArrayList<String> tags, boolean is24hours ) {

    }
    static public String subscribe(int userId) {
        return "OK";
    }

    static public AccountInfo getAccountInfo(int userId) {

        return new AccountInfo();
    }

}
