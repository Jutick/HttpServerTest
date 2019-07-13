package postres.bd;

public class Breed
{
    private String id;
    private String name;

    public Breed() {}

    public Breed(String name)
    {
        this.name = name;
    }

    public Breed(String id, String name)
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
