package master.ccm.rpgandroidproject.Entity;

public class ItemFactory {
    public Item fabriqueMoiUnItem ( String type ) {
        Item monItem = null;
        switch (type) {
            case "Arme":
                monItem = new ItemArme();
                break;
            case "Armure":
                monItem = new ItemArmure();
                break;
            case "Objet":
                monItem = new ItemObjet();
                break;

        }
        return monItem;
    }
}
