package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPetResponseDTO {
  private Long id;
  private CategoryDTO category;
  private String name;
  private List<String> photoUrls;
  private List<TagDTO> tags;
  private String status;

}
