package dev.wayne.contacts.controller;

import dev.wayne.contacts.entity.Client;
import dev.wayne.contacts.util.PageableUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "clients")
public interface ClientController {

    @Operation(
            summary = "Добавление нового клиента",
            description = "Добавление нового клиента."
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST, description = "Имя клиента не задано"),
    })
    Client create(@Parameter(description = "Данные клиента", required = true)
                  @RequestBody Client client);

    @Operation(
            summary = "Получение списка клиентов",
            description = "Получение списка всех клиентов. Можно использовать параметры пагинации"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(type = "array", implementation = Client.class)))),
    })
    List<Client> findAll(
            @Parameter(description = "Номер страницы")
            @RequestParam(value = "pageNum", required = false) @Min(value = 0, message = "Неверный номер страницы") Integer pageNum,
            @Parameter(description = "Размер страницы")
            @RequestParam(value = "pageSize", required = false) @Min(value = 1, message = "Неверный размер страницы") Integer pageSize,
            @Parameter(description = "Поле сортировки")
            @RequestParam(value = "sortField", required = false) String sortField,
            @Parameter(description = "Направление сортировки (по умолчанию ASC)")
            @RequestParam(value = "sortDirection", required = false) PageableUtil.SortDirection sortDirection);

    @Operation(
            summary = "Получение информации о конкретном клиенте по ID.",
            description = "Получение информации о конкретном клиенте по ID."
    )
    @GetMapping(path = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Client.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Клиент не найден"),
    })
    Client findById(
            @Parameter(description = "Идентификатор клиента", required = true)
            @PathVariable("id") Long id);
}
