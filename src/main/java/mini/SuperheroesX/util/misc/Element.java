package mini.SuperheroesX.util.misc;

public class Element<O> {

    private O obj;

    public Element() {
        this.obj = null;
    }

    public O getObj() {
        return this.obj;
    }

    public void setObj(O obj) {
        this.obj = obj;
    }

    public boolean test() {
        return obj == null;
    }
}
