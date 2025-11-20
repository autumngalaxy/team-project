package entity;

public class Pet {
    private final int id;
    private final String name;
    private final String species;
    private final int ageYears;
    private final String gender;
    private final String breed;
    private final String color;
    private final String description;

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private final Size size;

    private boolean isAdopted;

    public Pet(int id, String name, String species, int ageYears, String gender, String breed, String color, String description, Size size) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.ageYears = ageYears;
        this.gender = gender;
        this.breed = breed;
        this.color = color;
        this.description = description;
        this.size = size;
        this.isAdopted = false;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return ageYears;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return size;
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public void setAdopted() {
        isAdopted = true;
    }
}
