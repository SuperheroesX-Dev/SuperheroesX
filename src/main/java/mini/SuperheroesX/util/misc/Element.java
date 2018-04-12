package mini.SuperheroesX.util.misc;

public class Element<T> {

    private T obj;

    public Element() {
        this.obj = null;
    }

    public T getObj() {
        return this.obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
