package com.handen.trends;

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
    public static int lastPostId = 49;
    public static int currentUserId = 0;

    public static final int POST_BONUS = 25;
    public static final float POST_IS24BONUS = 1.5f;

    static {
        availablePosts = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Обо всём"));
 /*       availablePosts.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения1", new ArrayList<>(Arrays.asList("день рожденья1")), false, 0, 1, 0,0));
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

        availablePosts.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения13", new ArrayList<>(Arrays.asList("день рожденья1")), false, 12, 1, 0,0));
        availablePosts.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения14", new ArrayList<>(Arrays.asList("день рожденья2")), false, 13, 1, 0,0));
        availablePosts.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения15", new ArrayList<>(Arrays.asList("день рожденья3")), false, 14, 1, 0,0));
        availablePosts.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения16", new ArrayList<>(Arrays.asList("день рожденья4")), false, 15, 1, 0,0));
        availablePosts.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения17", new ArrayList<>(Arrays.asList("день рожденья5")), false, 16, 1, 0,0));
        availablePosts.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения18", new ArrayList<>(Arrays.asList("день рожденья6")), false, 17, 2, 0,0));
        availablePosts.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения19", new ArrayList<>(Arrays.asList("день рожденья7")), false, 18, 2, 0,0));
        availablePosts.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения20", new ArrayList<>(Arrays.asList("день рожденья8")), false, 19, 2, 0,0));
        availablePosts.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения21", new ArrayList<>(Arrays.asList("день рожденья9")), false, 20, 2, 0,0));
        availablePosts.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения22", new ArrayList<>(Arrays.asList("день рожденья10")), false, 21, 3, 0,0));
        availablePosts.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения23", new ArrayList<>(Arrays.asList("день рожденья11")), false, 22, 3, 0,0));
        availablePosts.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения24", new ArrayList<>(Arrays.asList("день рожденья12")), false, 23, 3, 0,0));

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

        availablePosts.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения13", new ArrayList<>(Arrays.asList("день рожденья1")), false, 12, 1, 0,0));
        availablePosts.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения14", new ArrayList<>(Arrays.asList("день рожденья2")), false, 13, 1, 0,0));
        availablePosts.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения15", new ArrayList<>(Arrays.asList("день рожденья3")), false, 14, 1, 0,0));
        availablePosts.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения16", new ArrayList<>(Arrays.asList("день рожденья4")), false, 15, 1, 0,0));
        availablePosts.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения17", new ArrayList<>(Arrays.asList("день рожденья5")), false, 16, 1, 0,0));
        availablePosts.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения18", new ArrayList<>(Arrays.asList("день рожденья6")), false, 17, 2, 0,0));
        availablePosts.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения19", new ArrayList<>(Arrays.asList("день рожденья7")), false, 18, 2, 0,0));
        availablePosts.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения20", new ArrayList<>(Arrays.asList("день рожденья8")), false, 19, 2, 0,0));
        availablePosts.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения21", new ArrayList<>(Arrays.asList("день рожденья9")), false, 20, 2, 0,0));
        availablePosts.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения22", new ArrayList<>(Arrays.asList("день рожденья10")), false, 21, 3, 0,0));
        availablePosts.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения23", new ArrayList<>(Arrays.asList("день рожденья11")), false, 22, 3, 0,0));
        availablePosts.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения24", new ArrayList<>(Arrays.asList("день рожденья12")), false, 23, 3, 0,0));

*/

        availablePosts.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения1", new ArrayList<>(Arrays.asList("день рожденья1")), false, 0, 1, 100, 100));
        availablePosts.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения2", new ArrayList<>(Arrays.asList("день рожденья2")), false, 1, 1, 200,200));
        availablePosts.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения3", new ArrayList<>(Arrays.asList("день рожденья3")), false, 2, 1, 400,400));
        availablePosts.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения4", new ArrayList<>(Arrays.asList("день рожденья4")), false, 3, 1, 500,500));
        availablePosts.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения5", new ArrayList<>(Arrays.asList("день рожденья5")), false, 4, 1, 600,600));
        availablePosts.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения6", new ArrayList<>(Arrays.asList("день рожденья6")), false, 5, 2, 700,700));
        availablePosts.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения7", new ArrayList<>(Arrays.asList("день рожденья7")), false, 6, 2, 800,800));
        availablePosts.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения8", new ArrayList<>(Arrays.asList("день рожденья8")), false, 7, 2, 900,900));
        availablePosts.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения9", new ArrayList<>(Arrays.asList("день рожденья9")), false, 8, 2, 1000,1000));
        availablePosts.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения10", new ArrayList<>(Arrays.asList("день рожденья10")), false, 9, 3, 1100,1100));
        availablePosts.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения11", new ArrayList<>(Arrays.asList("день рожденья11")), false, 10, 3, 1200,1200));
        availablePosts.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения12", new ArrayList<>(Arrays.asList("день рожденья12")), false, 11, 3, 1300,1300));

        availableUsers = new ArrayList<>();
        availableUsers.add(new User(1, 1, "Handen", new Date()));
        availableUsers.get(0).setDescription("Это описание #1");

        availableUsers.add(new User(2, 2, "Handen2", new Date()));
        availableUsers.get(1).setDescription("Это описание #2");

        availableUsers.add(new User(3, 3, "Handen3", new Date()));
        availableUsers.get(2).setDescription("Это описание #3");
        availableUsers.add(new User(2, 0, "God", new Date()));
        availableUsers.get(2).setDescription("Одмен");


/*        for(int i = 0; i < 10 ; i ++) {
            int random = (int)(Math.random() * 100);
            availablePosts.add(new Post(Integer.toString(random), categories, Integer.toString(random),
                    new ArrayList<>(Arrays.asList(Integer.toString(random))), true, 24 + i, 1, random, random));
        }
*/

    }



    static public ArrayList<Post> getPosts(int region) {

        ArrayList<Post> ret = new ArrayList<>();

        //Если возвращаем посты для всех регионов
        if(region == 0) {
            ret = availablePosts;
        }
        else {
            for (Post post : availablePosts) {
                long userId = post.getUserId();

             //   User user = availableUsers.get((int) (userId)); //TODO происходит преобразование long в int

                if (getUser(userId).getRegion() == region)
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
        availablePosts.add(new Post(title, category, text, tags, is24hours, ++lastPostId, currentUserId));
    }
    static public String subscribe(int userId) {
        return "OK";
    }

    static public ArrayList<Category> getCategories() {
        return null;
    }


/*    static public UserInformation getUserInformation(long userId) {

        for (int i = 0; i < availableUsers.size(); i++) {
            User user = availableUsers.get(i);
            if(user.getId() == userId);
                return user.get;
        }
    }
    */

    static public User getUser(long userId) {
        for (User user : availableUsers) {
            if(user.getId() == userId)
                return user;
        }
        return null;
    }
}
