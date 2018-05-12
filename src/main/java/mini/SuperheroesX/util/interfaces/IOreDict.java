package mini.SuperheroesX.util.interfaces;


public interface IOreDict<T> {
    T setOreDictName(String oreDictName);

    String getOreDictName();

    default boolean hasOreDictName() {
        return false;
    }

    T getEntry();
}