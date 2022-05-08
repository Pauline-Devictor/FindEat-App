package etu.ihm.myactivity.restaurants;

import java.util.Comparator;

import etu.ihm.myactivity.factoryTests.Lieux;

public class LieuxComparator implements Comparator<Lieux> {
    @Override
    public int compare(Lieux l1, Lieux l2) {
        if (l1.getDistance() > l2.getDistance())
            return 1;
        if (l1.getDistance() < l2.getDistance())
            return -1;
        return 0;
    }
}
