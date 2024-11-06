package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private static final Comparator<String> BY_DAYS = new SortByDays();
    private static final Comparator<String> BY_ORDER = new SortByMonthOrder();

    
    @Override
    public Comparator<String> sortByDays() {
        return BY_DAYS;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return BY_ORDER;
    }


    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        public Integer getDays() {
            return this.days;
        }

        public static Month fromString(final String m) {
            Month match = null;
            for (final Month mo : Month.values()) {
                if (mo.toString().toLowerCase().startsWith(m.toLowerCase())) {
                    if (match != null) {
                        throw new IllegalArgumentException(m + "matches to multiple names");
                    }
                    match = mo;
                }
            }

            if (match == null) {
                throw new IllegalArgumentException(m + "doesn't match with anything");
            }
            return match;
        }
    }

    
    private static final class SortByDays implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            final Month m1 = Month.fromString(o1);
            final Month m2 = Month.fromString(o2);
            return Integer.compare(m1.getDays(), m2.getDays());
        }

    }

    private static final class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(final String o1, final String o2) {
            return Month.fromString(o1).compareTo(Month.fromString(o2));
        }

    }
}
