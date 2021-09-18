package concurrent.port3;

public class Pier {
     private static int generator = 1;
     private int id;

    public Pier() {
        this.id = generator++;
    }

    @Override
    public String toString() {
        return "Pier{" +
                "id=" + id +
                '}';
    }
}
