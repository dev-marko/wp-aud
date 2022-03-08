package mk.ukim.finki.wpaud.repository.jpa;

import mk.ukim.finki.wpaud.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Pagination ni se korisni za da go ogranicime brojot na objekti sho gi vleceme od bazata vo daden moment
// ova ni pomaga za podobri performansi i drugi raboti

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Page<Discount> findAll(Pageable pageable);
}
