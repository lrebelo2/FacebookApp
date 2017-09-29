package com.example.lucas.fbsearch;

import java.util.List;

public class ResponseDetails{
    private String name;
    private Albums albums;
    private Posts posts;
    private String id;
    private Picture picture;

    public String getName(){
        return name;
    }
    public void setName(String input){
        this.name = input;
    }
    public Albums getAlbums(){
        return albums;
    }
    public void setAlbums(Albums input){
        this.albums = input;
    }
    public Posts getPosts(){
        return posts;
    }
    public void setPosts(Posts input){
        this.posts = input;
    }
    public String getId(){
        return id;
    }
    public void setId(String input){
        this.id = input;
    }
    public void setPicture(Picture input) {this.picture = input;}
    public Picture getPicture() { return picture;}
    public class Picture {
        private Response.PicData data;

        public Response.PicData getData() {
            return data;
        }

        public void setData(Response.PicData input) {
            this.data = input;
        }
    }
    public class PicData {
        private int height;
        private boolean isSilhouette;
        private String url;
        private int width;

        public int getHeight() {
            return height;
        }

        public void setHeight(int input) {
            this.height = input;
        }

        public boolean getIsSilhouette() {
            return isSilhouette;
        }

        public void setIsSilhouette(boolean input) {
            this.isSilhouette = input;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String input) {
            this.url = input;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int input) {
            this.width = input;
        }
    }

    public class DataPhoto{
        private String name;
        private String picture;
        private String id;

        public String getName(){
            return name;
        }
        public void setName(String input){
            this.name = input;
        }
        public String getPicture(){
            return picture;
        }
        public void setPicture(String input){
            this.picture = input;
        }
        public String getId(){
            return id;
        }
        public void setId(String input){
            this.id = input;
        }
    }
    public class Photos{
        private List<DataPhoto> data;

        public List<DataPhoto> getData(){
            return data;
        }
        public void setData(List<DataPhoto> input){
            this.data = input;
        }
    }
    public class DataAlbum{
        private String name;
        private Photos photos;
        private String id;

        public String getName(){
            return name;
        }
        public void setName(String input){
            this.name = input;
        }
        public Photos getPhotos(){
            return photos;
        }
        public void setPhotos(Photos input){
            this.photos = input;
        }
        public String getId(){
            return id;
        }
        public void setId(String input){
            this.id = input;
        }
    }
    public class Albums{
        private List<DataAlbum> data;


        public List<DataAlbum> getData(){
            return data;
        }
        public void setData(List<DataAlbum> input){
            this.data = input;
        }
    }
    public class DataPost{
        private String message;
        private String created_time;
        private String id;

        public String getMessage(){
            return message;
        }
        public void setMessage(String input){
            this.message = input;
        }
        public String getCreatedTime(){
            return created_time;
        }
        public void setCreatedTime(String input){
            this.created_time = input;
        }
        public String getId(){
            return id;
        }
        public void setId(String input){
            this.id = input;
        }
    }

    public class Posts{
        private List<DataPost> data;

        public List<DataPost> getData(){
            return data;
        }
        public void setData(List<DataPost> input){
            this.data = input;
        }
    }
}

