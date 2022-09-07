package characterbase.rest.tableTopCharacters.model;

import java.util.HashMap;
import java.util.List;

public class CharacterModel {


    private Integer id; //порядковый номер для ДБ
    private String name; //Имя персонажа
    private String system; // Название системы (DnD5E, PF2, ect.)
    private String race; //Раса персонажа
    private String companyType; // Тип компании (Adventure League, Pathfinder Society, DnD official campaign, HomeBrew... ect)
    private String companyName; // Название компании.
    private boolean gotMultiClass; // Есть ли мультиклас у персонажа
    private HashMap<String, String> classAndLvl; // map классов и лвлов в них.
    private List<String> feats; // список фитов персонажа, возможно сделать класс.
    private Inventory inventory; // инвентарь персонажа


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isGotMultiClass() {
        return gotMultiClass;
    }

    public void setGotMultiClass(boolean gotMultiClass) {
        this.gotMultiClass = gotMultiClass;
    }

    public HashMap<String, String> getClassAndLvl() {
        return classAndLvl;
    }

    public void setClassAndLvl(HashMap<String, String> classAndLvl) {
        this.classAndLvl = classAndLvl;
    }

    public List<String> getFeats() {
        return feats;
    }

    public void setFeats(List<String> feats) {
        this.feats = feats;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
