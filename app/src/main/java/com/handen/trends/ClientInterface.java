package com.handen.trends;

import com.handen.trends.data.Category;
import com.handen.trends.data.Comment;
import com.handen.trends.data.Post;
import com.handen.trends.data.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Vanya on 21.10.2017.
 */

public class ClientInterface {

    public static ArrayList<Post> postsTable;
    public static ArrayList<User> usersTable;
    public static ArrayList<Comment> commentsTable;
    /**
     * Первый аргумент userId, второй postId
     */
    public static HashMap<Long, HashSet<Long>> likesTable = new HashMap<>();

    /**
     * Первый аргумент userId, второй postId
     */
    public static HashMap<Long, HashSet<Long>> viewsTable = new HashMap<>();
    public static int lastPostId = 49;
    public static int currentUserId = 1;

    public static final int POST_BONUS = 25;
    public static final float POST_IS24BONUS = 1.5f;

    static {
        postsTable = new ArrayList<>();
       // ArrayList<Category> categories = new ArrayList<>();
       // categories.add(new Category("Обо всём"));
        Category category = new Category("Обо всём");

 /*       postsTable.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения1", new ArrayList<>(Arrays.asList("день рожденья1")), false, 0, 1, 0,0));
        postsTable.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения2", new ArrayList<>(Arrays.asList("день рожденья2")), false, 1, 1, 0,0));
        postsTable.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения3", new ArrayList<>(Arrays.asList("день рожденья3")), false, 2, 1, 0,0));
        postsTable.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения4", new ArrayList<>(Arrays.asList("день рожденья4")), false, 3, 1, 0,0));
        postsTable.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения5", new ArrayList<>(Arrays.asList("день рожденья5")), false, 4, 1, 0,0));
        postsTable.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения6", new ArrayList<>(Arrays.asList("день рожденья6")), false, 5, 2, 0,0));
        postsTable.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения7", new ArrayList<>(Arrays.asList("день рожденья7")), false, 6, 2, 0,0));
        postsTable.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения8", new ArrayList<>(Arrays.asList("день рожденья8")), false, 7, 2, 0,0));
        postsTable.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения9", new ArrayList<>(Arrays.asList("день рожденья9")), false, 8, 2, 0,0));
        postsTable.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения10", new ArrayList<>(Arrays.asList("день рожденья10")), false, 9, 3, 0,0));
        postsTable.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения11", new ArrayList<>(Arrays.asList("день рожденья11")), false, 10, 3, 0,0));
        postsTable.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения12", new ArrayList<>(Arrays.asList("день рожденья12")), false, 11, 3, 0,0));

        postsTable.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения13", new ArrayList<>(Arrays.asList("день рожденья1")), false, 12, 1, 0,0));
        postsTable.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения14", new ArrayList<>(Arrays.asList("день рожденья2")), false, 13, 1, 0,0));
        postsTable.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения15", new ArrayList<>(Arrays.asList("день рожденья3")), false, 14, 1, 0,0));
        postsTable.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения16", new ArrayList<>(Arrays.asList("день рожденья4")), false, 15, 1, 0,0));
        postsTable.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения17", new ArrayList<>(Arrays.asList("день рожденья5")), false, 16, 1, 0,0));
        postsTable.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения18", new ArrayList<>(Arrays.asList("день рожденья6")), false, 17, 2, 0,0));
        postsTable.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения19", new ArrayList<>(Arrays.asList("день рожденья7")), false, 18, 2, 0,0));
        postsTable.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения20", new ArrayList<>(Arrays.asList("день рожденья8")), false, 19, 2, 0,0));
        postsTable.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения21", new ArrayList<>(Arrays.asList("день рожденья9")), false, 20, 2, 0,0));
        postsTable.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения22", new ArrayList<>(Arrays.asList("день рожденья10")), false, 21, 3, 0,0));
        postsTable.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения23", new ArrayList<>(Arrays.asList("день рожденья11")), false, 22, 3, 0,0));
        postsTable.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения24", new ArrayList<>(Arrays.asList("день рожденья12")), false, 23, 3, 0,0));

        postsTable.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения1", new ArrayList<>(Arrays.asList("день рожденья1")), false, 0, 1, 0,0));
        postsTable.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения2", new ArrayList<>(Arrays.asList("день рожденья2")), false, 1, 1, 0,0));
        postsTable.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения3", new ArrayList<>(Arrays.asList("день рожденья3")), false, 2, 1, 0,0));
        postsTable.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения4", new ArrayList<>(Arrays.asList("день рожденья4")), false, 3, 1, 0,0));
        postsTable.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения5", new ArrayList<>(Arrays.asList("день рожденья5")), false, 4, 1, 0,0));
        postsTable.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения6", new ArrayList<>(Arrays.asList("день рожденья6")), false, 5, 2, 0,0));
        postsTable.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения7", new ArrayList<>(Arrays.asList("день рожденья7")), false, 6, 2, 0,0));
        postsTable.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения8", new ArrayList<>(Arrays.asList("день рожденья8")), false, 7, 2, 0,0));
        postsTable.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения9", new ArrayList<>(Arrays.asList("день рожденья9")), false, 8, 2, 0,0));
        postsTable.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения10", new ArrayList<>(Arrays.asList("день рожденья10")), false, 9, 3, 0,0));
        postsTable.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения11", new ArrayList<>(Arrays.asList("день рожденья11")), false, 10, 3, 0,0));
        postsTable.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения12", new ArrayList<>(Arrays.asList("день рожденья12")), false, 11, 3, 0,0));

        postsTable.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения13", new ArrayList<>(Arrays.asList("день рожденья1")), false, 12, 1, 0,0));
        postsTable.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения14", new ArrayList<>(Arrays.asList("день рожденья2")), false, 13, 1, 0,0));
        postsTable.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения15", new ArrayList<>(Arrays.asList("день рожденья3")), false, 14, 1, 0,0));
        postsTable.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения16", new ArrayList<>(Arrays.asList("день рожденья4")), false, 15, 1, 0,0));
        postsTable.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения17", new ArrayList<>(Arrays.asList("день рожденья5")), false, 16, 1, 0,0));
        postsTable.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения18", new ArrayList<>(Arrays.asList("день рожденья6")), false, 17, 2, 0,0));
        postsTable.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения19", new ArrayList<>(Arrays.asList("день рожденья7")), false, 18, 2, 0,0));
        postsTable.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения20", new ArrayList<>(Arrays.asList("день рожденья8")), false, 19, 2, 0,0));
        postsTable.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения21", new ArrayList<>(Arrays.asList("день рожденья9")), false, 20, 2, 0,0));
        postsTable.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения22", new ArrayList<>(Arrays.asList("день рожденья10")), false, 21, 3, 0,0));
        postsTable.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения23", new ArrayList<>(Arrays.asList("день рожденья11")), false, 22, 3, 0,0));
        postsTable.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения24", new ArrayList<>(Arrays.asList("день рожденья12")), false, 23, 3, 0,0));

*/
/*
        postsTable.add(new Post("День рождения7", categories, "У меня сегодня дунь рождения7", new ArrayList<>(Arrays.asList("день рожденья7")), false, 6, 2, 800,800));
        postsTable.add(new Post("День рождения9", categories, "У меня сегодня дунь рождения9", new ArrayList<>(Arrays.asList("день рожденья9")), false, 8, 2, 1000,1000));

        postsTable.add(new Post("День рождения1", categories, "У меня сегодня дунь рождения1", new ArrayList<>(Arrays.asList("день рожденья1")), false, 0, 1, 100, 100));
        postsTable.add(new Post("День рождения2", categories, "У меня сегодня дунь рождения2", new ArrayList<>(Arrays.asList("день рожденья2")), false, 1, 1, 200,200));
        postsTable.add(new Post("День рождения3", categories, "У меня сегодня дунь рождения3", new ArrayList<>(Arrays.asList("день рожденья3")), false, 2, 1, 400,400));
        postsTable.add(new Post("День рождения4", categories, "У меня сегодня дунь рождения4", new ArrayList<>(Arrays.asList("день рожденья4")), false, 3, 1, 500,500));
        postsTable.add(new Post("День рождения5", categories, "У меня сегодня дунь рождения5", new ArrayList<>(Arrays.asList("день рожденья5")), false, 4, 1, 600,600));
        postsTable.add(new Post("День рождения6", categories, "У меня сегодня дунь рождения6", new ArrayList<>(Arrays.asList("день рожденья6")), false, 5, 2, 700,700));

        postsTable.add(new Post("День рождения8", categories, "У меня сегодня дунь рождения8", new ArrayList<>(Arrays.asList("день рожденья8")), false, 7, 2, 900,900));

        postsTable.add(new Post("День рождения10", categories, "У меня сегодня дунь рождения10", new ArrayList<>(Arrays.asList("день рожденья10")), false, 9, 3, 1100,1100));
        postsTable.add(new Post("День рождения11", categories, "У меня сегодня дунь рождения11", new ArrayList<>(Arrays.asList("день рожденья11")), false, 10, 3, 1200,1200));
        postsTable.add(new Post("День рождения12", categories, "У меня сегодня дунь рождения12", new ArrayList<>(Arrays.asList("день рожденья12")), false, 11, 3, 1300,1300));
*/
        usersTable = new ArrayList<>();
        usersTable.add(new User(1, 1, "Handen", new Date()));
        usersTable.get(0).setDescription("Это описание #1");

        usersTable.add(new User(2, 2, "Handen2", new Date()));
        usersTable.get(1).setDescription("Это описание #2");

        usersTable.add(new User(3, 3, "Handen3", new Date()));
        usersTable.get(2).setDescription("Это описание #3");
        usersTable.add(new User(2, 0, "God", new Date()));
        usersTable.get(2).setDescription("Одмен");

        for(int i = 0; i < 20 ; i ++) {
            int random = (int)(Math.random() * 100);
            postsTable.add(new Post(Integer.toString(random), category, Integer.toString(random),
                    new ArrayList<>(Arrays.asList(Integer.toString(random))), true, 24 + i, 1, random, random));
        }
    }

