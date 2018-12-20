package ivosdc.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DemoRepository extends CrudRepository<Demo, Long> {
    Boolean existsByName(String name);

    Optional<Demo> findByName(String name);
}
