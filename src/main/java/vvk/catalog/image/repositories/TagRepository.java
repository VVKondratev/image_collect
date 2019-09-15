package vvk.catalog.image.repositories;

import org.springframework.data.repository.CrudRepository;
import vvk.catalog.image.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer> {

}
