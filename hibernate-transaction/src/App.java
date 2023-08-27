import java.util.ArrayList;
import java.util.List;

import dto.OrderDetailDto;
import dto.OrderDto;
import service.OrderService;

public class App {
    public static void main(String[] args) throws Exception {

        OrderService orderService = new OrderService();

        OrderDto dto = new OrderDto();
        dto.setId("D068");
        dto.setCustId("C013");

        List<OrderDetailDto> detailDtos = new ArrayList<>();
        detailDtos.add(new OrderDetailDto("P001", 10, 0.0));
        detailDtos.add(new OrderDetailDto("P010", 5, 0.0));

        dto.setDetailDtos(detailDtos);

        String resp = orderService.saveOrder(dto);

        System.out.println(resp);

    }
}