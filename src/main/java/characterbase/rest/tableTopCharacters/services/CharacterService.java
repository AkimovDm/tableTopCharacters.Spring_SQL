package characterbase.rest.tableTopCharacters.services;

import characterbase.rest.tableTopCharacters.model.CharacterModel;

import java.util.List;

public interface CharacterService {

    /**
     *Создание нового персонажа
     * @param characterModel - новый персонаж
     */
    void create(CharacterModel characterModel);

    /**
     * Возврат списка персонажей
     * @return - список всех имеющихся персонажей
     */
    List<CharacterModel> readAll();

    /**
     * Данные персонажа
     * @param name - Имя запрашиваемого персонажа
     * @return - персонаж под данным ID
     */
    CharacterModel read(String name);



    /**
     * Обновление данных персонажа
     * @param characterModel - новые данные персонажа
     * @param name - Имя персонажа
     * @return true если данные успешно обновлены, иначе false
     */
    boolean update(CharacterModel characterModel, String name);

    /**
     * Удалить персонажа из базы
     * @param name - Имя персонажа, которого нужно удалить
     * @return - true, если персонаж успешно удалён из базы, иначе false
     */
    boolean delete(String name);


}
