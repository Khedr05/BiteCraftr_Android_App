package com.example.bitecraftr.Model;

public class Ingredients {

    // Name of the ingredient
    private String strIngredient;

    // Measurement of the ingredient (e.g., "1 cup", "200g")
    private String ingredientsMeasure;

    // URL or path for the ingredient's thumbnail image
    private String ingredientsThumbnail;

    private String idIngredient;
    private String strDescription;

    /**
     * Constructor to initialize an ingredient with its name, measurement, and thumbnail.
     *
     * @param strIngredient      Name of the ingredient
     * @param ingredientsMeasure   Measurement of the ingredient
     * @param ingredientsThumbnail URL or path of the ingredient's thumbnail image
     */
    public Ingredients(String strIngredient, String ingredientsMeasure, String ingredientsThumbnail, String idIngredient, String strDescription) {
        this.strIngredient = strIngredient;
        this.ingredientsMeasure = ingredientsMeasure;
        this.ingredientsThumbnail = ingredientsThumbnail;
        this.idIngredient = idIngredient;
        this.strDescription = strDescription;
    }

    /**
     * Gets the name of the ingredient.
     *
     * @return Ingredient name as a String
     */
    public String getStrIngredient() {
        return strIngredient;
    }

    /**
     * Sets the name of the ingredient.
     *
     * @param strIngredient Name of the ingredient
     */
    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    /**
     * Gets the measurement of the ingredient.
     *
     * @return Ingredient measurement as a String
     */
    public String getIngredientsMeasure() {
        return ingredientsMeasure;
    }

    /**
     * Sets the measurement of the ingredient.
     *
     * @param ingredientsMeasure Measurement of the ingredient
     */
    public void setIngredientsMeasure(String ingredientsMeasure) {
        this.ingredientsMeasure = ingredientsMeasure;
    }

    /**
     * Gets the thumbnail URL or path of the ingredient.
     *
     * @return Ingredient thumbnail as a String
     */
    public String getIngredientsThumbnail() {
        return ingredientsThumbnail;
    }

    /**
     * Sets the thumbnail URL or path of the ingredient.
     *
     * @param ingredientsThumbnail URL or path of the ingredient's thumbnail image
     */
    public void setIngredientsThumbnail(String ingredientsThumbnail) {
        this.ingredientsThumbnail = ingredientsThumbnail;
    }

    public String getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }
}

