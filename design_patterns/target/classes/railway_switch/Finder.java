package railway_switch;

import java.util.Optional;

/**
 * Finder class can enable us to maintain multi chain checking the same tye value
 * from different sources
 *
 * @param <T>
 * @author uday.mekala
 */
public interface Finder<T> {

    /**
     * @return {@code Optional<T>}
     */
    Optional<T> find();

    /**
     * It gives another {@code Finder} combining with present {@code Finder}
     *
     * @param finderOfAnyType
     * @return {@code Finder}
     */
    default Finder<T> orElse(final Finder<T> finderOfAnyType) {
        return () -> this.find().or(finderOfAnyType::find);
    }

}
