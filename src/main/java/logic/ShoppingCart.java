package logic;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static final double[] DISCOUNT = {0.95, 0.9, 0.8, 0.75};

    public double price(int[] books) {
        double result = 0;
        List<List<Integer>> sortedShoppingCart;

        if (books.length == 1) {
            result = 8;
        } else if (books.length > 1) {
           sortedShoppingCart = rearrangeShoppingCart(books);

            for (int i = 0; i < sortedShoppingCart.size(); i++) {
                if (sortedShoppingCart.get(i).size() == 1) {
                    result += 8;
                } else {
                    result += 8 * sortedShoppingCart.get(i).size() * DISCOUNT[sortedShoppingCart.get(i).size()-2];
                }
            }
        }
        return result;
    }

    public List<List<Integer>> rearrangeShoppingCart(int[] books) {
        List<List<Integer>> sortedShoppingCart = new ArrayList<List<Integer>>();
        List<Integer> booksDummy = new ArrayList(books.length);
        for (int element: books) {
            booksDummy.add(element);
        }

        List<Integer> subList = new ArrayList(booksDummy.size());
        while(booksDummy.size() > 0) {
            subList.clear();
            subList.add(booksDummy.get(0));
            booksDummy.remove(0);
            for (int j = 0; j < booksDummy.size(); j++) {
                if (!subList.contains(booksDummy.get(j))) {
                    subList.add(booksDummy.get(j));
                    booksDummy.remove(j);
                    j--;
                }
            }
            sortedShoppingCart.add(new ArrayList<Integer>(subList));
        }
        return sortedShoppingCart;
    }
}
