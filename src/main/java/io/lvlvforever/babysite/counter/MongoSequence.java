
package io.lvlvforever.babysite.counter;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MongoSequence {
    @Id
    private String id;
    private Long seq;

}
