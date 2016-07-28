import com.nrdc.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by localadmin on 7/28/16.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Stock> stocks = new ArrayList<>();
        Stock s1 = new Stock("ALL", 68.0f, 100);
        Stock s2 = new Stock("GOOG", 738.0f, 50);
        Stock s3 = new Stock("AAPL", 98.0f, 25);
        stocks.add(s1);
        stocks.add(s2);
        stocks.add(s3);

        // need to use parameterized types for ArrayList hence Float and not float primitive type
        Float[] amounts1 =
                stocks.stream().map(stock -> stock.getShares() * stock.getPrice())
                        .toArray(size -> new Float[size]);

        List<Float> amounts2 =
                stocks.stream().map(stock -> stock.getShares() * stock.getPrice())
                        .collect(Collectors.toList());

        ArrayList<Float> amounts =
                stocks.stream().map(stock -> stock.getShares() * stock.getPrice())
                        .collect(Collectors.toCollection(ArrayList::new));

        Stock[] stocks2 = stocks.stream().filter( stock -> stock.getSymbol() == "ALL" )
                .toArray(Stock[]::new);

        float position = stocks.stream().parallel().reduce(0f, (acc, stock) -> {
            System.out.println("stock: " + stock);
            return acc + (stock.getPrice() * stock.getShares());
        }, (total, value) -> {
            System.out.println("total: " + total + " value: " + value);
            return total + value;
        });

        System.out.println("s1:" + s1);

    }
}
