package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import ru.nsu.nsustudyhelper.entity.EntryYear;

import java.util.Optional;

public interface EntryYearRepository extends CrudRepository<EntryYear, Integer> {
    Optional<EntryYear> findByEntryYear(int entryYear);

    Iterable<EntryYear> findAllByOrderByEntryYearAsc();
}
