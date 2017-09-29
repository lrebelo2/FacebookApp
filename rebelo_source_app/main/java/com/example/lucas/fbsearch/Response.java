package com.example.lucas.fbsearch;

import java.util.List;

/**
 * Created by lucas on 21-Apr-17.
 */


public class Response {
    private List<Data> data;
    private Paging paging;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> input) {
        this.data = input;
    }

    public Paging getPaging() {
        return paging;
    }
    public int size(){
        return data.size();
    }
    public void setPaging(Paging input) {
        this.paging = input;
    }

    public class Paging {
        private Cursors cursors;
        private String next;
        private String previous;

        public Cursors getCursors() {
            return cursors;
        }

        public void setCursors(Cursors input) {
            this.cursors = input;
        }

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setNext(String input) {
            this.next = input;
        }

        public void setPrevious(String input) {
            this.previous = input;
        }
    }

    public class Cursors {
        private String after;

        public String getAfter() {
            return after;
        }

        public void setAfter(String input) {
            this.after = input;
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

    public class Picture {
        private PicData data;

        public PicData getData() {
            return data;
        }

        public void setData(PicData input) {
            this.data = input;
        }
    }

    public class Data {
        private String id;
        private String name;
        private Picture picture;

        public String getId() {
            return id;
        }

        public void setId(String input) {
            this.id = input;
        }

        public String getName() {
            return name;
        }

        public void setName(String input) {
            this.name = input;
        }

        public Picture getPicture() {
            return picture;
        }

        public void setPicture(Picture input) {
            this.picture = input;
        }
    }
}
