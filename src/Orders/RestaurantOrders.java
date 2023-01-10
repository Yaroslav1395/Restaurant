package Orders;

import com.google.gson.Gson;
import domain.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RestaurantOrders {
    private List<Order> orders = RestaurantOrders.read();

    private RestaurantOrders(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            orders = List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Order> read() {
        var ro = new RestaurantOrders("orders_1000");
        ro.getOrders().forEach(Order::calculateTotal);
        return ro.getOrders();
    }

    public List<Order> getOrders() {
        return orders;
    }

}
