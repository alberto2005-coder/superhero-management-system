package com.example.jwt2.services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jwt2.entities.Heroe;
import com.example.jwt2.entities.Poder;
import com.example.jwt2.repositories.HeroeRepository;
import com.example.jwt2.repositories.PoderRepository;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;

@Service
public class HeroeService {

    @Autowired
    private HeroeRepository heroeRepository;

    @Autowired
    private PoderRepository poderRepository;

    @PersistenceContext
    private EntityManager em;

    public List<Heroe> findAll() {
        return heroeRepository.findAll();
    }

    public Optional<Heroe> findById(@org.springframework.lang.NonNull Long id) {
        return heroeRepository.findById(id);
    }

    public List<Heroe> findByUniverso(String universo) {
        return heroeRepository.findByUniverso(universo);
    }

    public List<Heroe> findByNombreStartingWith(String prefijo) {
        return heroeRepository.findByNombreStartingWith(prefijo);
    }

    public List<Heroe> buscarHeroesConCriteria(
            String terminoGeneral,
            String universo,
            LocalDate altaDespuesDe,
            String ordenarPor,
            String ordenDir) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Heroe> cq = cb.createQuery(Heroe.class);

        Root<Heroe> heroeRoot = cq.from(Heroe.class);

        List<Predicate> predicados = new ArrayList<>();

        if (terminoGeneral != null && !terminoGeneral.isEmpty()) {

            Predicate likeNombre = cb.like(heroeRoot.get("nombre"), "%" + terminoGeneral + "%");
            Predicate likeBase = cb.like(heroeRoot.get("base"), "%" + terminoGeneral + "%");
            predicados.add(cb.or(likeNombre, likeBase));
        }

        if (universo != null && !universo.isEmpty()) {

            predicados.add(cb.equal(heroeRoot.get("universo"), universo));
        }

        if (altaDespuesDe != null) {
            predicados.add(cb.greaterThan(heroeRoot.get("fechaDeAlta"), altaDespuesDe));
        }

        cq.where(cb.and(predicados.toArray(new Predicate[0])));

        if (ordenarPor != null && !ordenarPor.isEmpty()) {
            if ("desc".equalsIgnoreCase(ordenDir)) {
                cq.orderBy(cb.desc(heroeRoot.get(ordenarPor)));
            } else {
                cq.orderBy(cb.asc(heroeRoot.get(ordenarPor)));
            }
        }

        TypedQuery<Heroe> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Transactional
    public int updateBaseByUniverso(String universo, String nuevaBase) {
        return heroeRepository.updateBaseByUniverso(universo, nuevaBase);
    }

    @Transactional
    public Heroe save(@org.springframework.lang.NonNull Heroe heroe) {
        return heroeRepository.save(heroe);
    }

    @Transactional
    public Optional<Heroe> update(@org.springframework.lang.NonNull Long id,
            @org.springframework.lang.NonNull Heroe heroeDetails) {
        return heroeRepository.findById(id)
                .map(heroeExistente -> {
                    heroeExistente.setNombre(heroeDetails.getNombre());
                    heroeExistente.setUniverso(heroeDetails.getUniverso());
                    heroeExistente.setBase(heroeDetails.getBase());
                    return heroeRepository.save(heroeExistente);
                });
    }

    @Transactional
    public boolean deleteById(@org.springframework.lang.NonNull Long id) {
        if (heroeRepository.existsById(id)) {
            heroeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Heroe> addPoderToHeroe(@org.springframework.lang.NonNull Long heroeId,
            @org.springframework.lang.NonNull Long poderId) {
        Optional<Heroe> heroeOpt = heroeRepository.findById(heroeId);
        Optional<Poder> poderOpt = poderRepository.findById(poderId);

        if (heroeOpt.isPresent() && poderOpt.isPresent()) {
            Heroe heroe = heroeOpt.get();
            Poder poder = poderOpt.get();
            heroe.addPoder(poder);
            return Optional.of(heroeRepository.save(heroe));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Heroe> removePoderFromHeroe(@org.springframework.lang.NonNull Long heroeId,
            @org.springframework.lang.NonNull Long poderId) {
        Optional<Heroe> heroeOpt = heroeRepository.findById(heroeId);
        Optional<Poder> poderOpt = poderRepository.findById(poderId);

        if (heroeOpt.isPresent() && poderOpt.isPresent()) {
            Heroe heroe = heroeOpt.get();
            Poder poder = poderOpt.get();
            heroe.removePoder(poder);
            return Optional.of(heroeRepository.save(heroe));
        }
        return Optional.empty();
    }
}