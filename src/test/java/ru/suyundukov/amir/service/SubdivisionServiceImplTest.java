package ru.suyundukov.amir.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.suyundukov.amir.app.impl.SubdivisionServiceImpl;
import ru.suyundukov.amir.entity.Subdivision;
import ru.suyundukov.amir.exception.ApplicationException;
import ru.suyundukov.amir.adapter.repository.SubdivisionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubdivisionServiceImplTest {

    @Mock(lenient = true)
    private SubdivisionRepository subdivisionRepository;

    @InjectMocks
    private SubdivisionServiceImpl subdivisionServiceImpl;

    private Subdivision subdivision;

    @BeforeEach
    void setUp() {
        subdivision = new Subdivision();
        subdivision.setId(1L);
        subdivision.setName("Nike");
        subdivision.setContactDetails("Contract#1");

        when(subdivisionRepository.save(subdivision)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    @Test
    public void testCreateEmployee() {
        Subdivision createSubdivision = subdivisionServiceImpl.createSubdivision(subdivision);

        assertEquals(subdivision.getName(), createSubdivision.getName());
        assertEquals(subdivision.getContactDetails(), createSubdivision.getContactDetails());
        assertEquals(subdivision.getDirector(), createSubdivision.getDirector());
        assertEquals(subdivision.getId(), createSubdivision.getId());
        verify(subdivisionRepository, times(1)).save(subdivision);

    }

    @Test
    public void testGetEmployeeById() {
        when(subdivisionRepository.findById(1L)).thenReturn(Optional.of(subdivision));

        Subdivision foundSubdivision = subdivisionServiceImpl.getSubdivisionById(1L);

        assertEquals(subdivision.getName(), foundSubdivision.getName());
    }

    @Test
    public void testUpdateEmployee() {
        when(subdivisionRepository.findById(1L)).thenReturn(Optional.of(subdivision));
        Subdivision updateSubdivision = new Subdivision();
        updateSubdivision.setName("Nike 1");
        updateSubdivision.setContactDetails("Contract#2");

        Subdivision updatedSubdivision = subdivisionServiceImpl.updateSubdivision(1L, updateSubdivision);

        assertEquals("Nike 1", updatedSubdivision.getName());//дописать асерты
        assertEquals("Contract#2", updatedSubdivision.getContactDetails());//дописать асерты
        verify(subdivisionRepository, times(1)).save(subdivision);
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(subdivisionRepository.findById(1L)).thenThrow(ApplicationException.class);

        assertThrows(ApplicationException.class, () -> subdivisionServiceImpl.getSubdivisionById(1L));
    }

    @Test
    public void testGetAllEmployee() {
        List<Subdivision> list = Arrays.asList(subdivision, subdivision);
        when(subdivisionRepository.findAll()).thenReturn(list);

        List<Subdivision> employees = subdivisionServiceImpl.getAllSubdivision();

        assertEquals(2, employees.size());
    }

    @Test
    public void testDeleteEmployee() {
        when(subdivisionRepository.existsById(1L)).thenReturn(true);
        doNothing().when(subdivisionRepository).deleteById(1L);

        subdivisionServiceImpl.deleteSubdivision(1L);

        verify(subdivisionRepository, times(1)).deleteById(1L);
    }

    @Test
    public void delete_NotFound() {
        when(subdivisionRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> subdivisionServiceImpl.deleteSubdivision(1L));
    }
}
