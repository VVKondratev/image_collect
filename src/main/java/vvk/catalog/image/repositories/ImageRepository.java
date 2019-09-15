package vvk.catalog.image.repositories;

import org.springframework.data.repository.CrudRepository;
import vvk.catalog.image.model.Image;

public interface ImageRepository extends CrudRepository<Image, Integer> {

        }
