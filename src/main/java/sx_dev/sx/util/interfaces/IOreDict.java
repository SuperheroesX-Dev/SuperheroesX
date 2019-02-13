package sx_dev.sx.util.interfaces;


public interface IOreDict<T> {
    T setOreDictName(String oreDictName);

    String getOreDictName();

    default boolean hasOreDictName() {
        return false;
    }

    T getEntry();
}