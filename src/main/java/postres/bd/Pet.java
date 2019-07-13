package postres.bd;

public class Pet
{
    private String id;
    private String name;
    private Breed breed;
    private Food food;

    public Pet(){}

    public  Pet(String id, String name, Breed breed, Food food)
    {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.food = food;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Breed getBreed() {
        return breed;
    }

    public Food getFood() {
        return food;
    }
}
