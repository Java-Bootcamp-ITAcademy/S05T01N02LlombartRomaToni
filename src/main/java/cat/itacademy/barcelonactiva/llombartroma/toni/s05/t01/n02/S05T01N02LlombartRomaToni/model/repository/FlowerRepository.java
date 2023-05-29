package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.repository;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<Flower,Integer> {

}
