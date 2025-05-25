package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {

  private Long id;
  private CategoryDTO category;
  private String name;
  private List<String> photoUrls;
  private List<TagDTO> tags;
  private String status;


}
