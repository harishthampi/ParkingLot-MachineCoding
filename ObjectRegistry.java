import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {
    private Map<String,Object> objectMap = new HashMap<>();
    public void register(String name,Object object){
        objectMap.put(name,object);
    }
    public Object getObject(String name){
        return objectMap.get(name);
    }
}

/*We have to create instance of all the controller,service and repository for the client to generate a ticket from
main class.
How to do that? Here Spring boot helps us to achieve this
But here lets try without Spring Boot
Requirement - Initialize controller,service and repository,inject the dependencies and get it later.
Solution - Registry design
Object Registry
 */
