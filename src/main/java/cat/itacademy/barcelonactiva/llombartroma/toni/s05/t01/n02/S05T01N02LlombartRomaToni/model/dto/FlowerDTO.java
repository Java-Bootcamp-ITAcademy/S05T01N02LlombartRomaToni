package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n02.S05T01N02LlombartRomaToni.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;

@Data
public class FlowerDTO {

    private static final List<String> euCountries = Arrays.asList(
            "Austria",
            "Belgium",
            "Bulgaria",
            "Croatia",
            "Republic of Cyprus",
            "Czech Republic",
            "Denmark",
            "Estonia",
            "Finland",
            "France",
            "Germany",
            "Greece",
            "Hungary",
            "Ireland",
            "Italy",
            "Latvia",
            "Lithuania",
            "Luxembourg",
            "Malta",
            "Netherlands",
            "Poland",
            "Portugal",
            "Romania",
            "Slovakia",
            "Slovenia",
            "Spain",
            "Sweden"
    );
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private FlowerType flowerType;

    public void setFlowerType() {
        if(euCountries.contains(this.country)) {
            this.flowerType = FlowerType.UE;
        }
        else {
            this.flowerType = FlowerType.NON_UE;
        }
    }
}

