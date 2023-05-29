package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.services;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.domain.Flower;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.repository.FlowerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;
    /* Implements how API add a new Flower */
    public Optional<FlowerDTO> add(Flower flower) {
        return Optional.of(fromFlowerToFlowerDTO(flowerRepository.save(flower)));
    }
    /* Implements how API updates a Flower */
    public Optional<FlowerDTO> update(Flower flower, int id) {
        Optional<Flower> optionalFlower = flowerRepository.findById(id);
        if(optionalFlower.isPresent()) {
            Flower updatedFlower = optionalFlower.get();
            updatedFlower.setName(flower.getName());
            updatedFlower.setCountry(flower.getCountry());
            return Optional.of(fromFlowerToFlowerDTO(flowerRepository.save(updatedFlower)));
        }
        else {
            return Optional.empty();
        }
    }
    /* Implements how API removes a Flower by its id*/
    public Optional<FlowerDTO> delete(int id) {
        Optional<Flower> optionalFlower = flowerRepository.findById(id);
        flowerRepository.deleteById(id);
        return Optional.of(fromFlowerToFlowerDTO(optionalFlower.get()));
    }
    /* Implements how API retrieves a single Flower by its id*/
    public Optional<FlowerDTO> getOne(int id) {
        return Optional.of(fromFlowerToFlowerDTO(flowerRepository.findById(id).get()));
    }
    /* Implements how API retrieves all Flowers */
    public Optional<List<FlowerDTO>> getAll() {
        List<FlowerDTO> flowersDTO = flowerRepository.findAll().stream().map(this::fromFlowerToFlowerDTO).collect(Collectors.toList());
        return Optional.of(flowersDTO);
    }
    /* Converts from Flower to FlowerDTO */
    private FlowerDTO fromFlowerToFlowerDTO(Flower flower) {
        ModelMapper modelMapper = new ModelMapper();
        FlowerDTO flowerDTO = modelMapper.map(flower, FlowerDTO.class);
        flowerDTO.setFlowerType();
        return flowerDTO;
    }
    /* Converts from FlowerDTO to Flower */
    private Flower fromFlowerDTOToFlower(FlowerDTO flowerDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Flower flower = modelMapper.map(flowerDTO, Flower.class);
        return flower;
    }
}