    static public ArrayList<Post> getPosts(int region) {
        ArrayList<Post> ret = new ArrayList<>();
        //Если возвращаем посты для всех регионов
        if(region == 0) {
            ret = postsTable;
        }
        else {
            for (Post post : postsTable) {
                long userId = post.getUserId();
             //   User user = usersTable.get((int) (userId)); //TODO происходит преобразование long в int
                if (getUser(userId).getRegion() == region)
                    ret.add(post);
            }
        }
        return ret;
    }

    static public ArrayList<Post> getSubscribedPosts() {
        return postsTable;
    }

    static public ArrayList<Post> getUserPosts(long userId) {
        ArrayList<Post> ret = new ArrayList<>();
        for(Post post : postsTable) {
            if(post.getUserId() == userId)
                ret.add(post);
        }

        return ret;
    }
    static public void likePost(long postId) {
        if(likesTable.containsKey((long)currentUserId)) {
            likesTable.get((long)currentUserId).add(postId);
        }
        else {
            HashSet<Long> set = new HashSet<>();
            set.add(postId);
            likesTable.put((long)currentUserId, set);
        }

        for(Post post : postsTable) {
            if(post.getId() == postId) {
                post.setLikes(post.getLikes() + 1);
            }
        }
    }

    static public void unlikePost(long postId) {
        likesTable.get((long)currentUserId).remove(postId);
        for(Post post : postsTable) {
            if(post.getId() == postId) {
                post.setLikes(post.getLikes() - 1);
            }
        }
    }
    static public void viewPost(long postId) {
        if(viewsTable.containsKey((long)currentUserId)) {
            viewsTable.get((long)currentUserId).add(postId);
        }
        else {
            HashSet<Long> set = new HashSet<>();
            set.add(postId);
            viewsTable.put((long)currentUserId, set);
        }

        for(Post post : postsTable) {
            if(post.getId() == postId) {
                post.setViews(post.getViews() + 1);
            }
        }

    }

