package com.example.finalappsaumit;

public class RecipeInfo {

    String title;
    String readyMins;
    String summary;
    String image;
    String servings;

    public RecipeInfo(String title, String readyMins, String summary, String image, String servings) {
        this.title = title;
        this.readyMins = readyMins;
        this.summary = summary;
        this.image = image;
        this.servings = servings;
    }

    public String getTitle() {

        return title;
    }

    public String getReadyMins() {

        return readyMins;
    }

    public String getSummary() {

        return summary;
    }
    public String getImage() {

        return image;
    }
    public String getServings() {

        return servings;
    }
}
