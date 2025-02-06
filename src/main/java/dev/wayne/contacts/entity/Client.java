package dev.wayne.contacts.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(description = "Клиент")
@Table(schema = "main", name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "name")
    @Schema(description = "Имя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
