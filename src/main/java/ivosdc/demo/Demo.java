package ivosdc.demo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Demo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("The unique ID")
    private long id;

    @Setter
    @ApiModelProperty("The name")
    private String name;

    @Setter
    @ApiModelProperty("A short description")
    private String description;

    public Demo(@NonNull String name, String description) {
        this.name = name;
        this.description = description;
    }
}
