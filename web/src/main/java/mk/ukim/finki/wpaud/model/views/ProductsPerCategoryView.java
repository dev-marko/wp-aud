package mk.ukim.finki.wpaud.model.views;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@Subselect("SELECT * FROM public.products_per_category")
@Immutable
public class ProductsPerCategoryView {
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "num_products")
    private Integer numProducts;
}
