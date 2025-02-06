package dev.wayne.contacts.controller;

import dev.wayne.contacts.entity.Contact;
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
@RequestMapping("/contacts")
@Tag(name = "contacts")
public interface ContactController {

    @Operation(
            summary = "Добавление нового контакта клиента",
            description = "Добавление нового контакта клиента."
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Contact.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Тип контакта не задан
                            * Идентификатор клиента не задан
                            * Указанный тип контакта {type} не предусмотрен системой
                            * Данные контакта не заданы
                            * Номер телефона не задан
                            * Неверный формат телефона"""),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Клиент не найден"),
    })
    Contact create(@Parameter(description = "Данные контакта", required = true)
                   @RequestBody Contact contact);

    @Operation(
            summary = "Получение списка контактов клиента",
            description = "Получение списка контактов клиента. Можно использовать параметры пагинации"
    )
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(type = "array", implementation = Contact.class)))),
    })
    List<Contact> findAll(
            @Parameter(description = "Идентификатор клиента")
            @RequestParam(value = "clientId") Long clientId,
            @Parameter(description = "Тип контакта")
            @RequestParam(value = "type", required = false) String type,
            @Parameter(description = "Номер страницы")
            @RequestParam(value = "pageNum", required = false) @Min(value = 0, message = "Неверный номер страницы") Integer pageNum,
            @Parameter(description = "Размер страницы")
            @RequestParam(value = "pageSize", required = false) @Min(value = 1, message = "Неверный размер страницы") Integer pageSize,
            @Parameter(description = "Поле сортировки")
            @RequestParam(value = "sortField", required = false) String sortField,
            @Parameter(description = "Направление сортировки (по умолчанию ASC)")
            @RequestParam(value = "sortDirection", required = false) PageableUtil.SortDirection sortDirection);
}
