package entity;


public class Pet {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String breed;
    private String color;
    private String bodyType;
    private double weight;
    private String region;


    public Pet(int id,
               String name,
               int age,
               String gender,
               String breed,
               String color,
               String bodyType,
               double weight,
               String region) {

        if (id < 0) {
            throw new IllegalArgumentException("Pet id must be non-negative.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Pet name must not be null or blank.");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Pet age must be non-negative.");
        }
        if (weight < 0.0) {
            throw new IllegalArgumentException("Pet weight must be non-negative.");
        }

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.color = color;
        this.bodyType = bodyType;
        this.weight = weight;
        this.region = region;
    }

    // ---------- Getters ----------

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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

    public String getBodyType() {
        return bodyType;
    }

    public double getWeight() {
        return weight;
    }

    public String getRegion() {
        return region;
    }

    // ---------- Setters (if you want to allow updates) ----------

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Pet id must be non-negative.");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Pet name must not be null or blank.");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Pet age must be non-negative.");
        }
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setWeight(double weight) {
        if (weight < 0.0) {
            throw new IllegalArgumentException("Pet weight must be non-negative.");
        }
        this.weight = weight;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
