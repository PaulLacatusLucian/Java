import java.util.ArrayList;
import java.util.UUID;

public class Controller<ObjectType> {
    private Repository<ObjectType> repository;

    public Controller(Repository<ObjectType> repository) {
        this.repository = repository;
    }

    public void createObject(ObjectType obj) {
        repository.createObject(obj);

    }

    public ArrayList<ObjectType> readAll() {
        return repository.readAll();
    }

    public void updateObject(ObjectType updatedObj, UUID identifier) {
        repository.updateObject(updatedObj, identifier);
    }

    public ObjectType deleteObject(UUID identifier) {
        return repository.deleteObject(identifier);
    }



}