package dev.wayne.contacts.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

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
    @Schema(description = "Идентификатор записи")
    private UUID id;

    @Column(name = "name")
    @Schema(description = "Имя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}
