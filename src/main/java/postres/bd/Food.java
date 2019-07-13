package postres.bd;

public class Food
{
    private String id;
    private String name;

    public Food() {}

    public Food(String name)
    {
        this.name = name;
    }

    public Food (String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
