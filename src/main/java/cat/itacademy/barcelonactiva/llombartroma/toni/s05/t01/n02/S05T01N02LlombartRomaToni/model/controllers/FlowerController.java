package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.controllers;

import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.domain.Flower;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.services.FlowerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FlowerController {
    private final FlowerService flowerService;
    private final UrlString urlString;

    public FlowerController(FlowerService flowerService, UrlString urlString) {
        this.flowerService = flowerService;
        this.urlString = urlString;
    }

    @Operation(
            summary = "Add a flower",
            tags = { "Adding flower" }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flower added successfully"),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    @PostMapping(UrlString.addUrl)
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
    @PutMapping(UrlString.updateUrl)
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
    @DeleteMapping(UrlString.deleteUrl)
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
    @GetMapping(UrlString.getOneUrl)
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
    @GetMapping(UrlString.getAllUrl)
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
    @Component
    public static class UrlString {
        public static final String addUrl = "/flower/add";
        public static final String updateUrl = "/flower/update/{id}";
        public static final String deleteUrl = "/flower/delete/{id}";
        public static final String getOneUrl = "/flower/getOne/{id}";
        public static final String getAllUrl = "/flower/getAll";
    }
}
