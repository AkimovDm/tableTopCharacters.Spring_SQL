package characterbase.rest.tableTopCharacters.controllers;

import characterbase.rest.tableTopCharacters.model.CharacterModel;
import characterbase.rest.tableTopCharacters.services.CharacterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharacterModelController {

    private final CharacterService characterService;

    @Autowired
    CharacterModelController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping(value = "/characters")
    public ResponseEntity<?> create(@RequestBody CharacterModel characterModel) {
        characterService.create(characterModel);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @PostMapping(value = "/save")
    public ResponseEntity<?> save(){
        return new ResponseEntity<>(new String("Вызов метода сохранения"),HttpStatus.OK);
    }

    @GetMapping(value = "/characters")
    public ResponseEntity<?> read() {
        final List<CharacterModel> characterModels = characterService.readAll();
        return ((characterModels != null) && (!characterModels.isEmpty()))
                ? new ResponseEntity<>(characterModels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/characters/{name}")
    public ResponseEntity<?> read(@PathVariable(name = "name") String name) {
        final CharacterModel characterModel = characterService.read(name);

        return (characterModel != null)
                ? new ResponseEntity<>(characterModel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> readByName(@RequestBody String name){
        final CharacterModel characterModel= characterService.read(name);
        return (characterModel!=null)
                ? new ResponseEntity<>(characterModel,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "characters/{name}")
    public ResponseEntity<?> update(@PathVariable(name = "name") String name, @RequestBody CharacterModel characterModel) {
        final boolean updated = characterService.update(characterModel, name);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping (value = "/characters/{name}")
public ResponseEntity<?> delete(@PathVariable(name="name") String name){
        final boolean deleted = characterService.delete(name);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
