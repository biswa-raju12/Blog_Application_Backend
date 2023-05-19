package com.Applicatoin_Blog_demo.Payroads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long categoryId;

    @NotEmpty
    @Size(min = 10,max = 50,message = "CategoryName length should be between 10 to 50")
    private String categoryName;

    @NotEmpty
    @Size(min = 10,max = 100,message = "CategoryDescription length should be between 10 to 100")
    private String categoryDescription;
}
