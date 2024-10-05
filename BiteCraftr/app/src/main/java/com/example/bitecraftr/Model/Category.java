package com.example.bitecraftr.Model;

public class Category {
    // Unique identifier for the category
    private String idCategory;

    // Name of the category
    private String strCategory;

    // URL for the category thumbnail image
    private String strCategoryThumb;

    // Description of the category
    private String strCategoryDescription;

    // Getter for idCategory
    public String getIdCategory() {
        return idCategory;
    }

    // Setter for idCategory
    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    // Getter for strCategory
    public String getStrCategory() {
        return strCategory;
    }

    // Setter for strCategory
    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    // Getter for strCategoryThumb
    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    // Setter for strCategoryThumb
    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }

    // Getter for strCategoryDescription
    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    // Setter for strCategoryDescription
    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }
}

