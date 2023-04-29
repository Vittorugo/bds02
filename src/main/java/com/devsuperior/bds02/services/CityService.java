package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DataBaseException;
import com.devsuperior.bds02.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDTO> findAll() {
        var result = cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        return result.stream().map(CityDTO::new).collect(Collectors.toList());
    }


    public CityDTO create(CityDTO cityDTO) {
        var city = cityRepository.save(mapperToDomain(cityDTO));
        return new CityDTO(city);
    }

    private City mapperToDomain(CityDTO dto) {
        var city = new City();
        city.setName(dto.getName());
        return city;
    }

    public void deleteById(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("City Id not found");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity Violation");
        }
    }
}
