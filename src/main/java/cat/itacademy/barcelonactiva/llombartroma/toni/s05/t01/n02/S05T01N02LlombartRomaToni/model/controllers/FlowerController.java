package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.controllers;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.domain.Flower;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.services.FlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @Operation(
            summary = "Add a flower",
            tags = { "Adding flower" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower added successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @PostMapping(UrlString.ADD_URL)
    public ResponseEntity<FlowerDTO> add( @RequestBody Flower flower) {
        try {
            Optional<FlowerDTO> optionalFlower = flowerService.add(flower);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.CREATED);
            } else {
                throw new FlowerNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Update a flower",
            tags = { "Updating flowers" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower updated successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @PutMapping(UrlString.UPDATE_URL)
    public ResponseEntity<FlowerDTO> update(@RequestBody Flower flower, @PathVariable int id) {
        try {
            Optional<FlowerDTO> optionalFlower = flowerService.update(flower, id);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.CREATED);
            } else {
                throw new FlowerNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Delete a flower",
            tags = { "Removing flowers" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower deleted successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @DeleteMapping(UrlString.DELETE_URL)
    public ResponseEntity<FlowerDTO> delete(@PathVariable int id) {
        try {
            Optional<FlowerDTO> optionalFlower = flowerService.delete(id);
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Retrieve a flower",
            tags = { "Retrieving a single flower" }
    )
    @GetMapping(UrlString.GET_ONE_URL)
    public ResponseEntity<FlowerDTO> getOne(@PathVariable int id) {
        Optional<FlowerDTO> optionalFlower = flowerService.getOne(id);
        try {
            if (optionalFlower.isPresent()) {
                return new ResponseEntity<>(optionalFlower.get(), HttpStatus.OK);
            } else {
                throw new FlowerNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "Get all flowers",
            tags = { "Retrieving all flowers" }
    )
    @GetMapping(UrlString.GET_ALL_URL)
    public ResponseEntity<List<FlowerDTO>> getAll() {
        Optional<List<FlowerDTO>> optionalFlowers = flowerService.getAll();
        try {
            if (optionalFlowers.isPresent()) {
                return new ResponseEntity<>(optionalFlowers.get(), HttpStatus.OK);
            } else {
                throw new FlowerNotFoundException();
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    /* This inner class contains all endpoints strings. Static access */

    private static class UrlString {
        public static final String ADD_URL = "/flower/add";
        public static final String UPDATE_URL = "/flower/update/{id}";
        public static final String DELETE_URL = "/flower/delete/{id}";
        public static final String GET_ONE_URL = "/flower/getone/{id}";
        public static final String GET_ALL_URL = "/flower/getall";
    }
}
