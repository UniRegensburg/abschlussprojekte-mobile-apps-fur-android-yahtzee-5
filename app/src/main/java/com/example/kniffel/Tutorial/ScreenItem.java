package com.example.kniffel.Tutorial;

public class ScreenItem {
    int screenTitle, screenDescription, screenImage;

    public ScreenItem(int title, int description, int screenImage){
        screenTitle = title;
        screenDescription = description;
        this.screenImage = screenImage;

    }
    public void setTitle(int title){
        screenTitle = title;
    }

    public int getTitle() {
        return screenTitle;
    }

    public int getDescription(){
        return screenDescription;
    }

    public int getScreenImage(){
        return screenImage;
    }

}
