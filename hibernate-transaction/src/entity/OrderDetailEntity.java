package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "OrderDetail_v2")
public class OrderDetailEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ItemCode", nullable = false)
    private ItemEntity itemEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderID", nullable = false)
    private OrderEntity orderEntity;

    @Column(name = "OrderQTY", nullable = false)
    private Integer qty;

    @Column(name = "Discount")
    private Double discount;
}