    static public boolean isLiked(long postId) {
        boolean isFound = false;
        if(likesTable.containsKey((long)currentUserId) &&
                likesTable.get((long)currentUserId).contains(postId))
            isFound = true;
        return isFound;
    }
    static public boolean isViewed(long postId) {
        boolean isFound = false;
        if(viewsTable.containsKey((long)currentUserId) &&
                viewsTable.get((long)currentUserId).contains(postId))
            isFound = true;
        return isFound;
    }

    static public ArrayList<Post> getLiked() {
        ArrayList<Post> ret = new ArrayList<>();
        HashSet<Long> set = likesTable.get((long)currentUserId);

        for (Long postId : set) {
            for (Post post : postsTable) {
                if(postId == post.getId())
                    ret.add(post);
            }
        }
        return ret;
    }


    static public void deletePost(long postId) {
        for(Post post : postsTable) {
            if(post.getId() == postId) {
                postsTable.remove(post);
            }
        }
    }
    //TODO 30.12.2017 Добавить Category и Tags в этот метод
    static public void updatePost(long postId, String title, String text) {
        for(Post post : postsTable) {
            if(post.getId() == postId) {
                post.setTitle(title);
                post.setText(text);
            }
        }
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
        postsTable.add(new Post(title, category, text, tags, is24hours, ++lastPostId, currentUserId));
    }
    static public String subscribe(int userId) {
        return "OK";
    }

    static public ArrayList<Category> getCategories() {
        return null;
    }
/*    static public UserInformation getUserInformation(long userId) {

        for (int i = 0; i < usersTable.size(); i++) {
            User user = usersTable.get(i);
            if(user.getId() == userId);
                return user.get;
        }
    }
    */
    static public User getUser(long userId) {
        for (User user : usersTable) {
            if(user.getId() == userId)
                return user;
        }
        return null;
    }
}
