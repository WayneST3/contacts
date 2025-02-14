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
@Schema(description = "Контакт")
@Table(schema = "main", name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Идентификатор записи", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name = "type")
    @Schema(description = "Тип", requiredMode = Schema.RequiredMode.REQUIRED, implementation = ContactType.class)
    private String type;

    @Column(name = "value")
    @Schema(description = "Значение", requiredMode = Schema.RequiredMode.REQUIRED)
    private String value;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "client_id")
    @Schema(description = "Идентификатор клиента", requiredMode = Schema.RequiredMode.REQUIRED, accessMode = Schema.AccessMode.WRITE_ONLY)
    private Long clientId;

//    @JsonIgnore
//    @JoinColumn(name = "client_id")
//    @Schema(description = "Клиент", requiredMode = Schema.RequiredMode.REQUIRED)
//    @ManyToOne(targetEntity = Client.class, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
//    private Client client;

    public enum ContactType {
        phone, mail
    }
}
